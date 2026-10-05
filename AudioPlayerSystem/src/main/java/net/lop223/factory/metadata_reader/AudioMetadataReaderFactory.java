package net.lop223.factory.metadata_reader;

import net.lop223.utils.IMetadataReader;

public abstract class AudioMetadataReaderFactory
{
    // Фабричний метод
    public abstract IMetadataReader createReader();

    public void processAudioFile(String filePath) {
        IMetadataReader reader = createReader();
        reader.readTrackFromFile(filePath);
    }
}
