package sendbox.demafayz.com.demafayzsendbox.features.gson;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sendbox.demafayz.com.demafayzsendbox.R;
import sendbox.demafayz.com.demafayzsendbox.features.basecomponents.BaseFragment;
import sendbox.demafayz.com.demafayzsendbox.utils.Dummy;

/**
 * Created by demafayz on 15.09.16.
 */
public class GsonTestFragment extends BaseFragment {

    private String jsonData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_gson_test, container, false);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void doInBackground(Context context) {
        jsonData = Dummy.populateTestJSONObject();
    }

    @Override
    protected void onPostExecute() {

    }

    public static GsonTestFragment newInstance() {

        Bundle args = new Bundle();

        GsonTestFragment fragment = new GsonTestFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
