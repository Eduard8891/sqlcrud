package org.example.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.Label;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LabelsParser {
    private static final Gson gson = new Gson();

    public static String toJson(List<Label> labels) {
        return gson.toJson(labels);
    }

    public static List<Label> toList(String json) {
        Type listOfMyClassObject = new TypeToken<ArrayList<Label>>() {
        }.getType();
        return gson.fromJson(json, listOfMyClassObject);
    }
}
