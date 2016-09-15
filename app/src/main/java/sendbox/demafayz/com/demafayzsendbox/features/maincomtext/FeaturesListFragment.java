package sendbox.demafayz.com.demafayzsendbox.features.maincomtext;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sendbox.demafayz.com.demafayzsendbox.R;
import sendbox.demafayz.com.demafayzsendbox.features.basecomponents.BaseFragment;
import sendbox.demafayz.com.demafayzsendbox.features.rxandroid.RxTestFragment;
import sendbox.demafayz.com.demafayzsendbox.features.vksdk.VKSDKActivity;
import sendbox.demafayz.com.demafayzsendbox.features.yandexmoney.CardFragment;
import sendbox.demafayz.com.demafayzsendbox.utils.ContextUtil;
import sendbox.demafayz.com.demafayzsendbox.utils.Dummy;

/**
 * Created by demafayz on 01.09.16.
 */
public class FeaturesListFragment extends BaseFragment implements FeaturesListAdapter.OnRecyclerItemClickListener {

    private List<String> items;
    private ViewHolder vh;
    private FeaturesListAdapter adapter;

    private class ViewHolder {
        public RecyclerView rvFeaturesList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_features_list, container, false);
        return layout;
    }

    private void initData() {
        items = Dummy.populateFeaturesItems();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        populateViewHolder(view);
        super.onViewCreated(view, savedInstanceState);
    }

    private void createNewAdapter() {
        adapter = new FeaturesListAdapter(items);
        adapter.setOnRecyclerItemClickListener(this);
        vh.rvFeaturesList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        vh.rvFeaturesList.setAdapter(adapter);
    }

    @Override
    public void onRecyclerItemClickListener(View view) {
        int position = vh.rvFeaturesList.getChildAdapterPosition(view);
        openFeature(position);
    }

    private void openFeature(int position) {
        switch (position) {
            case 0:
                ContextUtil.showFragment(getActivity(), CardFragment.newInstance(), true);
                break;
            case 1:
                ContextUtil.showActivity(getContext(), VKSDKActivity.class);
                break;
            case 2:
                ContextUtil.showFragment(getActivity(), RxTestFragment.newInstance(), true);
                break;
        }
    }

    private void populateViewHolder(View view) {
        vh = new ViewHolder();
        vh.rvFeaturesList = (RecyclerView) view.findViewById(R.id.rvFeaturesList);
    }

    @Override
    protected String populateFragmentTitle() {
        return getString(R.string.features_list_fragment__title);
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void doInBackground(Context context) {
        initData();
    }

    @Override
    protected void onPostExecute() {
        createNewAdapter();
    }

    public static FeaturesListFragment newInstance() {

        Bundle args = new Bundle();
        FeaturesListFragment fragment = new FeaturesListFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
