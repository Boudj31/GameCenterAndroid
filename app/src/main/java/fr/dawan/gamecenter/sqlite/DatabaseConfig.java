package fr.dawan.gamecenter.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.dawan.gamecenter.models.Game;

public class DatabaseConfig extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "gameforma.db";
    public static final String TABLE_NAME = "games";
    public static final String COL_ID = "ID";
    public static final String COL_TITLE = "TITLE";
    public static final String COL_DESCRIPTION = "DESCRIPTION";
    public static final String COL_YEAR = "YEAR";
    public static final String COL_CONSOLE = "CONSOLE";
    public static final String COL_IMG_PATH = "IMG_PATH";
    // activity
    private Context context;

    // initialisation de la classe
    public DatabaseConfig(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_TITLE + " TEXT, " + COL_DESCRIPTION + " TEXT, " + COL_CONSOLE + " TEXT, "
                + COL_YEAR + " TEXT, " + COL_IMG_PATH + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

}
