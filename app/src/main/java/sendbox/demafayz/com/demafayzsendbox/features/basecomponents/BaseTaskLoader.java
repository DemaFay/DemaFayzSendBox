package sendbox.demafayz.com.demafayzsendbox.features.basecomponents;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by demafayz on 02.09.16.
 */
public class BaseTaskLoader extends AsyncTaskLoader<Void> {

    private BackgroundListener backgroundListener;


    public BaseTaskLoader(Context context) {
        super(context);
    }

    @Override
    public Void loadInBackground() {
        if (backgroundListener != null) {
            backgroundListener.backgroundListener(getContext());
        }
        return null;
    }

    public void setBackgroundListener(BackgroundListener backgroundListener) {
        this.backgroundListener = backgroundListener;
    }

    public interface BackgroundListener {
        public void backgroundListener(Context context);
    }

}
