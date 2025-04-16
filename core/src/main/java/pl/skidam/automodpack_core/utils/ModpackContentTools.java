package pl.skidam.automodpack_core.utils;

import pl.skidam.automodpack_core.config.Jsons;
import pl.skidam.automodpack_core.paths.ModpackPaths;

import java.nio.file.Path;
import java.util.Optional;

import static pl.skidam.automodpack_core.GlobalVariables.*;

public class ModpackContentTools {
    public static String getFileType(String file, Jsons.ModpackContentFields list) {
        for (Jsons.ModpackContentFields.ModpackContentItem item : list.list) {
            if (item.file.contains(file)) { // compare file absolute path if it contains item.file
                return item.type;
            }
        }
        return "other";
    }

    public static Optional<ModpackPaths> getClientModpackPaths(String modpack) {
        if (modpack == null || modpack.isEmpty()) {
            return Optional.empty();
        }

        // eg. modpack = /automodpack/modpacks/TestPack `directory`

        return Optional.of(clientPaths.getModpackPaths(modpack));
    }

//    public static Optional<Path> getModpackContentFile(Path modpackDir) {
//        if (!Files.exists(modpackDir)) {
//            return Optional.empty();
//        }
//
//        Path path = modpackDir.getParent().resolve(hostModpackContentFile.getFileName()); // server
//        if (!Files.exists(path)) {
//            path = modpackDir.resolve(hostModpackContentFile.getFileName()); // client
//            if (!Files.exists(path)) {
//                return Optional.empty();
//            }
//        }
//
//        return Optional.of(path);
//    }
}
