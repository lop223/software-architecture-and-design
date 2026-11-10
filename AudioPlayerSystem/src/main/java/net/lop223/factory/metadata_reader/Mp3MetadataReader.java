package net.lop223.factory.metadata_reader;

import net.lop223.Track;
import net.lop223.builder.TrackBuilder;
import net.lop223.utils.IMetadataReader;

import java.time.Duration;
import java.util.Random;
import java.util.UUID;

public class Mp3MetadataReader extends AudioMetadataReaderTemplate {
    @Override
    protected String formatName() {
        return "MP3";
    }

    @Override
    protected void addFormatMetadata(TrackBuilder builder) {
        builder.artist("Mp3 Artist")
                .album("Mp3 Album")
                .genre("Mp3 Genre")
                .bitrate(320)
                .durationsSeconds(215);
    }
}
