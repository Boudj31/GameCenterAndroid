package fr.dawan.gamecenter.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.dawan.gamecenter.DetailActivity;
import fr.dawan.gamecenter.R;
import fr.dawan.gamecenter.adapters.GameViewAdapter;
import fr.dawan.gamecenter.models.Game;
import fr.dawan.gamecenter.sqlite.GameDao;
import fr.dawan.gamecenter.tools.Truncate;

public class ListGameFragment extends Fragment {

    TextView desc, total;
    RecyclerView recyclerView;
    List<Game> games = new ArrayList<>();
    GameViewAdapter adapter;
    EditText search;
    Button searchBtn;


    public void beginSearch(String keyword){
        GameDao gameDao = new GameDao(getContext());

        games = gameDao.findAllGamesBySearch(keyword);


        adapter = new GameViewAdapter(games, getContext());
        total.setText(String.valueOf(adapter.getItemCount()));

        //Définir un LayoutManager - choisir la façon d'afficher les éléments dans la RecycleView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2, RecyclerView.HORIZONTAL, false);

        //Affecter le layout
        recyclerView.setLayoutManager(layoutManager);

        //Affecter l'adapter à recycleView
        recyclerView.setAdapter(adapter);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.list_game_fragment, container, false);
        recyclerView = view.findViewById(R.id.game_recycleview);
        recyclerView = view.findViewById(R.id.game_recycleview);
        total = view.findViewById(R.id.tv_total);
        search = view.findViewById(R.id.edit_search);
        searchBtn = view.findViewById(R.id.search_btn);

        beginSearch("");

        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchBtn.setOnClickListener(v -> {
            if (!search.getText().toString().isEmpty())
                beginSearch(search.getText().toString());
        });

    }


}