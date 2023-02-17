package fr.dawan.gamecenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import fr.dawan.gamecenter.fragments.AddGameFragment;
import fr.dawan.gamecenter.fragments.HomeFragment;
import fr.dawan.gamecenter.fragments.ListGameFragment;
import fr.dawan.gamecenter.models.Game;
import fr.dawan.gamecenter.sqlite.DatabaseConfig;
import fr.dawan.gamecenter.sqlite.GameDao;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    AddGameFragment addGameFragment = new AddGameFragment();
    ListGameFragment listGameFragment = new ListGameFragment();
    TextView fragmentTitle;

    GameDao gameDao;

    ArrayList<String> game_id, game_title, game_description, game_year, game_console, game_img_path;

    private void showFragment(Fragment fragment, String title) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        fragmentTitle = findViewById(R.id.title_fragment);
        fragmentTitle.setText(title);
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, DetailActivity.class);
        //startActivity(intent);

        // SQLITE
        gameDao = new GameDao(this);

        /*
        Game game1 = new Game(
                "Zelda twilight princess",
                "Super jeu sur nintendo 64",
                "2000",
                "N64",
                "https://imgs.search.brave.com/SKjoadvHpurtwDteQ3nYT3TObe3wKboxUhyi5Yb4llk/rs:fit:1200:1200:1/g:ce/aHR0cHM6Ly9pbWFn/ZXMubGF1bmNoYm94/LWFwcC5jb20vMGY2/MzNlZTMtNTY2NS00/MTI4LWEyOGItOWRm/YWQyM2RiNGZmLmpw/Zw");

        Game game2 = new Game(
                "Zelda Occarina of time",
                "Super jeu sur nintendo DS",
                "2009",
                "NDS",
                "https://imgs.search.brave.com/SKjoadvHpurtwDteQ3nYT3TObe3wKboxUhyi5Yb4llk/rs:fit:1200:1200:1/g:ce/aHR0cHM6Ly9pbWFn/ZXMubGF1bmNoYm94/LWFwcC5jb20vMGY2/MzNlZTMtNTY2NS00/MTI4LWEyOGItOWRm/YWQyM2RiNGZmLmpw/Zw");
        db.addGame(game1);
        db.addGame(game2);


        List<Game> games = db.findAllGame();
        for (Game game : games) {
            Log.i("Title" , game.getTitle());
        }


        Game gameFind = gameDao.findOneGame(7);
        Log.i("Title" , gameFind.getTitle());
        Log.i("ImagePath" , gameFind.getImgPath());
         */




        showFragment(homeFragment, "Accueil");

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_page:
                        showFragment(homeFragment, "Accueil");
                        break;
                    case R.id.add_page:
                        showFragment(addGameFragment, "Ajouter un jeu");
                        break;
                    case R.id.list_page:
                        showFragment(listGameFragment, "Liste de jeux");
                        break;
                }
                return true;
            }


        });
    }

}