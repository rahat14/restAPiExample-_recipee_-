package com.metacodersbd.restapiexampleclass;

import com.metacodersbd.restapiexampleclass.responseModel.RecipeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface api {

         //   String URL = "https://api.spoonacular.com/recipes/random?number=1&apiKey=543f1d385fc24c9f8bfcfd05420c2fbf/" ;

            @GET("recipes/random")
                Call<RecipeResponse>getRecipee(
                    @Query("number")String num ,
                    @Query("apiKey") String key
            ) ;








}
