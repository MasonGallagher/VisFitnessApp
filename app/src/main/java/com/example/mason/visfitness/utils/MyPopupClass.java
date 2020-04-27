package com.example.mason.visfitness.utils;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.mason.visfitness.EditWorkoutsFragment;
import com.example.mason.visfitness.MainActivity;
import com.example.mason.visfitness.R;
import com.example.mason.visfitness.RoutinesModel;

public class MyPopupClass {

    //PopupWindow display method
    private MainActivity activity;

    public void showPopupWindow(final View view, final RoutinesModel routinesModel, final Context context) {


        //Create a View object yourself through inflater
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.pop_up_layout, null);

        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

        //Create a window with our parameters
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        final Handler handler = new Handler();
        activity = ((MainActivity) context);

        LinearLayout delete_layout = popupView.findViewById(R.id.delete_layout);
        delete_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.postDelayed(new Runnable() {
                    public void run() {
                        new DeleteNewRoutine().deleteNewRoutine(context,routinesModel);
                        activity.refreshViewFragment(routinesModel);
                        Toast.makeText(context, routinesModel.getRoutineName()+
                                " has been successfully deleted!", Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                }, 100);
            }
        });
        LinearLayout edit_layout = popupView.findViewById(R.id.edit_layout);
        edit_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Fragment fragment = new EditWorkoutsFragment().makeFragment(routinesModel);
                        activity.setFragment(fragment);
                        popupWindow.dismiss();
                    }
                }, 100);

            }
        });

    }

}
