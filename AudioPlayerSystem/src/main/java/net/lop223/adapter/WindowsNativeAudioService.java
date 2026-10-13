package net.lop223.adapter;

public class WindowsNativeAudioService {
    public void openFile(String filePath) {
        System.out.println("[Adapter: WindowsNativeAudioService] openFile(" + filePath + ")");
    }

    public void startPlayback() {
        System.out.println("[Adapter: WindowsNativeAudioService] startPlayback()");
    }

    public void pausePlayback() {
        System.out.println("[Adapter: WindowsNativeAudioService] pausePlayback()");
    }

    public void stopPlayback() {
        System.out.println("[Adapter: WindowsNativeAudioService] stopPlayback()");
    }

    public void setNativeVolume(float volume) {
        System.out.println("[Adapter: WindowsNativeAudioService] setNativeVolume(" + volume + ")");
    }

    public void switchDevice(String deviceId) {
        System.out.println("[Adapter: WindowsNativeAudioService] switchDevice(" + deviceId + ")");
    }
}
