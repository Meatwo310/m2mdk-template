package io.github.meatwo310.fabric.client;

import io.github.meatwo310.ModConstants;
import net.fabricmc.api.ClientModInitializer;
import net.meatwo310.mdk.config.ui.PlatformConfigScreenRegistrar;

public final class FabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PlatformConfigScreenRegistrar.register(ModConstants.MOD_ID);
    }
}
