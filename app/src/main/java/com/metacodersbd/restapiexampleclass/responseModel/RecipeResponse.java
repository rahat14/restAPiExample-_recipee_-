package com.metacodersbd.restapiexampleclass.responseModel;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.metacodersbd.restapiexampleclass.model.recipe;

import java.util.List;

public class RecipeResponse {
    @SerializedName("recipes")
    @Expose
    private List<recipe> recipeList   ;

    public  List<recipe> getRecipeList(){

        return  recipeList ;

    }


    @NonNull
    @Override
    public String toString() {
        return  "Response :" + recipeList;
    }
}
