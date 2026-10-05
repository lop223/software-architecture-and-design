package net.lop223.factory.metadata_reader;

import net.lop223.utils.IMetadataReader;

public class FlacMetadataReaderFactory extends AudioMetadataReaderFactory
{
    @Override
    public IMetadataReader createReader() {
        return new FlacMetadataReader();
    }
}
