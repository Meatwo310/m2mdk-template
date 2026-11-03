package io.github.meatwo310;

import net.minecraft.resources.Identifier;

public final class ModIds {
    private ModIds() {}
    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(ModConstants.MOD_ID, path);
    }
}
