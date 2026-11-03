import net.fabricmc.loom.api.LoomGradleExtensionAPI
import net.meatwo310.mdk.build.sharedCode

plugins {
    id("net.meatwo310.mdk.mod-base-conventions")
    id("net.meatwo310.mdk.fabric-loom-mod-conventions") apply false
    id("net.meatwo310.mdk.fabric-api-conventions") apply false
    id("net.meatwo310.mdk.fabric-config-conventions") apply false
    id("net.meatwo310.mdk.fabric-config-ui-conventions") apply false
}

sharedCode {
    commonProject.set(":shared")
    versionProject.set(":26.1.2-common")
    configLibraries("26.1.2", "fabric", providers.gradleProperty("mdkVersion").get())
}
apply(plugin = "net.meatwo310.mdk.fabric-loom-mod-conventions")
apply(plugin = "net.meatwo310.mdk.fabric-api-conventions")
apply(plugin = "net.meatwo310.mdk.fabric-config-conventions")
apply(plugin = "net.meatwo310.mdk.fabric-config-ui-conventions")

extensions.configure<LoomGradleExtensionAPI> {
    runs.configureEach { jvmArguments.add("-Xmx1024m") }
}

dependencies {
    add("ciRuntimeMods", "net.fabricmc.fabric-api:fabric-api:${providers.gradleProperty("fabricApiVersion").get()}")
}
