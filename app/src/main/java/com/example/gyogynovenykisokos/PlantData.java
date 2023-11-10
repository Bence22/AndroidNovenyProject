package com.example.gyogynovenykisokos;

import java.io.Serializable;
import java.util.List;


public class PlantData implements Serializable {

    private int id;
    private String common_name;
    private List<String> scientific_name;
    private List<String> other_name;
    private String cycle;
    private String watering;
    private List<String> sunlight;
    private ImageData default_image;
    private ImageData thumbnail_image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommon_name() {
        return common_name;
    }

    public void setCommon_name(String common_name) {
        this.common_name = common_name;
    }

    public List<String> getScientific_name() {
        return scientific_name;
    }

    public void setScientific_name(List<String> scientific_name) {
        this.scientific_name = scientific_name;
    }

    public List<String> getOther_name() {
        return other_name;
    }

    public void setOther_name(List<String> other_name) {
        this.other_name = other_name;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getWatering() {
        return watering;
    }

    public void setWatering(String watering) {
        this.watering = watering;
    }

    public List<String> getSunlight() {
        return sunlight;
    }

    public void setSunlight(List<String> sunlight) {
        this.sunlight = sunlight;
    }

    public ImageData getDefault_image() {
        return default_image;
    }

    public void setDefault_image(ImageData default_image) {
        this.default_image = default_image;
    }

    public ImageData getThumbnail_image() {
        return thumbnail_image;
    }

    public void setThumbnail_image(ImageData thumbnail_image) {
        this.thumbnail_image = thumbnail_image;
    }
}
