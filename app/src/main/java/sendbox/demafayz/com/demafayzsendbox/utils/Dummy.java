package sendbox.demafayz.com.demafayzsendbox.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by demafayz on 01.09.16.
 */
public class Dummy {
    public static List<String> populateFeaturesItems() {
        List<String> items = new ArrayList<>();
        items.add("Yandex Money");
        items.add("VKSDK Photo");
        items.add("RxAndroid Test");
        items.add("Gson Test");
        return items;
    }

    public static String populateTestJSONObject() {
        String json = "[\n" +
                "   {\n" +
                "      \"date\":\"11/8/2014\",\n" +
                "      \"auther\":\"nirav kalola 0\",\n" +
                "      \"description\":\"json object parsing using gson library is easy\",\n" +
                "      \"post_name\":\"json object parsing\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"date\":\"12/8/2014\",\n" +
                "      \"auther\":\"nirav kalola 1\",\n" +
                "      \"description\":\"json array parsing using gson library\",\n" +
                "      \"post_name\":\"json array parsing\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"date\":\"17/8/2014\",\n" +
                "      \"auther\":\"nirav kalola 2\",\n" +
                "      \"description\":\"store json file in assets folder and get data when required\",\n" +
                "      \"post_name\":\"json parsing from assets folder\"\n" +
                "   }\n" +
                "]";
        return json;
    }
}
