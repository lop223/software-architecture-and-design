package net.lop223.utils.player_engine;

import net.lop223.utils.AudioDevice;

import java.util.List;

public interface IAudioDeviceManager {
    AudioDevice getCurrentDevice();
    List<AudioDevice> getAvailableDevices();
    void setOutputDevice(AudioDevice device);
}
