package com.yesipov.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Clob;

@Entity
@Table(name = "yesipov")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    private long id;

    @Column(columnDefinition="TEXT")
    private String imageBase64;

    @Expose
    private String descript;

    public Image() {
    }

    public Image(String imageBase64, String descript) {
        this.imageBase64 = imageBase64;
        this.descript = descript;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        return gson.toJson(this);
    }
}
