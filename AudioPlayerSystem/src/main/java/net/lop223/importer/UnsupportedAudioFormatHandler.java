package net.lop223.importer;

import net.lop223.Track;

public final class UnsupportedAudioFormatHandler implements AudioImportHandler {
    @Override
    public Track handle(String filePath) {
        throw new IllegalArgumentException(
                "Unsupported audio format: " + filePath);
    }
}