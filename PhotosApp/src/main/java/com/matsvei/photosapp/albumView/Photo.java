package com.matsvei.photosapp.albumView;

import com.matsvei.photosapp.Tag;
import javafx.scene.Node;

import java.io.File;
import java.io.Serial;
import java.time.Instant;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Photo extends Node implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String filePath;
    private LocalDateTime dateTaken;
    private List<Tag> tags;

    public Photo(String filePath) {
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photo p)) return false;
        return filePath.equalsIgnoreCase(p.filePath);
    }

    @Override
    public int hashCode() {
        return filePath.toLowerCase().hashCode();
    }
}
