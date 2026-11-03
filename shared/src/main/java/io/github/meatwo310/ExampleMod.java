package io.github.meatwo310;

/** Loader-independent initialization. Keep client-only types out of this class. */
public final class ExampleMod {
    private static final System.Logger LOGGER = System.getLogger(ModConstants.MOD_ID);
    private ExampleMod() {}

    public static void initialize() {
        LOGGER.log(System.Logger.Level.INFO, "Initialized " + ModConstants.MOD_ID);
    }
}
