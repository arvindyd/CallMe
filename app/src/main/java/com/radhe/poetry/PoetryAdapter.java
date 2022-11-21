package com.radhe.poetry;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.radhe.poetry.Modelss.DeleteResponse;
import com.radhe.poetry.Modelss.Poetry;
import com.radhe.poetry.RRR.ApClient;
import com.radhe.poetry.RRR.ApInterface;
import com.radhe.poetry.databinding.SampleRvDesignBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PoetryAdapter extends RecyclerView.Adapter<PoetryAdapter.viewHolder>{

   Context context;
   List<Poetry>poetryModels;
   ApInterface apInterface;

    public PoetryAdapter(Context context, List<Poetry> poetryModels) {
        this.context = context;
        this.poetryModels = poetryModels;

        Retrofit retrofit= ApClient.getClient();
        apInterface=retrofit.create(ApInterface.class);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.sample_rv_design,null);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Poetry poetry= poetryModels.get(position);

      holder.binding.petryName.setText(poetry.getPoet_name());
      holder.binding.poetryData.setText(poetry.getPoetry_data());
      holder.binding.dateTime.setText(poetry.getDate_time());

      holder.binding.delete.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {


              deletepoetry(poetry.getId()+"",position);
          }
      });

    }

    @Override
    public int getItemCount() {
        return poetryModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        SampleRvDesignBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding= SampleRvDesignBinding.bind(itemView);
        }
    }

    private  void  deletepoetry(String id,int pose){

        apInterface.deletepoetry(id).enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {

                try {

                    if (response!=null){

                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();

                        if (response.body().getStatus().equals("1")){

                            poetryModels.remove(pose);

                            notifyDataSetChanged();
                        }
                    }

                }catch (Exception e){
                    Log.e("exp",e.getLocalizedMessage());

                }

            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {

                Log.e("exp",t.getLocalizedMessage());
            }
        });
    }

}
