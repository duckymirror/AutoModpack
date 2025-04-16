package pl.skidam.automodpack_core.paths;

import java.nio.file.Path;

public abstract class CommonPaths {
    private final Path dataDir;

    public CommonPaths(Path dataDir) {
        this.dataDir = dataDir;
    }

    public Path getDataDir() {
        return dataDir;
    }

    public Path getPrivateDir() {
        return getDataDir().resolve(".private");
    }
}
