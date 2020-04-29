package com.example.mason.visfitness.utils;

import android.content.ContentValues;
import android.content.Context;

import com.example.mason.visfitness.Models.RoutinesModel;

public class SaveShareCode {

    public void saveShareCode(Context context,RoutinesModel routinesModel){
        ContentValues newValues = new ContentValues();
        newValues.put(MyProviderContract.SHARE_CODE, routinesModel.getRoutineName());
        context.getContentResolver().insert(MyProviderContract.SHAR_URI, newValues);
    }
}
