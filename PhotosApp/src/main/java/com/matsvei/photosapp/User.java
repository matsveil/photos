package com.matsvei.photosapp;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;

    private String username;
    private String password;

    private List<Album> albums = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addAlbum(Album album) {
        if (!albums.contains(album)) {
            albums.add(album);
        } else  {
            System.out.println("Album already exists: " + album.getName());
        }

        System.out.println("Successfully added album: " + album.getName());
    }

    public void removeAlbum(Album album) {
        if (albums.contains(album)) {
            albums.remove(album);
        } else   {
            System.out.println("Album does not exist: " + album.getName());
        }

        System.out.println("Successfully removed album: " + album.getName());
    }

}
