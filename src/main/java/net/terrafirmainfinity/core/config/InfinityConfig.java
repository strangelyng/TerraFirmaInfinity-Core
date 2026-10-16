package net.terrafirmainfinity.core.config;

import net.dries007.tfc.config.BaseConfig;
import net.dries007.tfc.config.ConfigBuilder;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.function.Function;

/**
 * The config for TerraFirmaInfinity-Core is based on the config format used by TFC
 */
public class InfinityConfig {
    public static final ClientConfig CLIENT = register(ClientConfig::new, ConfigBuilder.ClientValue::new, "client");
    public static final ServerConfig SERVER = register(ServerConfig::new, ConfigBuilder.ServerValue::new, "server");

    private static <C extends BaseConfig> C register(Function<ConfigBuilder, C> factory, ConfigBuilder.Factory value, String prefix) {
        final Pair<C, ModConfigSpec> pair = new ModConfigSpec.Builder()
                .configure(builder -> factory.apply(new ConfigBuilder(builder, value, prefix)));
        pair.getKey().updateSpec(pair.getValue());
        return pair.getKey();
    }
}
