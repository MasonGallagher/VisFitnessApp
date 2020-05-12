package com.diss.mason.visfitness.utils;

import android.net.Uri;

/**
 * Created by Mason on 16/12/2019.
 */

/* a contract class to make sure i was pointing to the correct tables*/
class MyProviderContract {

    public static final String AUTHORITY =
            "com.diss.mason.visfitness.utils.MyProvider";
    public static final Uri EXER_URI = Uri.parse("content://"+AUTHORITY+"/exercises");
    public static final Uri ROUT_URI = Uri.parse("content://"+AUTHORITY+"/routines");
    public static final Uri WORK_URI = Uri.parse("content://"+AUTHORITY+"/workouts");
    public static final Uri SHAR_URI = Uri.parse("content://"+AUTHORITY+"/shared");
    public static final String SHARE_CODE = "shareCode";
    public static final String EXERCISE_ID = "exerciseID";
    public static final String EXERCISE_NAME = "exerciseName";
    public static final String DEFAULT_SETS = "defaultSets";
    public static final String DEFAULT_REPS = "defaultReps";
    public static final String ROUTINE_ID = "routineID";
    public static final String ROUTINE_NAME = "routineName";


    public static final String CONTENT_TYPE_SINGLE = "vnd.android.cursor.item/MyProvider.data.text";
    public static final String CONTENT_TYPE_MULTIPLE = "vnd.android.cursor.dir/MyProvider.data.text";

}
