package com.personal.FileSharing.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String filename;
    private String filetype;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public Files() {
    }

    public Files(int id, String filename, String filetype) {
        this.id = id;
        this.filename = filename;
        this.filetype = filetype;
    }
}
