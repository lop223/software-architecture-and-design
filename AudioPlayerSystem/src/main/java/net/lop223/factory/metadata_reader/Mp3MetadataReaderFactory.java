package net.lop223.factory.metadata_reader;

import net.lop223.utils.IMetadataReader;

public class Mp3MetadataReaderFactory extends AudioMetadataReaderFactory
{
    @Override
    public IMetadataReader createReader() {
        return new Mp3MetadataReader();
    }
}
