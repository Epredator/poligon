package pl.etroya.corejava.ioStreamsAndFiles;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {
        String[] data = {
                "Line 1",
                "Line 2 2",
                "Line 3 3 3"
        };

        FileSystem zipFs = openZip(Paths.get("testData.zip"));
    }

    private static FileSystem openZip(Path zipPath) throws URISyntaxException, IOException {
        Map<String, String> providerProps = new HashMap<>();
        providerProps.put("create", "true");

        URI zipUri = new URI("jar:file", zipPath.toUri().getPath(), null);
        FileSystem zipFs = FileSystems.newFileSystem(zipUri, providerProps);
        return zipFs;

    }

    private static void copyToZim(FileSystem zipFs) {

    }
}
