import net.meatwo310.mdk.build.sharedCode
import net.neoforged.moddevgradle.dsl.NeoForgeExtension

plugins {
    id("net.meatwo310.mdk.mod-base-conventions")
    id("net.meatwo310.mdk.neoforge-mod-conventions") apply false
    id("net.meatwo310.mdk.neoforge-config-conventions") apply false
    id("net.meatwo310.mdk.neoforge-config-ui-conventions") apply false
}

sharedCode {
    commonProject.set(":shared")
    versionProject.set(":26.1.2-common")
    configLibraries("26.1.2", "neo", providers.gradleProperty("mdkVersion").get())
}
apply(plugin = "net.meatwo310.mdk.neoforge-mod-conventions")
apply(plugin = "net.meatwo310.mdk.neoforge-config-conventions")
apply(plugin = "net.meatwo310.mdk.neoforge-config-ui-conventions")

extensions.configure<NeoForgeExtension> {
    runs.configureEach { jvmArguments.add("-Xmx1024m") }
}
