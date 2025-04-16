package pl.skidam.automodpack_core.paths;

import java.nio.file.Path;

public class ClientModpackPaths extends ModpackPaths {
    protected ClientModpackPaths(Path modpackDir) {
        super(modpackDir);
    }

    @Override
    public Path getModpackContentFile() {
        return getModpackDir().resolve("automodpack-content.json");
    }

    @Override
    public ModpackPaths withReplacedName(String newModpackName) {
        return new ClientModpackPaths(getModpackDir().getParent().resolve(newModpackName));
    }
}
