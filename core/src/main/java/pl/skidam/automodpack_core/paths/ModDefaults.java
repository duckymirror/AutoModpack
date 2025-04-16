package pl.skidam.automodpack_core.paths;

import java.nio.file.Path;

public class ModDefaults {
    public static final Path DATA_DIR = Path.of(System.getProperty("user.dir"), "automodpack");
    public static final String HOST_MODPACK_ID = "host-modpack";
}
