package fr.dawan.gamecenter.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import fr.dawan.gamecenter.DetailActivity;
import fr.dawan.gamecenter.R;
import fr.dawan.gamecenter.tools.Truncate;

public class ListGameFragment extends Fragment {

    LinearLayout card;
    TextView desc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.list_game_fragment, container, false);
        card = view.findViewById(R.id.card_game);
        desc = view.findViewById(R.id.detail_desc);

        desc.setText(Truncate.usingSubstringMethod(desc.getText().toString(), 20));
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the button click here
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                startActivity(intent);
            }
        });

    }


}