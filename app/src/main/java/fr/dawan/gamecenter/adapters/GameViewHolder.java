package fr.dawan.gamecenter.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import fr.dawan.gamecenter.R;

public class GameViewHolder extends RecyclerView.ViewHolder {

    // Objet intermédiaire entre la liste d'objet et à afficher et le layout
    ImageView imgGame;
    TextView tvTitle;
    TextView tvDesc;
    LinearLayoutCompat card;

    public GameViewHolder(@NonNull View itemView) {
        super(itemView);
        imgGame = itemView.findViewById(R.id.img_card);
        tvTitle = itemView.findViewById(R.id.title_card_tv);
        tvDesc = itemView.findViewById(R.id.desc_card_tv);
        card = itemView.findViewById(R.id.card_game);

    }
}
