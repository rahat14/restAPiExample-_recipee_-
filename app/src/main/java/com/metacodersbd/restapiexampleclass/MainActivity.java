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
import com.metacodersbd.restapiexampleclass.responseModel.RecipeSearchResponse;

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
  //  SearchView searchView ;
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


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                startSearchRecipee(query);


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

                getData();

                return false;
            }
        });


        getData();
    }

    private void startSearchRecipee(String q ) {

        // implement search

        retrofit = new Retrofit.Builder()
                .baseUrl(constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // call api
        api  apiInterface = retrofit.create(api.class) ;

        Call<RecipeSearchResponse> call = apiInterface.getSearchMenu(q , "15", constants.key) ;

        call.enqueue(
                new Callback<RecipeSearchResponse>() {
                    @Override
                    public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response)
                    {


                        //  Log.d("Response : "  ,  response.body().toString()  ) ;
                        if(response.isSuccessful())
                        {
                            if(response.code()==200){
                                List<recipe> recipes = new ArrayList<>(response.body().getRecipeList());


                                recyclerVierAdapter = new adapter(recipes , MainActivity.this  ,itemClickListenter  , true )  ;
                                recyclerView.setAdapter(recyclerVierAdapter) ;

                                recyclerView.setLayoutManager(llm) ;
                            }
                            mShimmerViewContainer.stopShimmerAnimation();
                            mShimmerViewContainer.setVisibility(View.GONE);
                        }




                    }

                    @Override
                    public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {

                        Toast.makeText(getApplicationContext() , "Error Code : " + call + "\n Msg : " + t.getMessage() , Toast.LENGTH_LONG)
                                .show();

                        Log.d("ERROR TEXT : "  ,  call + "Mgs :" + t.getMessage() ) ;

                    }
                }
        ) ;










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


                            recyclerVierAdapter = new adapter(recipes , MainActivity.this  ,itemClickListenter, false )  ;
                            recyclerView.setAdapter(recyclerVierAdapter) ;

                            recyclerView.setLayoutManager(llm) ;


                            // get some animation going

                            recyclerView.getViewTreeObserver().addOnPreDrawListener(

                                    new ViewTreeObserver.OnPreDrawListener() {
                                        @Override
                                        public boolean onPreDraw() {

                                            recyclerView.getViewTreeObserver().removeOnPreDrawListener( this);
                                            for( int i = 0 ; i<recyclerView.getChildCount() ; i++)
                                            {
                                                View v = recyclerView.getChildAt(i) ;
                                                v.setAlpha(0.0f);
                                                v.animate()
                                                        .alpha(1.0f)
                                                        .setDuration(300)
                                                        .setStartDelay(i*50)
                                                        .start();


                                            }


                                            return true;
                                        }
                                    }

                            );




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
