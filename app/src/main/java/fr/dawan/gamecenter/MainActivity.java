package fr.dawan.gamecenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fr.dawan.gamecenter.fragments.AddGameFragment;
import fr.dawan.gamecenter.fragments.HomeFragment;
import fr.dawan.gamecenter.fragments.ListGameFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    AddGameFragment addGameFragment = new AddGameFragment();
    ListGameFragment listGameFragment = new ListGameFragment();
    TextView fragmentTitle;

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