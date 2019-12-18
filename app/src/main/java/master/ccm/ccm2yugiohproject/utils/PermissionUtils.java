package master.ccm.ccm2yugiohproject.utils;

import android.Manifest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

/**
 * Classe de notre utilitaire pour les permissions
 */
public class PermissionUtils {

    /**
     * Constructeur par défaut
     */
    public PermissionUtils() {
    }

    /**
     * Demander l'ensemble des permissions
     */
    public static void askAllPermission(AppCompatActivity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{
                        Manifest.permission.INTERNET
                },
                123);
    }
}
