package com.restAssured.solution.utilities;

import com.google.common.reflect.TypeToken;
import com.google.gson.GsonBuilder;
import com.restAssured.solution.pojos.comments.Comments;
import com.restAssured.solution.pojos.posts.Posts;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.restAssured.solution.pojos.users.User;

public class DataHandler {
    static List<Posts> posts;
    static List<Comments> comments;
    static List<User> users;
    public static List<Posts> postMessageBody(String resourceName){
        try {
            Reader reader = Files.newBufferedReader(Paths.get(resourceName));
            posts = new Gson().fromJson(reader, new TypeToken<List<Posts>>() {}.getType());
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return posts;
    }

    public static List<Comments> commentsMessageBody(String resourceName){
        try {
            Reader reader = Files.newBufferedReader(Paths.get(resourceName));
            comments = new Gson().fromJson(reader, new TypeToken<List<Comments>>() {}.getType());
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return comments;
    }

    public static List<User> usersMessageBody(String resourceName){
        try {
            Reader reader = Files.newBufferedReader(Paths.get(resourceName));
            users = new Gson().fromJson(reader, new TypeToken<List<User>>() {}.getType());
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public static void objectToJson(Object t, String filePath){
        Gson json = new GsonBuilder().setPrettyPrinting().create();
        try {
            Writer writer = Files.newBufferedWriter(Paths.get(filePath));
            json.toJson(t, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("Exception was thrown during serialization");
            e.printStackTrace();
        }
    }
}
