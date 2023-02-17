package fr.dawan.gamecenter.fragments;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import fr.dawan.gamecenter.R;
import fr.dawan.gamecenter.models.Game;
import fr.dawan.gamecenter.sqlite.GameDao;
import fr.dawan.gamecenter.tools.RealPathUtil;


public class AddGameFragment extends Fragment {

    EditText title;
    EditText description;
    EditText yearReleased;
    Spinner console;
    Button addGameBtn;
    Button loadImgBtn;
    GameDao gameDao;
    ImageView imageView;
    String imgPath;
    private static final int PICK_IMAGE = 100;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // OUVRIR LE GESTIONNAIRE DE FICHIER ANDROID
        loadImgBtn.setOnClickListener( v ->  {
            try {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Choisir une image"), PICK_IMAGE);

            } catch (Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });

        // AJOUT D'UN JEU
        addGameBtn.setOnClickListener(v -> {
            Game newGame = new Game(
                    title.getText().toString(),
                    description.getText().toString(),
                    yearReleased.getText().toString(),
                    console.getSelectedItem().toString(),
                    imgPath
            );

            gameDao.addGame(newGame);
            title.setText("");
            description.setText("");
            yearReleased.setText("");
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.add_game_fragment, container, false);
        gameDao = new GameDao(getContext());
        title = view.findViewById(R.id.edit_title);
        description = view.findViewById(R.id.edit_desc);
        yearReleased = view.findViewById(R.id.edit_year);
        console = view.findViewById(R.id.edit_console);
        addGameBtn = view.findViewById(R.id.btn_add_game);
        imageView = view.findViewById(R.id.image_view_game);
        loadImgBtn = view.findViewById(R.id.btn_load_img);

        return view;
    }
     //Lien classe utilitaire pour le path
    //https://gist.github.com/tatocaster/32aad15f6e0c50311626

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);
       if(requestCode == PICK_IMAGE &&  resultCode == Activity.RESULT_OK) {
           Uri uri = data.getData();
           imgPath = RealPathUtil.getRealPath(getContext(), uri);
           Bitmap btm = null;
           try {
               btm = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
               imageView.setImageBitmap(btm);
           } catch (Exception e) {
               Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
           }


       }

    }


}