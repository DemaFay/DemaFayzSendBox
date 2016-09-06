package sendbox.demafayz.com.demafayzsendbox.app;

import android.app.Application;

import com.vk.sdk.VKSdk;

import sendbox.demafayz.com.demafayzsendbox.R;

/**
 * Created by demafayz on 01.09.16.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(getApplicationContext());
    }
}
