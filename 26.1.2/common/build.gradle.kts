import net.meatwo310.mdk.build.sharedCode

plugins {
    id("net.meatwo310.mdk.mod-base-conventions")
    id("net.meatwo310.mdk.neoforge-common-conventions") apply false
    id("net.meatwo310.mdk.neoforge-common-config-conventions") apply false
    id("net.meatwo310.mdk.neoforge-common-config-ui-conventions") apply false
}

sharedCode {
    commonProject.set(":shared")
    configLibraries("26.1.2", "common", providers.gradleProperty("mdkVersion").get())
}
apply(plugin = "net.meatwo310.mdk.neoforge-common-conventions")
apply(plugin = "net.meatwo310.mdk.neoforge-common-config-conventions")
apply(plugin = "net.meatwo310.mdk.neoforge-common-config-ui-conventions")
