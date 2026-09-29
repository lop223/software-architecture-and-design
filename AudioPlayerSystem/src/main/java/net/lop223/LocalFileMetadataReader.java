package net.lop223;

import net.lop223.utils.IMetadataReader;

import java.time.Duration;
import java.util.UUID;

public class LocalFileMetadataReader implements IMetadataReader {
    @Override
    public Track readTrackFromFile(String filePath) {
        System.out.println("[STUB] MetadataReader.readTrackFromFile() -> Parsing file ID3 tags: " + filePath);

        // Симуляція зчитування метаданих з назви файлу
        String fileName = filePath.substring(Math.max(filePath.lastIndexOf('/'), filePath.lastIndexOf('\\')) + 1);
        String titleName = fileName.contains(".") ? fileName.substring(0, fileName.lastIndexOf('.')) : fileName;

        var track = new Track(
                UUID.randomUUID().toString(),
                titleName,
                "Artist (Auto-detected)",
                "Single / Album",
                Duration.ofSeconds(215),
                filePath
        );
        return track;
    }
}
