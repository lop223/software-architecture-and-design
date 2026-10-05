package net.lop223;

public class AudioPlayerConfig {
    private int bufferSize;
    private int defaultSampleRate;
    private boolean hardwareAcceleration;
    private float maxVolumeLimit;

    private AudioPlayerConfig() {
        this.bufferSize = 4096;
        this.defaultSampleRate = 44100;
        this.hardwareAcceleration = true;
        this.maxVolumeLimit = 1.0f;
    }

    private static class InstanceHolder {
        private static final AudioPlayerConfig INSTANCE = new AudioPlayerConfig();
    }

    public static AudioPlayerConfig getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public int getBufferSize() { return bufferSize; }
    public void setBufferSize(int bufferSize) { this.bufferSize = bufferSize; }

    public int getDefaultSampleRate() { return defaultSampleRate; }
    public void setDefaultSampleRate(int defaultSampleRate) { this.defaultSampleRate = defaultSampleRate; }

    public boolean isHardwareAcceleration() { return hardwareAcceleration; }
    public void setHardwareAcceleration(boolean hardwareAcceleration) { this.hardwareAcceleration = hardwareAcceleration; }

    public float getMaxVolumeLimit() { return maxVolumeLimit; }
    public void setMaxVolumeLimit(float maxVolumeLimit) { this.maxVolumeLimit = maxVolumeLimit; }

    @Override
    public String toString() {
        return String.format("AudioPlayerConfig [Buffer: %d bytes, SampleRate: %d Hz, HW-Accel: %b, MaxVol: %.0f%%]",
                bufferSize, defaultSampleRate, hardwareAcceleration, maxVolumeLimit * 100);
    }
}
