package net.lop223.factory.audio_platform;

import net.lop223.utils.IMetadataReader;
import net.lop223.utils.player_engine.IMediaPlayerEngine;

public interface AudioPlatformFactory
{
    IMediaPlayerEngine createMediaPlayerEngine();
    IMetadataReader createMetadataReader();
}
