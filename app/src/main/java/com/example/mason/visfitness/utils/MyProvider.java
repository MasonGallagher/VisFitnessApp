package com.example.mason.visfitness.utils;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by Mason on 16/12/2019.
 */

/* My contentProvider for handling all my requests*/
public class MyProvider extends ContentProvider {

    private static final UriMatcher uriMatcher;
    private DBHelper dbHelper;
    SQLiteDatabase db;

    /* my static pointers */

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(MyProviderContract.AUTHORITY, "exercises", 1);
        uriMatcher.addURI(MyProviderContract.AUTHORITY, "routines", 2);
        uriMatcher.addURI(MyProviderContract.AUTHORITY, "workouts", 3);
    }

    @Override
    public boolean onCreate() {
        this.dbHelper = new DBHelper(this.getContext(), "mydb", null, 8);
        db = dbHelper.getWritableDatabase();
        return true;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String tableName=null;
        switch(uriMatcher.match(uri)) {
            case 1:
                tableName = "exercises";
                break;
            case 2:
                tableName = "routines";
                break;
            case 3:
                tableName = "workouts";
                break;
        }
        long id = db.insert(tableName, null, values);
        Uri nu = ContentUris.withAppendedId(uri, id);


        getContext().getContentResolver().notifyChange(nu, null);

        return nu;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[]
            selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch(uriMatcher.match(uri)) {
            case 1:
                return db.query("exercises", new String[] { "exerciseID", "exerciseName" ,"defaultSets","defaultReps"  },
                        null, null, null, null, null);
            case 2:
                return db.query("routines", new String[] { "routineID", "routineName"  },
                        null, null, null, null, sortOrder);
            case 3:
                return db.query("workouts", new String[] { "exerciseID", "routineID"  },
                        null, null, null, null, sortOrder);
//            case 3:
//                String [] recipeId = { "6" };
//                Cursor cursor;
//                cursor = db.rawQuery("select r._id as recipe_id, r.name, ri.ingredient_id,"+
//                                "i.ingredientname "+
//                                "from recipes r "+
//                                "join recipe_ingredients ri on (r._id = ri.recipe_id)"+
//                                "join ingredients i on (ri.ingredient_id = i._id) where r._id == ?",
//                        recipeId);
//                return cursor;
            default:
                return null;
        }
    }

    @Override
    public String getType(Uri uri) {
        String contentType;
        if (uri.getLastPathSegment()==null) {
            contentType = MyProviderContract.CONTENT_TYPE_MULTIPLE;
        } else {
            contentType = MyProviderContract.CONTENT_TYPE_SINGLE;
        }
        return contentType;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[]
            selectionArgs) {
        switch(uriMatcher.match(uri)) {
            case 1:
                db.update("exercises", values, selection, selectionArgs);
                break;
            case 2:
                db.update("routines", values, selection, selectionArgs);
                break;
            case 3:
                db.update("workouts", values, selection, selectionArgs);
                break;
        }
        Uri nu = ContentUris.withAppendedId(uri, Long.parseLong(selection.split("=")[1]));

        getContext().getContentResolver().notifyChange(nu, null);
        return 0;
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch(uriMatcher.match(uri)) {
            case 1:
                db.delete("exercises", selection, selectionArgs);
                break;
            case 2:
                db.delete("routines", selection, selectionArgs);
                break;
            case 3:
                db.delete("workouts", selection, selectionArgs);
                break;
        }
        Uri nu = ContentUris.withAppendedId(uri, Long.parseLong(selection.split("=")[1]));

        getContext().getContentResolver().notifyChange(nu, null);
        return 0;
    }


}
