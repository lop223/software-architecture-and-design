package net.lop223.factory.metadata_reader;

import net.lop223.Track;
import net.lop223.builder.TrackBuilder;
import net.lop223.utils.IMetadataReader;

import java.time.Duration;
import java.util.Random;
import java.util.UUID;

public class FlacMetadataReader extends AudioMetadataReaderTemplate {
    @Override
    protected String formatName() {
        return "FLAC";
    }

    @Override
    protected void addFormatMetadata(TrackBuilder builder) {
        builder.artist("Flac Artist")
                .album("Flac Album")
                .genre("Flac Genre")
                .bitrate(320)
                .durationsSeconds(215);
    }
}