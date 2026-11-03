pluginManagement {
    val source = file(providers.gradleProperty("m2mdk.path").getOrElse("../m2mdk"))
    check(source.resolve("gradle-plugins/settings.gradle.kts").isFile) {
        "m2mdk source is missing at $source. Clone it beside this project or set -Pm2mdk.path=/path/to/m2mdk."
    }
    includeBuild(source.resolve("gradle-plugins")) { name = "m2mdk-gradle-plugins" }
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.fabricmc.net/")
        maven("https://maven.minecraftforge.net/")
        maven("https://maven.neoforged.net/releases")
        maven("https://nexus.gtnewhorizons.com/repository/public/") {
            content { includeGroupByRegex("com\\.gtnewhorizons(\\..+)?") }
        }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "m2mdk-template"

val source = file(providers.gradleProperty("m2mdk.path").getOrElse("../m2mdk"))
includeBuild(source) { name = "m2mdk" }

include(":shared")
// Add only the Minecraft versions and loaders this Mod supports.
listOf("common", "fabric", "neoforge").forEach { target ->
    include(":26.1.2-$target")
    project(":26.1.2-$target").projectDir = file("26.1.2/$target")
}
