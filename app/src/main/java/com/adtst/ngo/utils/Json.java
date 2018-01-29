package com.adtst.ngo.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lcom151-one on 1/19/2018.
 */

public class Json {
    public JSONObject getJeson() throws JSONException {
        JSONObject object = new JSONObject("{\"HotelList\":[\n" +
                "  {\n" +
                "    \"hotel_name\":\"Purohit\",\n" +
                "    \"food_info\":\"Pizza | Gujarati\",\n" +
                "    \"star_info\":\"118 Piccadilly\",\n" +
                "    \"address\":\"sahara darvaja, Surat\",\n" +
                "    \"image\":2131165287,\n" +
                "    \"location\":\"2km away\",\n" +
                "    \"lat\":\"21.170240\"\n," +
                "    \"lng\":\"72.831061\"\n" +
                "  },{\n" +
                "    \"hotel_name\":\"D Pizza\",\n" +
                "    \"food_info\":\"Pizza\",\n" +
                "    \"star_info\":\"118 Piccadilly\",\n" +
                "    \"address\":\"Adajan, Songadh\",\n" +
                "    \"image\":2131165288,\n" +
                "    \"location\":\"3km away\",\n" +
                "    \"lat\":\"21.166359\",\n" +
                "    \"lng\":\"73.564505\"\n" +
                "  },{\n" +
                "    \"hotel_name\":\"S Pizza\",\n" +
                "    \"food_info\":\"Italian Food\",\n" +
                "    \"star_info\":\"118 Piccadilly\",\n" +
                "    \"address\":\"Adajan, Baroda\",\n" +
                "    \"image\":2131165289,\n" +
                "    \"location\":\"5km away\",\n" +
                "    \"lat\":\"22.307159\",\n" +
                "    \"lng\":\"73.181219\"\n" +
                "  }\n" +
                "]}");
        return object;
    }

    public JSONObject getHotelMenu() throws JSONException {
        JSONObject object = new JSONObject("{\n" +
                "  \"menu\":[\n" +
                "    {\n" +
                "      \"item_name\":\"Panipuri\",\n" +
                "      \"price\":\"120\",\n" +
                "      \"image\":\"2131165300\"\n" +
                "    },{\n" +
                "      \"item_name\":\"Bhel\",\n" +
                "      \"price\":\"80\",\n" +
                "      \"image\":\"2131165301\"\n" +
                "    },{\n" +
                "      \"item_name\":\"Dhosa\",\n" +
                "      \"price\":\"280\",\n" +
                "      \"image\":\"2131165302\"\n" +
                "    },{\n" +
                "      \"item_name\":\"Rice\",\n" +
                "      \"price\":\"60\",\n" +
                "      \"image\":\"2131165300\"\n" +
                "    },{\n" +
                "      \"item_name\":\"Roti\",\n" +
                "      \"price\":\"50\",\n" +
                "      \"image\":\"2131165301\"\n" +
                "    },{\n" +
                "      \"item_name\":\"Sabji\",\n" +
                "      \"price\":\"120\",\n" +
                "      \"image\":\"2131165302\"\n" +
                "    }\n" +
                "  ]\n" +
                "}");
        return object;
    }
}
