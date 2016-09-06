package sendbox.demafayz.com.demafayzsendbox.features.vksdk;

import java.util.List;

/**
 * Created by demafayz on 06.09.16.
 */
public class VKPhotoItemData {

    private String text;
    private int width;
    private int height;
    private long postId;
    private long date;
    private long albumId;
    private long id;
    private long ownerId;
    List<VKPhotoItem> photoItems;

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<VKPhotoItem> getPhotoItems() {
        return photoItems;
    }

    public void setPhotoItems(List<VKPhotoItem> photoItems) {
        this.photoItems = photoItems;
    }
}
