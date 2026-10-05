package net.lop223.factory.metadata_reader;

import net.lop223.Track;
import net.lop223.builder.TrackBuilder;
import net.lop223.utils.IMetadataReader;

import java.time.Duration;
import java.util.Random;
import java.util.UUID;

public class Mp3MetadataReader implements IMetadataReader {
    @Override
    public Track readTrackFromFile(String filePath) {
        Random random = new Random();
        System.out.println("[Factory Method -> MP3 Reader] Reading ID3v2 tags from: " + filePath);
        String name = extractFileName(filePath);
        return new TrackBuilder()
                .title(name)
                .artist("Mp3 Artist")
                .album("Mp3 Album")
                .genre("Mp3 Genre")
                .bitrate(320)
                .durationsSeconds(215)
                .sourcePath(filePath)
                .build();
    }

    private String extractFileName(String path) {
        return path.substring(Math.max(path.lastIndexOf('/'), path.lastIndexOf('\\')) + 1);
    }
}
