package com.matsvei.photosapp.albums;

import com.matsvei.photosapp.Photo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Optional;

public class Album implements Serializable {
    private final static long serialVersionUID = 1L;

    private String name;
    private List<Photo> photos = new ArrayList<>();


    public List<Photo> getPhotos() {
        return photos;
    }


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

    public int getPhotoCount() {
        return photos.size();
    }

    public String getDateRange() {
        if (photos.isEmpty()) {
            return "N/A";
        }

        // Find the earliest and latest photo dates
        Optional<LocalDateTime> minDate = photos.stream()
                .map(Photo::getDateTaken)
                .min(Comparator.naturalOrder());

        Optional<LocalDateTime> maxDate = photos.stream()
                .map(Photo::getDateTaken)
                .max(Comparator.naturalOrder());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        // Format the date range string
        if (minDate.isPresent() && maxDate.isPresent()) {
            if (minDate.get().toLocalDate().equals(maxDate.get().toLocalDate())) {
                return minDate.get().format(formatter); // Only one date
            }
            return minDate.get().format(formatter) + " - " + maxDate.get().format(formatter);
        }

        return "N/A";
    }

    @Override
    public String toString() {
        // This will be displayed in the ListView
        // Example: "Vacation (15 photos) [01/01/2023 - 01/10/2023]"
        return String.format("%s (%d photos) [%s]",
                name,
                getPhotoCount(),
                getDateRange()
        );
    }
}
