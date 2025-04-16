package pl.skidam.automodpack_core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.skidam.automodpack_core.config.Jsons;
import pl.skidam.automodpack_core.loader.*;
import pl.skidam.automodpack_core.modpack.Modpack;
import pl.skidam.automodpack_core.paths.ClientPaths;
import pl.skidam.automodpack_core.paths.ModpackPaths;
import pl.skidam.automodpack_core.paths.ServerPaths;
import pl.skidam.automodpack_core.protocol.netty.NettyServer;

import java.nio.file.Path;

public class GlobalVariables {
    public static final Logger LOGGER = LogManager.getLogger("AutoModpack");
    public static final String MOD_ID = "automodpack";
    public static Boolean DEBUG = false;
    public static Boolean preload;
    public static String MC_VERSION;
    public static String AM_VERSION;
    public static String LOADER_VERSION;
    public static String LOADER;
    public static LoaderManagerService LOADER_MANAGER = new NullLoaderManager();
    public static ModpackLoaderService MODPACK_LOADER = new NullModpackLoader();
    public static GameCallService GAME_CALL = new NullGameCall();
    public static Path THIZ_JAR;
    public static Path MODS_DIR;
    public static Modpack modpack;
    public static NettyServer hostServer;
    public static Jsons.ServerConfigFields serverConfig;
    public static Jsons.ClientConfigFields clientConfig;

    public static ServerPaths serverPaths;
    public static ClientPaths clientPaths;

    public static final String clientConfigFileOverrideResource = "overrides-automodpack-client.json";
    public static String clientConfigOverride; // read from inside a jar file on preload, used instead of clientConfigFile if exists

    public static ModpackPaths selectedModpackPaths;

    public static void init(ServerPaths serverPaths, ClientPaths clientPaths) {
        if (GlobalVariables.serverPaths != null || GlobalVariables.clientPaths != null) {
            throw new IllegalStateException("ServerPaths or clientPaths are already defined");
        }
        GlobalVariables.serverPaths = serverPaths;
        GlobalVariables.clientPaths = clientPaths;
    }
}
