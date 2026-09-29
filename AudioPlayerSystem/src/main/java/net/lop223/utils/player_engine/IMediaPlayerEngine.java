package net.lop223.utils.player_engine;

import net.lop223.utils.AudioDevice;
import net.lop223.utils.PlaybackState;

import java.time.Duration;
import java.util.List;

/**
 * [GRASP: Protected Variations, Polymorphism]
 * Інтерфейс медіа-рушія. Захищає ядро програми від змін у конкретних API аудіо.
 * [SOLID: ISP (Interface Segregation)]
 * Розбито товстий інтерфейс `IMediaPlayerEngine на інтерфейси по відповідальності.
 */
public interface IMediaPlayerEngine extends IAudioPlaybackControl, IAudioVolumeControl, IAudioDeviceManager
{}