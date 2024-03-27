package org.example.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.Post;

import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;

public class PostParser {
    private static final Gson gson = new Gson();

    public static String toJson(List<Post> posts) {
        return gson.toJson(posts);
    }

    public static List<Post> toList(String json) {
        Type listOfMyClassObject = new TypeToken<ArrayList<Post>>() {
        }.getType();
        return gson.fromJson(json, listOfMyClassObject);
    }
}
