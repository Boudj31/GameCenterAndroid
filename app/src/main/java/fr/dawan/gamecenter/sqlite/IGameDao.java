package fr.dawan.gamecenter.sqlite;

import java.util.List;

import fr.dawan.gamecenter.models.Game;

public interface IGameDao {

    Game findOneGame(String title);
    List<Game> findAllGames();
    List<Game> findAllGamesBySearch(String keywords);
    void addGame(Game gameToAdd);
    int deleteGame(String title);


}
