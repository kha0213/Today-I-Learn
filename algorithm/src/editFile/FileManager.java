package editFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * Created by Kim Young Long.
 * Date : 2021-09-13.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class FileManager {
    public void fileReplace(Path root, String before, String after) {
        List<Path> paths = Files.walk(root, FileVisitOption.FOLLOW_LINKS)
                .collect(toList());


    }
}
