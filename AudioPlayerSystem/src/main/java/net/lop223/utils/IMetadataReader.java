package net.lop223.utils;

import net.lop223.Track;

/**
 * [GRASP: Pure Fabrication]
 * Інтерфейс для зчитування тегів з фізичних файлів.
 * [SOLID: SRP, ISP (Interface Segregation)]
 * Читання метаданих аудіотреків.
 */
public interface IMetadataReader {
    Track readTrackFromFile(String filePath);
}