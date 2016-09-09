package sendbox.demafayz.com.demafayzsendbox.network.vksdk;

import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import java.util.List;

import sendbox.demafayz.com.demafayzsendbox.features.vksdk.VKPhotoItemData;
import sendbox.demafayz.com.demafayzsendbox.utils.vksdk.VKJsonParser;

/**
 * Created by demafayz on 07.09.16.
 */
public class VkPhoto {

    public static void populatePhotos(final OnVkPhotoResult onVkPhotoResult, String ownerId, final List<String> photoList) {

        VKRequest request = new VKRequest("photos.getAll", VKParameters.from(VKApiConst.OWNER_ID, ownerId, VKApiConst.OFFSET, photoList.size()));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                List<VKPhotoItemData> photoItemDatas = VKJsonParser.populatePhotoList(response);

                for (VKPhotoItemData itemData : photoItemDatas) {
                    photoList.add(itemData.getPhotoItems().get(0).getUrl());
                }
                onVkPhotoResult.onSuccess(photoList);
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                onVkPhotoResult.onError(error);
            }
        });
    }

    public interface OnVkPhotoResult {
        public void onSuccess(List<String> photos);
        public void onError(VKError error);
    }
}
