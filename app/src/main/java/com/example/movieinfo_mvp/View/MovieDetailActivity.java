package com.example.movieinfo_mvp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.movieinfo_mvp.Network.Model.Item;
import com.example.movieinfo_mvp.Network.Model.RecyclerViewModel;
import com.example.movieinfo_mvp.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MovieDetailActivity extends AppCompatActivity {

    private RecyclerViewModel recyclerViewModel;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Log.e("Start","MovieDetailActivity");
        String name = getIntent().getStringExtra("name");
        if(name.equals("search")){
            item = (Item)getIntent().getSerializableExtra("movie");
            Log.e("Start",item.getTitle() + " 야이");
        } else{
            recyclerViewModel = (RecyclerViewModel)getIntent().getSerializableExtra("movie");
            Log.e("Start",recyclerViewModel.getMovieNm() + " 호우");
        }
    }
}
