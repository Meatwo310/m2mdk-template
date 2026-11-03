# m2mdk-template

必要なMinecraft・ローダーだけを追加して使う、小さいMod用Gradleプロジェクトです。
初期構成はMinecraft 26.1.2のFabric / NeoForgeです。Configと設定画面を含みますが、
それぞれのプラグインとサンプルを削除して外せます。

## 開発を始める

JDK 25を用意し、m2mdkのソースを隣へ配置してください。Nixなら `nix develop` でJDKを用意できます。

```text
workspace/
  m2mdk/
  my-mod/       このテンプレートから作った、自分のリポジトリ
```

```sh
./gradlew build assembleRelease
./gradlew :26.1.2-fabric:runClient
./gradlew :26.1.2-neoforge:runClient
```

別の場所のソースを使う場合は `-Pm2mdk.path=/absolute/path/to/m2mdk` を指定します。
`settings.gradle.kts` の2つの `includeBuild` がプラグインとライブラリを直接参照します。
自動clone、キャッシュされたソース、テンプレートとのGit mergeは使いません。
現在の更新は、参照するm2mdkソースの更新です。将来Maven公開物を利用する際は、
プラグインのバージョンと `mdkVersion` を更新する形に切り替えます。

## 共有関係

```text
shared/                全バージョン共通。Minecraft APIに依存しないコードとConfig宣言
26.1.2/common/         このバージョンのMinecraft APIを使うローダー共通コード
26.1.2/fabric/         Fabric固有コード
26.1.2/neoforge/       NeoForge固有コード
```

各 `build.gradle.kts` の `sharedCode` に共有先を明示します。
プロジェクト名やディレクトリ名から共有関係や依存を推測しません。
設定値をローダープラグインが適用時に読むため、プラグインを `apply false` で宣言し、
`sharedCode` の後で `apply(plugin = ...)` します。適用後の追加設定は型付きの
`extensions.configure` で行います。

`configLibraries(Minecraftの版, ローダー, mdkVersion)` はConfigライブラリの座標を設定します。
座標を設定するだけではConfigやUIを有効化しません。対応するプラグインの適用が必要です。
実際の依存は `./gradlew :26.1.2-fabric:dependencies` で確認できます。

## 自分のModにする

- `gradle.properties` のMod ID、名前、group、作者、説明、URLと `LICENSE` を変更します。
- Javaパッケージ・各entrypointのプロパティを変更し、`ExampleMod` 等を自分の実装へ置き換えます。
- `version.txt` にModのバージョンを書き、`settings.gradle.kts` のプロジェクト名を変更します。

Mod ID定数は `shared/src/main/templates` から生成されます。
Configの再配置先は既定で `<modGroupId>.internal.mdk` です。
サンプル固有のクラス名やパッケージを要求する検証は、このリポジトリに含みません。

## Config・画面を外す

画面だけを外す場合:

1. shared・common・各ローダーから `*-config-ui-conventions` の宣言と適用を削除します。
2. Fabricの `FabricClient` の画面登録呼び出しとimportを削除します。
3. NeoForgeの画面登録専用 `NeoForgeClient` を削除します。

Configも外す場合:

1. さらに `*-config-conventions` の宣言と適用、`configLibraries` と
   `configCoreDependency` の設定を削除します。
2. `shared/src/config` を削除し、各Mod entrypointのConfig登録呼び出しとimportを削除します。

Configを残したまま画面だけを外せます。機能フラグや未使用コードの温存は不要です。
FabricでConfigを使う場合、実行環境にはForge Config API Portも必要です。

## 対応対象を追加・削除する

不要なローダーはsettingsのincludeと対応ディレクトリを削除します。
ルートのbuildと `assembleRelease` は残ったプロジェクトだけを対象にします。

別のMinecraft版は、その版のcommonと必要なローダーのディレクトリを追加して、
settingsへ明示的にincludeします。各プロジェクトに版・Java・ローダー依存の
`gradle.properties` を置き、`sharedCode` の共有先と `configLibraries` を指定してください。
現在ルートにあるMinecraft関連プロパティは、最初の26.1.2用の値です。
Fabricの互換範囲は `src/main/templates/fabric.mod.json`、NeoForgeは
`minecraftVersionRange` で指定します。

m2mdkソース側も通常は26.1.2のライブラリだけを構成します。
別の版や複数版を使う場合は、例えば
`./gradlew -Pmdk.libraryMinecraft=1.21.1,26.1.2 build` と明示します。
利用できる規約はm2mdkの `docs/gradle-plugins.md` を参照してください。

## 成果物と更新

`./gradlew assembleRelease` は `platformArtifacts` を宣言するプロジェクトの
配布JAR・sources JARを `build/release/<project-name>/` に集めます。
実行時に必要な追加Modは、その下の `dependencies/` に収集します。
共有コード用JARは配布対象に含めません。

CIも通常のbuildと設定キャッシュ再利用だけを確認します。テンプレート自体の検証・
サーバー起動テストはm2mdk側の `scripts/verify-consumer.py` と
`scripts/consumer-smoke-server.py` が担当します。

依存更新時は `--write-verification-metadata sha256` でチェックサム候補を生成し、
取得元と差分を確認してください。依存検証を無効化しないでください。
Loomがローカル生成するMinecraft common/clientOnlyのJARとPOMは、
ZIP時刻や有効なaccess widenerでハッシュ・座標が変わります。
`verification-metadata.xml` では26.1.2のこの生成物群だけを、
座標とファイル名のパターンで区別しています。
ダウンロードする依存のチェックサム検証は維持しています。
現在のCIはm2mdkの `main2` を別checkoutとして参照します。再現可能なリリースが
必要な場合は、CI/releaseのcheckoutの `ref` を使用するm2mdkのコミットへ固定してください。

## License

[MIT](LICENSE)
