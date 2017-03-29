package com.enid.materialtest.model;

import java.io.Serializable;

/**
 * Created by enid on 2017/1/3.
 */

public class FruitModel implements Serializable{
    private int id;
    private String fruitName;
    private int fruitImageId;

    public FruitModel(String fruitName, int fruitImageId) {
        this.fruitName = fruitName;
        this.fruitImageId = fruitImageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public int getFruitImageId() {
        return fruitImageId;
    }

    public void setFruitImageId(int fruitImageId) {
        this.fruitImageId = fruitImageId;
    }
}
