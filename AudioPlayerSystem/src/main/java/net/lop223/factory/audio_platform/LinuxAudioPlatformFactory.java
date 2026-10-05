package net.lop223.factory.audio_platform;

import net.lop223.SystemMediaPlayerEngine;
import net.lop223.factory.metadata_reader.FlacMetadataReader;
import net.lop223.factory.metadata_reader.Mp3MetadataReader;
import net.lop223.utils.IMetadataReader;
import net.lop223.utils.player_engine.IMediaPlayerEngine;

public class LinuxAudioPlatformFactory implements AudioPlatformFactory
{
    @Override
    public IMediaPlayerEngine createMediaPlayerEngine() {
        System.out.println("[Abstract Factory] Initializing Linux WASAPI Media Engine...");
        return new SystemMediaPlayerEngine();
    }

    @Override
    public IMetadataReader createMetadataReader() {
        return new FlacMetadataReader();
    }
}
