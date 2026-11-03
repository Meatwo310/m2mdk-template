package io.github.meatwo310.config;

import java.util.List;
import net.meatwo310.mdk.config.ConfigDeclaration;
import net.meatwo310.mdk.config.ConfigEntries;
import net.meatwo310.mdk.config.ConfigEntry;
import net.meatwo310.mdk.config.ConfigEntryBuilder;
import net.meatwo310.mdk.config.ConfigSide;

public final class ModConfigs {
    private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();
    public static final ConfigEntry.BooleanEntry ENABLED = BUILDER.comment("Enable the example feature.").define("enabled", true);
    public static final ConfigEntries ENTRIES = BUILDER.build();
    public static final List<ConfigDeclaration> ALL = List.of(ConfigDeclaration.of(ConfigSide.SYNCED, ENTRIES));
    private ModConfigs() {}
}
