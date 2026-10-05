package net.lop223.stream_connection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * [ПАТТЕРН: Object Pool (Пул об'єктів)]
 * Керує обмеженою кількістю готових підключень (наприклад, максимум 3 з'єднання).
 */
public class AudioConnectionPool {
    private final BlockingQueue<AudioStreamConnection> availableConnections;
    private final int maxPoolSize;

    public AudioConnectionPool(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
        this.availableConnections = new LinkedBlockingQueue<>(maxPoolSize);

        System.out.printf("[AudioConnectionPool] Створення пулу з %d попередньо ініціалізованих з'єднань...\n", maxPoolSize);
        for (int i = 1; i <= maxPoolSize; i++) {
            availableConnections.offer(new AudioStreamConnection("Conn_#" + i));
        }
    }

    public AudioStreamConnection acquireConnection() {
        AudioStreamConnection connection = availableConnections.poll();
        if (connection == null) {
            System.out.println("[AudioConnectionPool WARN] Усі з'єднання зайняті! Очікування вивільнення...");
            return null;
        }
        return connection;
    }

    public void releaseConnection(AudioStreamConnection connection) {
        if (connection != null) {
            connection.reset();
            availableConnections.offer(connection);
        }
    }

    public int getFreeCount() {
        return availableConnections.size();
    }
}