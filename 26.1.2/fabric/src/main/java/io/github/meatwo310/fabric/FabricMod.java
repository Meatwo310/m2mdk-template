package io.github.meatwo310.fabric;

import io.github.meatwo310.ExampleMod;
import io.github.meatwo310.ModConstants;
import io.github.meatwo310.config.ModConfigs;
import net.fabricmc.api.ModInitializer;
import net.meatwo310.mdk.config.adapter.PlatformConfigRegistrar;
import net.meatwo310.mdk.config.adapter.VersionedConfigSpec;

public final class FabricMod implements ModInitializer {
    @Override
    public void onInitialize() {
        ExampleMod.initialize();
        PlatformConfigRegistrar.registerAll(ModConstants.MOD_ID, VersionedConfigSpec.bindAll(ModConfigs.ALL));
    }
}
