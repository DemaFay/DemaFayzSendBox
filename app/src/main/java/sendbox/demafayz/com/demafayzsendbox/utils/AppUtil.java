package sendbox.demafayz.com.demafayzsendbox.utils;

import android.util.Log;

/**
 * Created by demafayz on 02.09.16.
 */
public class AppUtil {
    private static boolean DEBUG = true;

    public static void dLog(Class objectClass, String text) {
        if (DEBUG) Log.d(objectClass.getSimpleName(), text);
    }
}
