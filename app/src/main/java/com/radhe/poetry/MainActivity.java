package com.radhe.poetry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.radhe.poetry.Modelss.GetPoetryResponse;
import com.radhe.poetry.Modelss.Poetry;
import com.radhe.poetry.RRR.ApClient;
import com.radhe.poetry.RRR.ApInterface;
import com.radhe.poetry.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ApInterface apiInterface;
    List<Poetry>poetryList= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Retrofit retrofit= ApClient.getClient();
         apiInterface=retrofit.create(ApInterface.class);


        getData();

    }

    private void setAdapter(List<Poetry>poetryModels){

        PoetryAdapter adapter= new PoetryAdapter(this,poetryModels);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        binding.rvPoetry.setLayoutManager(layoutManager);
        binding.rvPoetry.setAdapter(adapter);

    }

    private void getData(){

        apiInterface.getpoetry().enqueue(new Callback<GetPoetryResponse>() {
            @Override
            public void onResponse(Call<GetPoetryResponse> call, Response<GetPoetryResponse> response) {

                try {

                    if (response!=null){

                        if (response.body().getStatus().equals("1")){

                            setAdapter(response.body().getData());
                        }else {

                            Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                }catch (Exception e){
                    Log.e("exception",e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<GetPoetryResponse> call, Throwable t) {

                Log.e("failer",t.getLocalizedMessage());
            }
        });
    }

}