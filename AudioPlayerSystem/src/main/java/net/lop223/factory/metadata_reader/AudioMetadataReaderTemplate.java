package net.lop223.factory.metadata_reader;

import net.lop223.Track;
import net.lop223.builder.TrackBuilder;
import net.lop223.utils.IMetadataReader;

public abstract class AudioMetadataReaderTemplate implements IMetadataReader {
    @Override
    public final Track readTrackFromFile(String filePath) {
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("File path must not be empty.");
        }

        System.out.println("[Template Method -> " + formatName()
                + " Reader] Reading metadata from: " + filePath);

        TrackBuilder builder = new TrackBuilder()
                .title(extractFileName(filePath))
                .sourcePath(filePath);

        addFormatMetadata(builder);
        return builder.build();
    }

    protected abstract String formatName();

    protected abstract void addFormatMetadata(TrackBuilder builder);

    private String extractFileName(String path) {
        return path.substring(Math.max(path.lastIndexOf('/'), path.lastIndexOf('\\')) + 1);
    }
}
