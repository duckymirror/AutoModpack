package pl.skidam.automodpack_core.paths;

import java.nio.file.Path;

public abstract class ModpackPaths {
    private final Path modpackDir;

    protected ModpackPaths(Path modpackDir) {
        this.modpackDir = modpackDir;
    }

    public Path getModpackDir() {
        return modpackDir;
    }

    public abstract Path getModpackContentFile();

    public abstract ModpackPaths withReplacedName(String newModpackName);
}
