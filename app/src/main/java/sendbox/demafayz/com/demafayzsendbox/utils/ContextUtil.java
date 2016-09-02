package sendbox.demafayz.com.demafayzsendbox.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import sendbox.demafayz.com.demafayzsendbox.R;
import sendbox.demafayz.com.demafayzsendbox.features.maincomtext.MainActivity;

/**
 * Created by demafayz on 01.09.16.
 */
public class ContextUtil {

    public static void showFragment(FragmentActivity activity, Fragment fragment, boolean useBackStack) {

        if (activity instanceof MainActivity) {
            FragmentManager fm = activity.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.flFragmentContainer, fragment);
            if (useBackStack)
                ft.addToBackStack(fragment.getClass().getSimpleName());
            ft.commit();
        }
    }
}
