package sendbox.demafayz.com.demafayzsendbox.features.gson;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import sendbox.demafayz.com.demafayzsendbox.R;
import sendbox.demafayz.com.demafayzsendbox.features.basecomponents.BaseFragment;
import sendbox.demafayz.com.demafayzsendbox.utils.Dummy;

/**
 * Created by demafayz on 15.09.16.
 */
public class GsonTestFragment extends BaseFragment {

    private String jsonData;
    private List<TestData> testDataList;

    private ViewHolder vh;

    private class ViewHolder {
        public TextView tvGsonResult;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_gson_test, container, false);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        populateViewHolder(view);
        super.onViewCreated(view, savedInstanceState);
    }

    private void populateViewHolder(View layout) {
        vh = new ViewHolder();
        vh.tvGsonResult = (TextView) layout.findViewById(R.id.tvGsonResult);
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void doInBackground(Context context) {
        jsonData = Dummy.populateTestJSONObject();
        Type listType = new TypeToken<ArrayList<TestData>>(){}.getType();
        testDataList = new GsonBuilder().create().fromJson(jsonData, listType);
    }

    @Override
    protected void onPostExecute() {
        showData();
    }

    private void showData() {
        String text = "";
        for (TestData testData : testDataList) {
            text += testData.getAuther() + "\n";
        }
        vh.tvGsonResult.setText(text);
    }

    public static GsonTestFragment newInstance() {

        Bundle args = new Bundle();

        GsonTestFragment fragment = new GsonTestFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
