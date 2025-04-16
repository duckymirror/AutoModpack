package pl.skidam.automodpack_core;

import pl.skidam.automodpack_core.config.ConfigTools;
import pl.skidam.automodpack_core.config.Jsons;
import pl.skidam.automodpack_core.modpack.Modpack;
import pl.skidam.automodpack_core.modpack.ModpackContent;
import pl.skidam.automodpack_core.paths.ModpackPaths;
import pl.skidam.automodpack_core.paths.ServerPaths;
import pl.skidam.automodpack_core.protocol.netty.NettyServer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static pl.skidam.automodpack_core.GlobalVariables.*;

public class Server {

    // TODO Finish this class that it will be able to host the server without mod
    public static void main(String[] args) {

        if (args.length < 1) {
            LOGGER.error("Modpack id not provided!");
            return;
        }

        Path dataDir = Path.of(System.getProperty("user.dir")).resolve("modpacks");
        ServerPaths serverPaths = new ServerPaths(dataDir);

        String modpackDirStr = args[0];

        ModpackPaths mainModpackPaths = serverPaths.getMainModpackPaths(modpackDirStr);

        mainModpackPaths.getModpackDir().toFile().mkdirs();

        NettyServer server = new NettyServer(serverPaths, mainModpackPaths);
        hostServer = server;

        Path serverCoreConfigFile = dataDir.resolve("automodpack-core.json");

        serverConfig = ConfigTools.load(serverPaths.getConfigFile(), Jsons.ServerConfigFields.class);
        if (serverConfig != null) {
            serverConfig.syncedFiles = new ArrayList<>();
            serverConfig.hostModpackOnMinecraftPort = false;
            serverConfig.validateSecrets = false;
            ConfigTools.save(serverPaths.getConfigFile(), serverConfig);

            if (serverConfig.hostPort == -1) {
                LOGGER.error("Host port not set in config!");
                return;
            }
        }

        Jsons.ServerCoreConfigFields serverCoreConfig = ConfigTools.load(serverCoreConfigFile, Jsons.ServerCoreConfigFields.class);
        if (serverCoreConfig != null) {
            AM_VERSION = serverCoreConfig.automodpackVersion;
            LOADER = serverCoreConfig.loader;
            LOADER_VERSION = serverCoreConfig.loaderVersion;
            MC_VERSION = serverCoreConfig.mcVersion;
            ConfigTools.save(serverCoreConfigFile, serverCoreConfig);
        }

        mainModpackPaths.getModpackDir().toFile().mkdirs();

        Modpack modpack = new Modpack(modpackDirStr);
        ModpackContent modpackContent = new ModpackContent(serverConfig.modpackName, null, mainModpackPaths, serverConfig.syncedFiles, serverConfig.allowEditsInFiles, modpack.CREATION_EXECUTOR);
        boolean generated = modpack.generateNew(modpackContent);

        if (generated) {
            LOGGER.info("Modpack generated!");
        } else {
            LOGGER.error("Failed to generate modpack!");
        }

        modpack.CREATION_EXECUTOR.shutdownNow();

        LOGGER.info("Starting server on port {}", serverConfig.hostPort);
        server.start();
        // wait for server to stop
        while (server.isRunning()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOGGER.error("Interrupted server thread", e);
            }
        }
    }
}
