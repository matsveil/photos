package com.matsvei.photosapp;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Album implements Serializable {
    private final static long serialVersionUID = 1L;

    private String name;
    private List<Photo> photos = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Album(String name) {
        this.name = name;
    }

    public void addPhoto(Photo photo) {
        if (!photos.contains(photo)) {
            photos.add(photo);
        } else {
            System.out.println("Photo already exists in album: " + photo.getFilePath());
        }

        System.out.println("Photo added successfully: " + photo.getFilePath());
    }

    public void removePhoto(Photo photo) {
        photos.remove(photo);
        System.out.println("Photo removed successfully");
    }
}
