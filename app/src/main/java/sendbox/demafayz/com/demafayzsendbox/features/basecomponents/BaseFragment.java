package sendbox.demafayz.com.demafayzsendbox.features.basecomponents;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;

/**
 * Created by demafayz on 01.09.16.
 */
public abstract class BaseFragment extends Fragment implements LoaderManager.LoaderCallbacks<Void>, BaseTaskLoader.BackgroundListener {

    private static final int BASE_LOADER_ID = 101;
    private LoaderManager manager;
    private Loader<Void> loader;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String title = populateFragmentTitle();
        if (title != null) {
            getActivity().setTitle(title);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        manager = getLoaderManager();
        loader = manager.restartLoader(BASE_LOADER_ID, null, this);
        loader.forceLoad();
    }

    protected String populateFragmentTitle() {
        return null;
    }

    @Override
    public Loader<Void> onCreateLoader(int id, Bundle args) {
        onPreExecute();
        BaseTaskLoader loader = new BaseTaskLoader(getContext());
        loader.setBackgroundListener(this);
        return loader;
    }

    @Override
    public void backgroundListener(Context context) {
        doInBackground(context);
    }

    @Override
    public void onLoadFinished(Loader<Void> loader, Void data) {
        onPostExecute();
    }

    @Override
    public void onLoaderReset(Loader<Void> loader) {

    }

    protected abstract void onPreExecute();

    protected abstract void doInBackground(Context context);

    protected abstract void onPostExecute();
}
