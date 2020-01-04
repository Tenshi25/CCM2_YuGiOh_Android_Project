package master.ccm.ccm2yugiohproject.utils;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import master.ccm.ccm2yugiohproject.ConfStartDuel_activity;
import master.ccm.ccm2yugiohproject.Home;
import master.ccm.ccm2yugiohproject.MenuDeckList_Activity;
import master.ccm.ccm2yugiohproject.Profile_activity;
import master.ccm.ccm2yugiohproject.R;

public class NavigationMenuUtils {

    public NavigationMenuUtils() {

    }

    public static void onClickHome(AppCompatActivity activity){
        Intent intent = new Intent(activity, Home.class);
        activity.startActivity(intent);
        SoundMusicUtils.launchSoundMusic(activity, R.raw.seto_kaiba_hacker_theme, true, 0.5);
        activity.finish();
    }

    public static void onClickDuel(AppCompatActivity activity){
        Intent intent = new Intent(activity, ConfStartDuel_activity.class);
        activity.startActivity(intent);
        SoundMusicUtils.launchSoundMusic(activity, R.raw.yugioh_opening, true, 0.5);
        activity.finish();
    }
    public static void onClickToDeckList(AppCompatActivity activity){
        Intent intent = new Intent(activity, MenuDeckList_Activity.class);
        activity.startActivity(intent);
        SoundMusicUtils.launchSoundMusic(activity, R.raw.yugioh_gx_spirit_caller_deck_construction, true, 0.5);
        activity.finish();
    }

    public static void onClickProfile(AppCompatActivity activity){
        Intent intent = new Intent(activity, Profile_activity.class);
        activity.startActivity(intent);
        SoundMusicUtils.launchSoundMusic(activity, R.raw.seto_kaiba_hacker_theme, true, 0.5);
        activity.finish();
    }
}
