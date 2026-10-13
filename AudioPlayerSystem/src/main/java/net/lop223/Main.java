package net.lop223;

import net.lop223.adapter.NativeAudioServiceAdapter;
import net.lop223.adapter.WindowsNativeAudioService;
import net.lop223.builder.TrackBuilder;
import net.lop223.decorator.VolumeLimitMediaPlayerDecorator;
import net.lop223.factory.audio_platform.AudioPlatformFactory;
import net.lop223.factory.audio_platform.WindowsAudioPlatformFactory;
import net.lop223.factory.metadata_reader.AudioMetadataReaderFactory;
import net.lop223.factory.metadata_reader.FlacMetadataReaderFactory;
import net.lop223.proxy.LazyMediaPlayerEngineProxy;
import net.lop223.utils.AudioDevice;
import net.lop223.utils.player_engine.IMediaPlayerEngine;
import net.lop223.utils.IMetadataReader;
import net.lop223.utils.IPlaylistRepository;

import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("===================================================================");
        System.out.println("     ІНТЕГРОВАНА СИСТЕМА АУДІОПЛЕЄРА (12 DESIGN PATTERNS)          ");
        System.out.println("===================================================================\n");

        // 1. Singleton: Налаштування плеєра
        AudioPlayerConfig config = AudioPlayerConfig.getInstance();
        config.setBufferSize(8192);
        config.setMaxVolumeLimit(0.9f);
        System.out.println("1. [Singleton] Конфігурація застосунку:\n   " + config + "\n");

        // 2. Abstract Factory: Ініціалізація екосистеми платформи
        System.out.println("2. [Abstract Factory] Ініціалізація компонентів для ОС Windows:");
        AudioPlatformFactory platformFactory = new WindowsAudioPlatformFactory();

        // [Proxy + Decorator + Bridge + Adapter] Backend and engine are created on first use;
        // the decorator then enforces the configured volume limit.
        IMediaPlayerEngine audioEngine = new VolumeLimitMediaPlayerDecorator(
                new LazyMediaPlayerEngineProxy(() ->
                        new SystemMediaPlayerEngine(
                                new NativeAudioServiceAdapter(new WindowsNativeAudioService()))),
                config.getMaxVolumeLimit());
        IMetadataReader defaultMetadataReader = platformFactory.createMetadataReader();

        IPlaylistRepository repository = new InMemoryPlaylistRepository();
        PlaybackQueueService queueService = new PlaybackQueueService(audioEngine);
        PlayerController controller = new PlayerController(queueService, audioEngine, defaultMetadataReader, repository);

        // 3. Створення користувача та плейліста
        User user = new User("usr_101", "Maks");
        Playlist rockPlaylist = user.createPlaylist("Rock Classics");

        // 4. Factory Method: Зчитування MP3 та FLAC треків
        System.out.println("\n3. [Factory Method] Імпорт файлів за допомогою фабричних зчитувачів:");
        Track firstMp3Track = controller.importFileToPlaylist("C:/Music/ACDC_Highway_To_Hell.mp3", rockPlaylist);
        Track secondMp3Track = controller.importFileToPlaylist("C:/Music/ACDC_Back_In_Black.mp3", rockPlaylist);
        System.out.println("   [Flyweight] MP3 tracks share their immutable artist/album/genre metadata: "
                + (firstMp3Track.getMetadata() == secondMp3Track.getMetadata()));

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

        // 7. Composite: playlist folders and nested folders expose the same aggregate operations.
        System.out.println("\n6. [Composite] Групування плейлистів у бібліотеці:");
        PlaylistFolder favorites = new PlaylistFolder("Favorites");
        PlaylistFolder library = new PlaylistFolder("Music Library");
        favorites.add(rockPlaylist);
        library.add(favorites);
        library.add(workoutPlaylist);
        System.out.printf("   Плейлистів/папок у бібліотеці: %d, треків у дереві: %d, загальна тривалість: %s\n",
                library.getItems().size(), library.getTracks().size(), library.calculateTotalDuration());

        // 8. Відтворення та робота Object Pool у рушії
        System.out.println("\n7. [Object Pool] Відтворення плейліста через аудіорушій (Pool з'єднань):");
        controller.onSelectPlaylist(rockPlaylist);
        controller.onPlayClicked();   // Відтворює 1-й трек (захоплює Conn_#1)
        controller.onNextClicked();   // Перемикає на 2-й трек (перевикористовує підключення)

        // Перемикання пристрою та гучності
        AudioDevice bluetooth = audioEngine.getAvailableDevices().stream()
                .filter(d -> d.name().contains("Bluetooth"))
                .findFirst()
                .orElse(audioEngine.getCurrentDevice());

        controller.onDeviceSelected(bluetooth);
        controller.onVolumeChanged(0.95f);
        controller.onPauseClicked();

        System.out.println("\n===================================================================");
        System.out.println("           ВСІ КОМПОНЕНТИ ТА ПАТЕРНИ ПРАЦЮЮТЬ ЗЛАГОДЖЕНО!           ");
        System.out.println("===================================================================");
    }
}