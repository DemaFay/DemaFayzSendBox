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
        String json = "{\n" +
                "  \"Orin\": {\n" +
                "    \"age\": 90,\n" +
                "    \"facialHair\": \"black beard and mustache\",\n" +
                "    \"weapons\": [\n" +
                "      {\n" +
                "        \"name\": \"Slasher\",\n" +
                "        \"origin\": \"Gondolin\",\n" +
                "        \"type\": \"sword\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Oaken Shield\",\n" +
                "        \"origin\": \"Moria\",\n" +
                "        \"type\": \"shield\"\n" +
                "      },\n" +
                "      \"dagger\"\n" +
                "    ]\n" +
                "  },\n" +
                "  \"Kori\": {\n" +
                "    \"age\": 60,\n" +
                "    \"facialHair\": \"red mustache\",\n" +
                "    \"weapons\": [\n" +
                "      \"mace\",\n" +
                "      \"bow\"\n" +
                "    ]\n" +
                "  },\n" +
                "  \"Billy Bob\": {\n" +
                "    \"age\": 45,\n" +
                "    \"facialHair\": \"is he really a dwarf?\",\n" +
                "    \"weapons\": []\n" +
                "  }\n" +
                "}";
        return json;
    }
}
