package fr.dawan.gamecenter.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.dawan.gamecenter.models.Game;

public class GameDao implements IGameDao {

    private DatabaseConfig databaseConfig;
    private Context context;

    public GameDao(Context context) {
        databaseConfig = new DatabaseConfig(context);
        this.context = context;
    }

    @Override
    public Game findOneGame(String title) {
        SQLiteDatabase db = databaseConfig.getReadableDatabase();
        Cursor cursor = null ;
        if(db != null) {
            cursor = db.rawQuery(
                    "SELECT * FROM " + DatabaseConfig.TABLE_NAME + " WHERE " + DatabaseConfig.COL_TITLE + " = ?",
                    // argument de selection
                    new String[]{title}
            );
        }
        Game game = null;
        if(cursor.moveToFirst()) {
            game = new Game(
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConfig.COL_TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConfig.COL_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConfig.COL_YEAR)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConfig.COL_CONSOLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConfig.COL_IMG_PATH))
            );
        }
        cursor.close();
        db.close();
        return game;
    }

    @Override
    public List<Game> findAllGames() {
        String query = "SELECT * FROM " + DatabaseConfig.TABLE_NAME;
        SQLiteDatabase db = databaseConfig.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        List<Game> games = new ArrayList<>();
        while (cursor.moveToNext()) {
            Game game = new Game(
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConfig.COL_TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConfig.COL_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConfig.COL_YEAR)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConfig.COL_CONSOLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConfig.COL_IMG_PATH))
            );
            games.add(game);
        }
        cursor.close();
        db.close();
        return games;
    }

    @Override
    public List<Game> findAllGamesBySearch(String keywords) {
        String query = "SELECT * FROM " + DatabaseConfig.TABLE_NAME + " WHERE " + DatabaseConfig.COL_TITLE + " LIKE ? OR " + DatabaseConfig.COL_DESCRIPTION + " LIKE ?" ;
        SQLiteDatabase db = databaseConfig.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query,  new String[] { "%" + keywords + "%",  "%" + keywords + "%"});
        }
        List<Game> games = new ArrayList<>();
        while (cursor.moveToNext()) {
            Game game = new Game(
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConfig.COL_TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConfig.COL_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConfig.COL_YEAR)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConfig.COL_CONSOLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConfig.COL_IMG_PATH))
            );
            games.add(game);
        }
        cursor.close();
        db.close();
        return games;
    }

    @Override
    public void addGame(Game gameToAdd) {
        // this référence à ma classe
        SQLiteDatabase db = databaseConfig.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        // Ajout des données dans mon content value
        contentValues.put(DatabaseConfig.COL_TITLE, gameToAdd.getTitle());
        contentValues.put(DatabaseConfig.COL_DESCRIPTION, gameToAdd.getDescription());
        contentValues.put(DatabaseConfig.COL_YEAR, gameToAdd.getYearReleased());
        contentValues.put(DatabaseConfig.COL_CONSOLE, gameToAdd.getConsole());
        contentValues.put(DatabaseConfig.COL_IMG_PATH, gameToAdd.getImgPath());

        // insertion en base
        long result = db.insert(DatabaseConfig.TABLE_NAME, null, contentValues);
        if (result == -1) {
            Toast.makeText(context, "Un problème est survenu !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Le jeu " + gameToAdd.getTitle() + " à été ajouté !", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int deleteGame(String title) {
        SQLiteDatabase db = databaseConfig.getWritableDatabase();
        int deletedRows = db.delete(DatabaseConfig.TABLE_NAME, DatabaseConfig.COL_TITLE + " = ?", new String[]{title});
        db.close();
        if(deletedRows == 0) {
            Toast.makeText(context, "Le Jeu : " + title  + " n'existe pas!", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(context, "Le Jeu : " + title  + " a été supprimé!", Toast.LENGTH_SHORT).show();
        }
        return deletedRows;
    }
}
