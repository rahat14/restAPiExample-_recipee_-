package com.metacodersbd.restapiexampleclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;
//TODO
// Search with query
// Single recipee VIEW ....
//

import com.metacodersbd.restapiexampleclass.model.recipe;
import com.metacodersbd.restapiexampleclass.responseModel.RecipeResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    adapter recyclerVierAdapter ;
    List<recipe> recipeList ;
    adapter.ItemClickListenter itemClickListenter ;
    RecyclerView recyclerView;
    LinearLayoutManager llm ;
    Retrofit retrofit ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.listview);
        llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

        getData();
    }

    public  void getData()
    {
        // retrofit initlize

        retrofit = new Retrofit.Builder()
                .baseUrl(constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // call api
        api  apiInterface = retrofit.create(api.class) ;

        Call<RecipeResponse> call = apiInterface.getRecipee("20" , constants.key) ;

        call.enqueue(
                new Callback<RecipeResponse>() {
                    @Override
                    public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response)
                    {


                      //  Log.d("Response : "  ,  response.body().toString()  ) ;

                        List<recipe> recipes = new ArrayList<>(response.body().getRecipeList());


                        recyclerVierAdapter = new adapter(recipes , MainActivity.this  ,itemClickListenter )  ;
                        recyclerView.setAdapter(recyclerVierAdapter) ;
                        recyclerView.setLayoutManager(llm) ;

                    }

                    @Override
                    public void onFailure(Call<RecipeResponse> call, Throwable t) {

                        Toast.makeText(getApplicationContext() , "Error Code : " + call + "\n Msg : " + t.getMessage() , Toast.LENGTH_LONG)
                                .show();

                        Log.d("ERROR TEXT : "  ,  call + "Mgs :" + t.getMessage().toString() ) ;

                    }
                }
        ) ;





    }




}
