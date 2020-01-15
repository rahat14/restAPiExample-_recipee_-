package com.metacodersbd.restapiexampleclass.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class recipe {

   // String  id , healthScore , title , readyInMinutes , servings , image , instructions  ;

    @Expose
    @SerializedName("id")  private  int  id  ;
    @Expose
    @SerializedName("healthScore")  private  float  healthScore  ;
    @Expose
    @SerializedName("title")  private  String  title  ;
    @Expose
    @SerializedName("readyInMinutes")  private  int  readyInMinutes  ;
    @Expose
    @SerializedName("servings")  private  int  servings  ;
    @Expose
    @SerializedName("image")  private  String  image ;
    @Expose
    @SerializedName("instructions")  private  String  instructions ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(float healthScore) {
        this.healthScore = healthScore;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
