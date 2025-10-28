package com.matsvei.photosapp;

import java.time.LocalDateTime;

public class Photo {
    private String name;
    private String filePath;
    private LocalDateTime dateTaken;

    public Photo(String name, String filePath, LocalDateTime dateTaken) {
        this.name = name;
        this.filePath = filePath;
        this.dateTaken = dateTaken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photo)) return false;
        Photo p =  (Photo) o;
        return filePath.equalsIgnoreCase(p.filePath);
    }

    @Override
    public int hashCode() {
        return filePath.toLowerCase().hashCode();
    }
}
