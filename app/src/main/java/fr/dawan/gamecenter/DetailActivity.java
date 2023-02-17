package fr.dawan.gamecenter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import fr.dawan.gamecenter.models.Game;
import fr.dawan.gamecenter.sqlite.GameDao;

public class DetailActivity extends AppCompatActivity {

    Game gameFind;
    GameDao gameDao;
    TextView title;
    TextView desc;
    TextView console;
    TextView year;
    ImageView detailImg;
    Button removeGameBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        gameDao = new GameDao(this);

        title = findViewById(R.id.text_title);
        desc = findViewById(R.id.detail_desc);
        console = findViewById(R.id.detail_console);
        year = findViewById(R.id.detail_year);
        detailImg = findViewById(R.id.detail_img);
        removeGameBtn = findViewById(R.id.remove_game_btn);

       loadGame();
       removeGameBtn.setOnClickListener(view -> {

           // boite de dialogue
           AlertDialog.Builder builder = new AlertDialog.Builder(this);
           builder.setTitle("Suppression du jeu")
                  .setMessage("Etes-vous sur de vouloir supprimer")
                  .setPositiveButton("OUI", (dialogInterface, i) -> {
                      gameDao.deleteGame(gameFind.getTitle());
                      Intent intent = new Intent(this, MainActivity.class);
                      startActivity(intent);
                           })
                   .setNegativeButton("NON", null);
           builder.create().show();
       });
    }

    public void loadGame() {
        gameFind = gameDao.findOneGame(getIntent().getStringExtra("gameTitle"));

        title.setText(gameFind.getTitle());
        desc.setText(gameFind.getDescription());
        console.setText(gameFind.getConsole());
        year.setText(gameFind.getYearReleased());
        Glide.with(this).load(gameFind.getImgPath()).into(detailImg);
    }
}
