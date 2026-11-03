package io.github.meatwo310.neoforge.client;

import io.github.meatwo310.ModConstants;
import net.meatwo310.mdk.config.ui.PlatformConfigScreenRegistrar;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(value = ModConstants.MOD_ID, dist = Dist.CLIENT)
public final class NeoForgeClient {
    public NeoForgeClient(ModContainer container) {
        PlatformConfigScreenRegistrar.register(container);
    }
}
