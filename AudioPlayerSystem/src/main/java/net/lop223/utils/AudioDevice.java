package net.lop223.utils;

/**
 * Опис системного пристрою виведення звуку (динаміки, навушники, Bluetooth).
 */
public record AudioDevice(String deviceId, String name, boolean isDefault) {

    @Override
    public String toString() {
        return name + (isDefault ? " [Default Device]" : "") + " (ID: " + deviceId + ")";
    }
}