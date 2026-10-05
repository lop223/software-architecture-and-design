package net.lop223.factory.metadata_reader;

import net.lop223.Track;
import net.lop223.builder.TrackBuilder;
import net.lop223.utils.IMetadataReader;

import java.time.Duration;
import java.util.Random;
import java.util.UUID;

public class FlacMetadataReader implements IMetadataReader
{
    @Override
    public Track readTrackFromFile(String filePath) {
        Random random = new Random();
        System.out.println("[Factory Method -> FLAC Reader] Reading Vorbis comments & Hi-Res audio: " + filePath);
        String name = extractFileName(filePath);
        return new TrackBuilder()
                .title(name)
                .artist("Flac Artist")
                .album("Flac Album")
                .genre("Flac Genre")
                .bitrate(320)
                .durationsSeconds(215)
                .sourcePath(filePath)
                .build();
    }

    private String extractFileName(String path) {
        return path.substring(Math.max(path.lastIndexOf('/'), path.lastIndexOf('\\')) + 1);
    }
}
