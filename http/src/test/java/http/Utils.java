package http;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

final public class Utils {

    private Utils() {
    }

    public static String readFile(String fileName) {
        try {
            Path resourceDirectory = Paths.get("src", "test", "resources");
            String absolutePath = resourceDirectory.toFile().getAbsolutePath();
            byte[] encoded = Files.readAllBytes(Paths.get(absolutePath, fileName));
            return new String(encoded, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
