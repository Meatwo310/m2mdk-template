import net.meatwo310.mdk.build.sharedCode

plugins {
    `java-library`
    id("net.meatwo310.mdk.mod-base-conventions")
    id("net.meatwo310.mdk.common-config-conventions") apply false
    id("net.meatwo310.mdk.common-config-ui-conventions") apply false
}

sharedCode {
    configCoreDependency.set("net.meatwo310.mdk:config-core:${providers.gradleProperty("mdkVersion").get()}")
}
apply(plugin = "net.meatwo310.mdk.common-config-conventions")
apply(plugin = "net.meatwo310.mdk.common-config-ui-conventions")

java.toolchain.languageVersion.set(JavaLanguageVersion.of(25))
java.withSourcesJar()
val constants = tasks.register<Copy>("generateModConstants") {
    val values = mapOf("mod_id" to providers.gradleProperty("modId").get())
    inputs.properties(values)
    from("src/main/templates")
    expand(values)
    into(layout.buildDirectory.dir("generated/sources/modConstants"))
}
sourceSets.main { java.srcDir(constants) }
