plugins {
    id("net.meatwo310.mdk.mod-workspace-conventions")
    id("net.meatwo310.mdk.mod-publish-conventions")
}

subprojects {
    repositories {
        mavenCentral()
        maven("https://libraries.minecraft.net")
        maven("https://maven.neoforged.net/releases")
        maven("https://maven.fabricmc.net/")
        exclusiveContent {
            forRepository { maven("https://raw.githubusercontent.com/Fuzss/modresources/main/maven/") }
            filter { includeGroupByRegex("fuzs\\..+") }
        }
    }
}

modPublishing {
    curseForge {
        providers.gradleProperty("curseForgeProjectId").orNull?.let { projectId.set(it) }
        client.set(true)
        server.set(true)
    }
    modrinth {
        providers.gradleProperty("modrinthProjectId").orNull?.let { projectId.set(it) }
        environment.set(CLIENT_AND_SERVER)
    }
}
