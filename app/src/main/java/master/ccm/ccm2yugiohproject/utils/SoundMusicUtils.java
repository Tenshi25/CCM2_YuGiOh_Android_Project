package master.ccm.ccm2yugiohproject.utils;

import android.media.MediaPlayer;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import master.ccm.ccm2yugiohproject.R;

public class SoundMusicUtils {
    private static MediaPlayer mediaPlayer = null;
    private static int playedLoopingSoundOrMusic = R.raw.life_point_sound_effect;
    private static final double MAXVOLUME = 1.0;

    public SoundMusicUtils() {}

    public static void launchSoundMusic(AppCompatActivity appCompatActivity, int recording, boolean playedInALoop, double currentVolume) {
        if (playedLoopingSoundOrMusic != recording) {
            Log.v("LaunchSoundMusic", "lancement de la musique");

            playedLoopingSoundOrMusic = recording;
            MediaPlayer mp = MediaPlayer.create(appCompatActivity, recording);

            mp.setVolume((float) currentVolume, (float) currentVolume);

            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }

            if (playedInALoop) {
                mp.setLooping(true);
            }

            mediaPlayer = mp;

            Log.v("LaunchSoundMusic", "Playing sound...");
            mp.start();
        }
    }

    public static void stopMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

    public static void launchSoundEffect(AppCompatActivity appCompatActivity, int recording) {
        Log.v("LaunchSoundMusic", "lancement d'un effet sonore");
        MediaPlayer mp = MediaPlayer.create(appCompatActivity, recording);
        mp.start();
    }
}
