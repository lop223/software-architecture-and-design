package net.lop223.utils.player_engine;

/**
 * [GRASP: Protected Variations, Polymorphism]
 * Інтерфейс медіа-рушія. Захищає ядро програми від змін у конкретних API аудіо.
 * [SOLID: ISP (Interface Segregation)]
 * Розбито товстий інтерфейс `IMediaPlayerEngine на інтерфейси по відповідальності.
 */
public interface IMediaPlayerEngine extends IAudioPlaybackControl, IAudioVolumeControl, IAudioDeviceManager
{}