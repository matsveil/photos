package com.matsvei.photosapp.photo;

import java.io.Serializable;

public class Tag implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String value;

    public Tag(String name, String value) {
        if (name == null || name.isEmpty() || value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Tag name and value cannot be empty");
        }
        this.name = name.trim().toLowerCase();
        this.value = value.trim().toLowerCase();
    }

    public String getName() { return name; }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag t = (Tag) o;
        return name.equals(t.name) && value.equals(t.value);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + value.hashCode();
    }

    @Override
    public String toString() {
        return name + "=" + value;
    }
}
