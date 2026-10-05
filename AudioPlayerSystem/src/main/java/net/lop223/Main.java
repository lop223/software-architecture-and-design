package net.lop223;

import net.lop223.builder.TrackBuilder;
import net.lop223.factory.audio_platform.AudioPlatformFactory;
import net.lop223.factory.audio_platform.WindowsAudioPlatformFactory;
import net.lop223.factory.metadata_reader.AudioMetadataReaderFactory;
import net.lop223.factory.metadata_reader.FlacMetadataReaderFactory;
import net.lop223.utils.AudioDevice;
import net.lop223.utils.player_engine.IMediaPlayerEngine;
import net.lop223.utils.IMetadataReader;
import net.lop223.utils.IPlaylistRepository;

import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("===================================================================");
        System.out.println("     ІНТЕГРОВАНА СИСТЕМА АУДІОПЛЕЄРА (6 CREATIONAL PATTERNS)       ");
        System.out.println("===================================================================\n");

        // 1. Singleton: Налаштування плеєра
        AudioPlayerConfig config = AudioPlayerConfig.getInstance();
        config.setBufferSize(8192);
        config.setMaxVolumeLimit(0.9f);
        System.out.println("1. [Singleton] Конфігурація застосунку:\n   " + config + "\n");

        // 2. Abstract Factory: Ініціалізація екосистеми платформи
        System.out.println("2. [Abstract Factory] Ініціалізація компонентів для ОС Windows:");
        AudioPlatformFactory platformFactory = new WindowsAudioPlatformFactory();
        IMediaPlayerEngine audioEngine = platformFactory.createMediaPlayerEngine();
        IMetadataReader defaultMetadataReader = platformFactory.createMetadataReader();

        IPlaylistRepository repository = new InMemoryPlaylistRepository();
        PlaybackQueueService queueService = new PlaybackQueueService(audioEngine);
        PlayerController controller = new PlayerController(queueService, audioEngine, defaultMetadataReader, repository);

        // 3. Створення користувача та плейліста
        User user = new User("usr_101", "Maks");
        Playlist rockPlaylist = user.createPlaylist("Rock Classics");

        // 4. Factory Method: Зчитування MP3 та FLAC треків
        System.out.println("\n3. [Factory Method] Імпорт файлів за допомогою фабричних зчитувачів:");
        controller.importFileToPlaylist("C:/Music/ACDC_Highway_To_Hell.mp3", rockPlaylist);

        AudioMetadataReaderFactory flacFactory = new FlacMetadataReaderFactory();
        IMetadataReader flacReader = flacFactory.createReader();
        Track flacTrack = flacReader.readTrackFromFile("C:/Music/Nirvana_Smells_Like_Teen_Spirit.flac");
        rockPlaylist.addTrack(flacTrack);

        // 5. Builder: Ручне створення складного треку
        System.out.println("\n4. [Builder] Створення треку через Fluent Builder:");
        Track customTrack = new TrackBuilder()
                .title("Comfortably Numb")
                .artist("Pink Floyd")
                .artist("The Wall")
                .genre("Progressive Rock")
                .bitrate(320)
                .durationsSeconds(382)
                .sourcePath("D:/Music/Pink_Floyd_Comfortably_Numb.flac")
                .build();
        rockPlaylist.addTrack(customTrack);

        // 6. Prototype: Клонування плейліста (глибока копія)
        System.out.println("\n5. [Prototype] Клонування плейліста:");
        Playlist workoutPlaylist = rockPlaylist.clone();
        workoutPlaylist.setName("Gym Workout Rock");
        System.out.printf("   Оригінальний плейліст: '%s' (Треків: %d)\n", rockPlaylist.getName(), rockPlaylist.getTracks().size());
        System.out.printf("   Клонований плейліст:   '%s' (Треків: %d)\n", workoutPlaylist.getName(), workoutPlaylist.getTracks().size());

        // 7. Відтворення та робота Object Pool у рушії
        System.out.println("\n6. [Object Pool] Відтворення плейліста через аудіорушій (Pool з'єднань):");
        controller.onSelectPlaylist(rockPlaylist);
        controller.onPlayClicked();   // Відтворює 1-й трек (захоплює Conn_#1)
        controller.onNextClicked();   // Перемикає на 2-й трек (перевикористовує підключення)

        // Перемикання пристрою та гучності
        AudioDevice bluetooth = audioEngine.getAvailableDevices().stream()
                .filter(d -> d.name().contains("Bluetooth"))
                .findFirst()
                .orElse(audioEngine.getCurrentDevice());

        controller.onDeviceSelected(bluetooth);
        controller.onVolumeChanged(0.75f);
        controller.onPauseClicked();

        System.out.println("\n===================================================================");
        System.out.println("           ВСІ КОМПОНЕНТИ ТА ПАТЕРНИ ПРАЦЮЮТЬ ЗЛАГОДЖЕНО!           ");
        System.out.println("===================================================================");
    }
}