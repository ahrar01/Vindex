package com.wedevelopapps.retrofit.View;

import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.wedevelopapps.retrofit.Model.Result;
import com.wedevelopapps.retrofit.Model.info;
import com.wedevelopapps.retrofit.R;
import com.wedevelopapps.retrofit.services.GetCountryDataService;
import com.wedevelopapps.retrofit.services.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Result> results;
    private CountryAdapter countryAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getContries();
    }

    private Object getContries() {

        GetCountryDataService getCountryDataService= RetrofitInstance.getService();
        Call<info> call=getCountryDataService.getResult();

        call.enqueue(new Callback<info>() {
            @Override
            public void onResponse(Call<info> call, Response<info> response) {

                info info=response.body();

                if(info !=null && info.getRestResponse() != null){

                    results=(ArrayList<Result>) info.getRestResponse().getResult();
                    viewData();

                }

            }

            @Override
            public void onFailure(Call<info> call, Throwable t) {

            }
        });




        return results;
    }


    private void viewData() {

        recyclerView = (RecyclerView) findViewById(R.id.rvContriesList);
        countryAdapter = new CountryAdapter(results);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(countryAdapter);


    }
}
