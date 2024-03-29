package tk.masa.blocks.powergen;

import net.minecraftforge.common.ForgeConfigSpec;

public class PowergenConfig {

    public static ForgeConfigSpec.IntValue POWERGEN_CAPACITY;
    public static ForgeConfigSpec.IntValue POWERGEN_GENERATE;
    public static ForgeConfigSpec.IntValue POWERGEN_SEND;

    public static ForgeConfigSpec.DoubleValue RENDER_SCALE;

    public static void registerServerConfig(ForgeConfigSpec.Builder SERVER_BUILDER) {
        SERVER_BUILDER.comment("Settings for the power generator").push("powergen");

        POWERGEN_CAPACITY = SERVER_BUILDER
                .comment("How much energy fits into the power generator")
                .defineInRange("capacity", 50000, 1, Integer.MAX_VALUE);
        POWERGEN_GENERATE = SERVER_BUILDER
                .comment("How much energy is generated by the power generator")
                .defineInRange("generate", 60, 1, Integer.MAX_VALUE);
        POWERGEN_SEND = SERVER_BUILDER
                .comment("How much energy the power generator will send out to adjacent blocks every tick")
                .defineInRange("send", 200, 1, Integer.MAX_VALUE);

        SERVER_BUILDER.pop();
    }

    public static void registerClientConfig(ForgeConfigSpec.Builder CLIENT_BUILDER) {
        CLIENT_BUILDER.comment("Client settings for the power generator").push("powergen");

        RENDER_SCALE = CLIENT_BUILDER
                .comment("Scale of the renderer")
                .defineInRange("scale", .3, 0.000001, 1000.0);

        CLIENT_BUILDER.pop();
    }

}
