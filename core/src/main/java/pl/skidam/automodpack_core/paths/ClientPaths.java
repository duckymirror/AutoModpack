package pl.skidam.automodpack_core.paths;

import java.nio.file.Path;

public class ClientPaths extends CommonPaths {
    public ClientPaths(Path dataDir) {
        super(dataDir);
    }

    public Path getModpackContentTempFile() {
        return getDataDir().resolve("automodpack-content.json.temp");
    }

    public Path getClientConfigFile() {
        return getDataDir().resolve("automodpack-client.json");
    }

    public Path getClientSecretsFile() {
        return getPrivateDir().resolve("automodpack-client-secrets.json");
    }

    public Path getModpacksDir() {
        return getDataDir().resolve("modpacks");
    }

    public ClientModpackPaths getModpackPaths(String modpack) {
        return new ClientModpackPaths(getModpacksDir().resolve(modpack));
    }
}
