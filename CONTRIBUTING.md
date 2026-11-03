# Contributing

This is a consumer Mod, not the implementation or test fixture for m2mdk.
Use JDK 25 and a sibling m2mdk source checkout (or `-Pm2mdk.path=...`).

Run `./gradlew build assembleRelease` after changes. Exercise affected loader
entrypoints with their `runClient` or `runServer` tasks when changing runtime
behavior. Only add the Minecraft versions and loaders this Mod needs.

Reusable build behavior, tool workarounds, and template-specific regression
tests belong in m2mdk, not here. Changing the Mod package, deleting sample
classes, or removing Config/UI must not require editing a template test.

Use English Conventional Commits. Do not commit generated artifacts,
development worlds, tokens, or local m2mdk copies.
