package com.example.mason.visfitness.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mason on 12/12/2019.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub

    }


    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE exercises (" +
                "exerciseID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "exerciseName VARCHAR(128) NOT NULL," +
                "defaultSets INTEGER," +
                "defaultReps INTEGER" +
                ");");

        db.execSQL("CREATE TABLE routines (" +
                "routineID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "routineName VARCHAR(128)NOT NULL)");

        db.execSQL("CREATE TABLE workouts (" +
                "exerciseID INT NOT NULL, " +
                "routineID INT NOT NULL," +
                "CONSTRAINT fk1 FOREIGN KEY(exerciseID)REFERENCES exercises(exerciseID)," +
                "CONSTRAINT fk2 FOREIGN KEY(routineID)REFERENCES routines(routineID)," +
                "CONSTRAINT workoutID PRIMARY KEY(exerciseID, routineID))");

        db.execSQL("CREATE TABLE shared (" +
                "shareCode VARCHAR(128)NOT NULL  PRIMARY KEY)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS exercises");
        db.execSQL("DROP TABLE IF EXISTS routines");
        db.execSQL("DROP TABLE IF EXISTS workouts");
        db.execSQL("DROP TABLE IF EXISTS shared");
        onCreate(db);
    }
}
