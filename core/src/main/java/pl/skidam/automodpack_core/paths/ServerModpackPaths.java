package pl.skidam.automodpack_core.paths;

import java.nio.file.Path;

public class ServerModpackPaths extends ModpackPaths {
    public ServerModpackPaths(Path modpackDir) {
        super(modpackDir);
    }

    @Override
    public Path getModpackContentFile() {
        return getModpackDir().getParent().resolve("automodpack-content.json");
    }

    @Override
    public ModpackPaths withReplacedName(String newModpackName) {
        return new ServerModpackPaths(getModpackDir().getParent().resolve(newModpackName));
    }
}
