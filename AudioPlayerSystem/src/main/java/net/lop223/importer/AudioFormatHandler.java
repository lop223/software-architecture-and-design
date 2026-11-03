package net.lop223.importer;

import net.lop223.Track;
import net.lop223.utils.IMetadataReader;

import java.util.Objects;

public class AudioFormatHandler implements AudioImportHandler{
    private final String extension;
    private final IMetadataReader metadataReader;
    private final AudioImportHandler next;

    public AudioFormatHandler(
            String extension,
            IMetadataReader metadataReader,
            AudioImportHandler next) {
        this.extension = Objects.requireNonNull(extension).toLowerCase();
        this.metadataReader = Objects.requireNonNull(metadataReader);
        this.next = Objects.requireNonNull(next);
    }

    @Override
    public Track handle(String filePath) {
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("File path must not be empty.");
        }

        String fileName = filePath.substring(
                Math.max(filePath.lastIndexOf('/'), filePath.lastIndexOf('\\')) + 1);
        int extensionStart = fileName.lastIndexOf('.');

        if (extensionStart >= 0
                && fileName.substring(extensionStart).equalsIgnoreCase(extension)) {
            return metadataReader.readTrackFromFile(filePath);
        }

        return next.handle(filePath);
    }
}
