package com.diss.mason.visfitness.utils;

import android.content.ContentValues;
import android.content.Context;

import com.diss.mason.visfitness.Models.RoutinesModel;

/*
    A class to save share codes to the devices database this is to stop users
    downloading their own share codes.
 */
public class SaveShareCode {

    public void saveShareCode(Context context,RoutinesModel routinesModel){
        ContentValues newValues = new ContentValues();
        newValues.put(MyProviderContract.SHARE_CODE, routinesModel.getRoutineName());
        context.getContentResolver().insert(MyProviderContract.SHAR_URI, newValues);
    }
}
