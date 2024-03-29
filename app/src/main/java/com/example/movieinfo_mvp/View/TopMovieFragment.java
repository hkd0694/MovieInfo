package com.example.movieinfo_mvp.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieinfo_mvp.Adapter.LikeMovieAdpater;
import com.example.movieinfo_mvp.Contract.TopMovieContract;
import com.example.movieinfo_mvp.Network.Model.RecyclerViewModel;
import com.example.movieinfo_mvp.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopMovieFragment extends Fragment implements TopMovieContract.View {

    private SharedPreferences sf;
    private View view;
    private RecyclerView recyclerView;
    private Gson gson;
    private LikeMovieAdpater likeMovieAdpater;
    private GridLayoutManager gridLayoutManager;
    private ArrayList<RecyclerViewModel> datas = new ArrayList<>();

    public TopMovieFragment() {

    }

    @Override
    public void attach(Object presenter) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_top_movie, container, false);
        sf = getContext().getSharedPreferences("Movielike", Context.MODE_PRIVATE);
        recyclerView = view.findViewById(R.id.likeview);
        likeMovieAdpater = new LikeMovieAdpater(view.getContext());
        likeMovieAdpater.clear();
        Log.e("Start","TopMovie Fragment  불림");
        gridLayoutManager = new GridLayoutManager(view.getContext(),3);
        String key = sf.getString("like",null);
        if(key != null) {
            gson = new GsonBuilder().create();
            Type listType = new TypeToken<ArrayList<RecyclerViewModel>>() {}.getType();
            datas = gson.fromJson(key,listType);
            for(RecyclerViewModel recyclerViewModel : datas) {
                Log.e("Start",recyclerViewModel.getMovieNm() + " 저장");
                likeMovieAdpater.add(recyclerViewModel);
            }
        }
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(likeMovieAdpater);
        return view;
    }
}