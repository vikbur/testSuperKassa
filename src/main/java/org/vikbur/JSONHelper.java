package org.vikbur;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class JSONHelper {
    public static String getJSONArray() {
        return "[" +
                "[\"a1\",\"a2\",\"a3\",\"a4\"]," +
                "[\"b1\",null,null,\"b4\"]," +
                "[null,\"c2\",\"c3\",null]," +
                "[\"d1\",null,null,\"d4\"]," +
                "[null,\"e2\",\"e3\",null]," +
                "[null,\"f2\",\"f3\",\"f4\"]," +
                "[\"h1\",null,null,null]," +
                "[\"g1\",null,null,null]" +
                "]";
    }

    public static String getJSONArray2() {
        return "[" +
                "[\"a1\",null,null,null]," +
                "[null,\"b2\",null,\"b4\"]," +
                "[null,null,\"c3\",null]," +
                "]";
    }

    public static List<Row> parseJSONArray(JSONArray jsonArray) {
        List<Row> array = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++){

            JSONArray subArray = jsonArray.getJSONArray(i);
            List<String> elements = new ArrayList<>();
            int mask = 0;

            for (int j = 0; j < subArray.length(); j++){
                String el = subArray.optString(j);
                if (el.isEmpty()){
                    elements.add(null);
                } else {
                    mask += 1 << j;
                    elements.add(el);
                }
            }

            array.add(new Row(elements, mask));
        }

        return array;
    }
}
