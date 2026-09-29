package net.lop223.utils;

import net.lop223.Track;

/**
 * [GRASP: Pure Fabrication]
 * Інтерфейс для зчитування тегів з фізичних файлів.
 */
public interface IMetadataReader {
    Track readTrackFromFile(String filePath);
}