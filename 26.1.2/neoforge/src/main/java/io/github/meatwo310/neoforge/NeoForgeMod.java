package io.github.meatwo310.neoforge;

import io.github.meatwo310.ExampleMod;
import io.github.meatwo310.ModConstants;
import io.github.meatwo310.config.ModConfigs;
import net.meatwo310.mdk.config.adapter.PlatformConfigRegistrar;
import net.meatwo310.mdk.config.adapter.VersionedConfigSpec;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(ModConstants.MOD_ID)
public final class NeoForgeMod {
    public NeoForgeMod(ModContainer container) {
        ExampleMod.initialize();
        PlatformConfigRegistrar.registerAll(container, VersionedConfigSpec.bindAll(ModConfigs.ALL));
    }
}
