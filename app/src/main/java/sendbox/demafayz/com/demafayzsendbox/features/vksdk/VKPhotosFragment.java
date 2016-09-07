package sendbox.demafayz.com.demafayzsendbox.features.vksdk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sendbox.demafayz.com.demafayzsendbox.R;
import sendbox.demafayz.com.demafayzsendbox.features.basecomponents.BaseFragment;

/**
 * Created by demafayz on 06.09.16.
 */
public class VKPhotosFragment extends BaseFragment implements VKCallback<VKAccessToken> {

    private SocialRecyclerAdapter adapter;
    private String[] vkScope = {VKScope.PHOTOS};

    private ViewHolder vh;
    private VKAccessToken res;
    private List<VKPhotoItemData> photoItemDatas;
    private List<String> path = new ArrayList<>();
    private int currentPage = 1;
    private int queryCount = 20;

    private class ViewHolder {
        public RecyclerView rvPhotos;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_social_photos, container, false);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        populateViewHolder(view);
        populateVKSDK();
        super.onViewCreated(view, savedInstanceState);
    }

    private void populateVKSDK() {
        VKSdk.login(getActivity(), vkScope);
    }

    private void populateViewHolder(View view) {
        vh = new ViewHolder();
        vh.rvPhotos = (RecyclerView) view.findViewById(R.id.rvSocialPhotos);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        VKSdk.onActivityResult(requestCode, resultCode, data, this);
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * @param res - VK access token
     */
    @Override
    public void onResult(VKAccessToken res) {
        this.res = res;
        populatePhotos(res);
    }

    /**
     * @param error - VK auth error
     */
    @Override
    public void onError(VKError error) {

    }

    private void populatePhotos(final VKAccessToken res) {
        String ownerId = res.userId;
        final VKRequest request = new VKRequest("photos.getAll", VKParameters.from(VKApiConst.OWNER_ID, ownerId, VKApiConst.OFFSET, path.size()));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                photoItemDatas = populatePhotoList(response);
                if (currentPage == 1) {
                    path = new ArrayList<String>();
                }
                for (VKPhotoItemData itemData : photoItemDatas) {
                    path.add(itemData.getPhotoItems().get(0).getUrl());
                }
                if (currentPage == 1) {
                    showData();
                } else {
                    updateAdapter();
                }
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
            }

            @Override
            public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
                super.attemptFailed(request, attemptNumber, totalAttempts);
            }
        });
    }

    private void updateAdapter() {
        adapter.update(path);
    }

    private List<VKPhotoItemData> populatePhotoList(VKResponse response) {
        List<VKPhotoItemData> photoItems = new ArrayList<>();
        JSONObject root = response.json;
        try {
            JSONObject responseJson = root.getJSONObject("response");
            JSONArray items = responseJson.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject photoJson = items.getJSONObject(i);
                try {
                    VKPhotoItemData photoItem = populatePhotoItem(photoJson);
                    photoItems.add(photoItem);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return photoItems;
    }

    private VKPhotoItemData populatePhotoItem(JSONObject photoJson) throws JSONException {
        VKPhotoItemData photoItemData = new VKPhotoItemData();
        List<VKPhotoItem> photoItems = new ArrayList<>();
        String text = photoJson.getString("text");
        int width = photoJson.getInt("width");
        int height = photoJson.getInt("height");
        int postId = photoJson.getInt("post_id");
        long date = photoJson.getLong("date");
        long id = photoJson.getLong("id");
        long albumId = photoJson.getLong("album_id");
        long ownerId = photoJson.getLong("owner_id");

        if (photoJson.has("photo_75")) {
            String url75 = photoJson.getString("photo_75");
            VKPhotoItem photoItem75 = new VKPhotoItem();
            photoItem75.setSize(75);
            photoItem75.setUrl(url75);
            photoItems.add(photoItem75);
        }

        if (photoJson.has("photo_130")) {
            String url130 = photoJson.getString("photo_130");
            VKPhotoItem photoItem130 = new VKPhotoItem();
            photoItem130.setSize(130);
            photoItem130.setUrl(url130);
            photoItems.add(photoItem130);
        }

        if (photoJson.has("photo_604")) {
            String url604 = photoJson.getString("photo_604");
            VKPhotoItem photoItem604 = new VKPhotoItem();
            photoItem604.setSize(604);
            photoItem604.setUrl(url604);
            photoItems.add(photoItem604);
        }

        if (photoJson.has("photo_807")) {
            String url807 = photoJson.getString("photo_807");
            VKPhotoItem photoItem807 = new VKPhotoItem();
            photoItem807.setSize(807);
            photoItem807.setUrl(url807);
            photoItems.add(photoItem807);
        }

        if (photoJson.has("photo_1280")) {
            String url1280 = photoJson.getString("photo_1280");
            VKPhotoItem photoItem1280 = new VKPhotoItem();
            photoItem1280.setSize(1280);
            photoItem1280.setUrl(url1280);
            photoItems.add(photoItem1280);
        }

        if (photoJson.has("photo_2560")) {
            String url2560 = photoJson.getString("photo_2560");
            VKPhotoItem photoItem2560 = new VKPhotoItem();
            photoItem2560.setSize(2560);
            photoItem2560.setUrl(url2560);
            photoItems.add(photoItem2560);
        }

        photoItemData.setText(text);
        photoItemData.setWidth(width);
        photoItemData.setHeight(height);
        photoItemData.setPostId(postId);
        photoItemData.setDate(date);
        photoItemData.setAlbumId(albumId);
        photoItemData.setId(id);
        photoItemData.setPhotoItems(photoItems);
        photoItemData.setOwnerId(ownerId);
        return photoItemData;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void doInBackground(Context context) {

    }

    @Override
    protected void onPostExecute() {
        //showData();
    }

    private void showData() {
        createNewAdapter();
    }

    private void createNewAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        vh.rvPhotos.setLayoutManager(manager);
        adapter = new SocialRecyclerAdapter(path);
        vh.rvPhotos.setAdapter(adapter);
        vh.rvPhotos.addOnScrollListener(new EndlessRecyclerOnScrollListener(manager) {
            @Override
            public void onLoadMore(int current_page) {
                if (currentPage != current_page) {
                    currentPage = current_page;
                    populatePhotos(res);
                }
            }
        });
    }

    public static VKPhotosFragment newInstance() {

        Bundle args = new Bundle();

        VKPhotosFragment fragment = new VKPhotosFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
