package sendbox.demafayz.com.demafayzsendbox.utils.vksdk;

import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sendbox.demafayz.com.demafayzsendbox.features.vksdk.VKPhotoItem;
import sendbox.demafayz.com.demafayzsendbox.features.vksdk.VKPhotoItemData;

/**
 * Created by demafayz on 07.09.16.
 */
public class VKJsonParser {

    public static List<VKPhotoItemData> populatePhotoList(VKResponse response) {
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

    private static VKPhotoItemData populatePhotoItem(JSONObject photoJson) throws JSONException {
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

}
