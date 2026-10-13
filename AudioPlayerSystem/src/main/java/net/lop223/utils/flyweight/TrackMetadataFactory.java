package net.lop223.utils.flyweight;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class TrackMetadataFactory {
    private static final ConcurrentMap<TrackMetadata, TrackMetadata> METADATA_POOL = new ConcurrentHashMap<>();

    private TrackMetadataFactory() {}

    public static TrackMetadata getMetadata(String artist, String album, String genre) {
        TrackMetadata key = new TrackMetadata(artist, album, genre);
        return METADATA_POOL.computeIfAbsent(key, metadata -> metadata);
    }
}
