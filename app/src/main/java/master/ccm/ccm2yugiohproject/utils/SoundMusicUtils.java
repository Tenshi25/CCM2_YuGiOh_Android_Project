package master.ccm.ccm2yugiohproject.utils;

import android.media.MediaPlayer;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import master.ccm.ccm2yugiohproject.R;

public class SoundMusicUtils {
    private static MediaPlayer mediaPlayer = null;
    private static int playedLoopingSoundOrMusic = R.raw.life_point_sound_effect;

    public SoundMusicUtils() {}

    public static void LaunchSoundMusic(AppCompatActivity appCompatActivity, int recording, boolean playedInALoop) {
        if (playedLoopingSoundOrMusic != recording) {
            Log.v("LaunchSoundMusic", "lancement de la musique");

            playedLoopingSoundOrMusic = recording;
            MediaPlayer mp = MediaPlayer.create(appCompatActivity, recording);

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
}
