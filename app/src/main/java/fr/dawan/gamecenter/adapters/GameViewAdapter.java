package fr.dawan.gamecenter.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import fr.dawan.gamecenter.DetailActivity;
import fr.dawan.gamecenter.R;
import fr.dawan.gamecenter.models.Game;
import fr.dawan.gamecenter.tools.Truncate;

public class GameViewAdapter extends RecyclerView.Adapter<GameViewHolder> {

    private List<Game> games;
    private Context context;

    public GameViewAdapter(List<Game> games, Context context) {
        this.games = games;
        this.context = context;
    }

    // Pour chaque élément de la liste -- cette méthode s'excecute toujours en premier
    // Sa mission c'est déserialiser (LayoutInflateur) le layout xml et le relier a un ViewHolder
    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // le layout xml desérialiser
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_game_item, parent, false);
        return new GameViewHolder(view);
    }

    // Association des données de chaque objet de la liste avec les éléments du layout utilisé pour chacun de ces objets
    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {

       Game currentGame = games.get(position);
       holder.tvTitle.setText(currentGame.getTitle());
       holder.tvDesc.setText(Truncate.usingSubstringMethod(currentGame.getDescription(), 40));
       Glide.with(context).load(currentGame.getImgPath()).into(holder.imgGame);

       holder.card.setOnClickListener(view -> {
           Intent intent = new Intent(context, DetailActivity.class);
           intent.putExtra("gameTitle", currentGame.getTitle());
           context.startActivity(intent);
       });

    }

    // Cette méthode elle va counter le nombre d'élement dans la liste
    @Override
    public int getItemCount() {
        return games.size();
    }
}
