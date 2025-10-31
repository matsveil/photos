package com.matsvei.photosapp.albumView;

import com.matsvei.photosapp.Tag;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Photo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String filePath;
    private LocalDateTime dateTaken;
    private List<Tag> tags;

    public Photo(String name, String filePath, LocalDateTime dateTaken) {
        this.name = name;
        this.filePath = filePath;
        this.dateTaken = getModifiedDate();
        this.tags = new ArrayList<>();
    }

    private LocalDateTime getModifiedDate() {
        File file = new File(filePath);
        return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(file.lastModified()),
                ZoneId.systemDefault()
        );
    }

    public LocalDateTime getDateTaken() {
        return dateTaken;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
