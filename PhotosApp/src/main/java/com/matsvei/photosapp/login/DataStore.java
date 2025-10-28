package com.matsvei.photosapp.login;

import com.matsvei.photosapp.home.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DataStore {
    private static final String DATA_FILE = "data/users.dat";

    private static Map<String, User> users = new HashMap<>();

    public static void load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            users = (Map<String, User>) in.readObject();
        } catch (FileNotFoundException e) {
            users = new HashMap<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            out.writeObject(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User getUser(String username) {
        return users.get(username.toLowerCase());
    }

    public static void addUser(User user) {
        users.put(user.getUsername().toLowerCase(), user);
        save();
    }

    public static Map<String, User> getAllUsers() {
        return users;
    }
}

