package sendbox.demafayz.com.demafayzsendbox.features.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by demafayz on 15.09.16.
 */
public class TestData {

    @SerializedName("post_name")
    private String postName;

    @SerializedName("auther")
    private String auther;

    @SerializedName("date")
    private String date;

    @SerializedName("description")
    private String description;

    public TestData(String postName, String auther, String date, String description) {
        this.postName = postName;
        this.auther = auther;
        this.date = date;
        this.description = description;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
