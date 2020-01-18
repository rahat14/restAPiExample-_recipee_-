package com.metacodersbd.restapiexampleclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;
//TODO
// Search with query
// Single recipee VIEW ....
//

import com.facebook.shimmer.ShimmerFrameLayout;
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
    SearchView searchView ;
    Retrofit retrofit ;
    private ShimmerFrameLayout mShimmerViewContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.listview);
        searchView = findViewById(R.id.search_bar) ;

        llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);


        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);





        getData();
    }


    public  void getData() {
        // retrofit initlize

        retrofit = new Retrofit.Builder()
                .baseUrl(constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // call api
        api  apiInterface = retrofit.create(api.class) ;

        Call<RecipeResponse> call = apiInterface.getRecipee("15" , constants.key) ;

        call.enqueue(
                new Callback<RecipeResponse>() {
                    @Override
                    public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response)
                    {


                      //  Log.d("Response : "  ,  response.body().toString()  ) ;
                    if(response.isSuccessful())
                    {
                        if(response.code()==200){
                            List<recipe> recipes = new ArrayList<>(response.body().getRecipeList());


                            recyclerVierAdapter = new adapter(recipes , MainActivity.this  ,itemClickListenter  )  ;
                            recyclerView.setAdapter(recyclerVierAdapter) ;

                            recyclerView.setLayoutManager(llm) ;
                        }
                        mShimmerViewContainer.stopShimmerAnimation();
                        mShimmerViewContainer.setVisibility(View.GONE);
                    }




                    }

                    @Override
                    public void onFailure(Call<RecipeResponse> call, Throwable t) {

                        Toast.makeText(getApplicationContext() , "Error Code : " + call + "\n Msg : " + t.getMessage() , Toast.LENGTH_LONG)
                                .show();

                        Log.d("ERROR TEXT : "  ,  call + "Mgs :" + t.getMessage() ) ;

                    }
                }
        ) ;





    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    protected void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }


}
