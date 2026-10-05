package net.lop223.stream_connection;

public class AudioStreamConnection {
    private final String connectionId;
    private boolean inUse = false;
    private String currentTrackUrl;

    public AudioStreamConnection(String connectionId) {
        this.connectionId = connectionId;
        System.out.println("  [System] Встановлено нове важке TLS/TCP з'єднання: " + connectionId);
    }

    public void openStream(String trackUrl) {
        this.currentTrackUrl = trackUrl;
        this.inUse = true;
        System.out.printf("  [%s] Початок передачі аудіопотоку для: %s\n", connectionId, trackUrl);
    }

    public void reset() {
        // Очищаємо стан перед поверненням у пул
        this.currentTrackUrl = null;
        this.inUse = false;
        System.out.printf("  [%s] З'єднання скинуто та готове до повторного використання.\n", connectionId);
    }

    public String getConnectionId() { return connectionId; }
    public boolean isInUse() { return inUse; }
}