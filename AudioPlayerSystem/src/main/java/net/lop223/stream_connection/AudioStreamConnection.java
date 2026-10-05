package net.lop223.stream_connection;

public class AudioStreamConnection {
    private final String connectionId;
    private String activeStreamUrl;
    private boolean active = false;

    public AudioStreamConnection(String connectionId) {
        this.connectionId = connectionId;
        System.out.println("  [System/Socket] Initialized heavy AudioStreamConnection: " + connectionId);
    }

    public void bindStream(String streamUrl) {
        this.activeStreamUrl = streamUrl;
        this.active = true;
        System.out.printf("  [%s] Stream bound to URL/File: %s\n", connectionId, streamUrl);
    }

    public void reset() {
        this.activeStreamUrl = null;
        this.active = false;
        System.out.printf("  [%s] Connection state reset.\n", connectionId);
    }

    public String getConnectionId() { return connectionId; }
    public boolean isActive() { return active; }
}