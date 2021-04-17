package com.example.courseboard.Adapters;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.courseboard.Menu;
import com.example.courseboard.R;
import com.example.courseboard.youtube.Adapter;
import com.example.courseboard.youtube.ApiClient;
import com.example.courseboard.youtube.Example;
import com.example.courseboard.youtube.Item;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class YoutubeFragment extends Fragment {

    RecyclerView recyclerView;


    Dialog dialog;
    final String API_KEY = "AIzaSyAWb1atCYOiy7j702DJRDSITDm3-mPIGtY";
    Adapter adapter;
    String query;
    View view;
    List<Item> articles = new ArrayList<>();

    public YoutubeFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //query=getArguments().getString("query");
        view= inflater.inflate(R.layout.fragment_youtube, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //recyclerView = recyclerView.findViewById(R.id.recyclerview);


        retrievejson("Nodejs","mostPopular",API_KEY);

    }

    private void retrievejson(String query, String mostPopular, String api_key) {
        Call<Example> call;
        call= ApiClient.getInstance().getApi().getHeadlines(query,"mostPopular",api_key);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful() && response.body().getItems() != null){
                    //swipeRefreshLayout.setRefreshing(false);
                    //articles.clear();
                    articles = response.body().getItems();
                    adapter = new Adapter(getContext(),articles);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(getContext(),"no possible value",Toast.LENGTH_LONG).show();
            }
        });
    }


}