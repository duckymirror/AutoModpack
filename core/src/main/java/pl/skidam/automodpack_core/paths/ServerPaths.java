package pl.skidam.automodpack_core.paths;

import java.nio.file.Path;

public class ServerPaths extends CommonPaths {
    private final Path certChainFile;

    private final Path privateKeyFile;

    public ServerPaths(Path dataDir) {
        super(dataDir);
        certChainFile = getPrivateDir().resolve("cert.crt");
        privateKeyFile = getPrivateDir().resolve("key.pem");
    }

    public ServerPaths(Path dataDir, Path certChainFile, Path privateKeyFile) {
        super(dataDir);
        this.certChainFile = certChainFile;
        this.privateKeyFile = privateKeyFile;
    }

    public Path getConfigFile() {
        return getDataDir().resolve("automodpack-server.json");
    }

    public Path getModpackDir(String modpackName) {
        return getDataDir().resolve(modpackName);
    }

//    public Path getHostModpackPath() {
//        return getDataDir().resolve("host-modpack");
//    }

    // TODO More server modpacks
    // Main - required
    // Addons - optional addon packs
    // Switches - optional or required packs, chosen by the player, only one can be installed at a time

    public ServerModpackPaths getMainModpackPaths(String modpackName) {
        return new ServerModpackPaths(getModpackDir(modpackName).resolve("main"));
    }

    public Path getServerSecretsFile() {
        return getPrivateDir().resolve("automodpack-secrets.json");
    }

    public Path getCertChainFile() {
        return certChainFile;
    }

    public Path getPrivateKeyFile() {
        return privateKeyFile;
    }
}
