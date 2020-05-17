package com.diss.mason.visfitness.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.diss.mason.visfitness.Fragments.EditWorkoutsFragment;
import com.diss.mason.visfitness.Activities.MainActivity;
import com.diss.mason.visfitness.R;
import com.diss.mason.visfitness.Models.RoutinesModel;
import com.diss.mason.visfitness.utils.SeverRequests.PostRoutine;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

/*
    A class to open a popup menu when a user presses the three dots on a workout
 */

public class MyPopupClass implements DataHandlerInterface {


        private MainActivity activity;

        public void showPopupWindow ( final View view, final RoutinesModel routinesModel,
        final Context context){


        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.pop_up_layout, null);


        final PopupWindow popupWindow = new PopupWindow(popupView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        final Handler handler = new Handler();
        activity = ((MainActivity) context);

        popupView.findViewById(R.id.delete_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.postDelayed(new Runnable() {
                    public void run() {
                        new DeleteNewRoutine().deleteNewRoutine(context, routinesModel);
                        activity.refreshViewFragment(routinesModel);
                        Toast.makeText(context, routinesModel.getRoutineName() +
                                context.getString(R.string.has_been_deleted), Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                }, 100);
            }
        });
        popupView.findViewById(R.id.edit_layout).setOnClickListener(new View.OnClickListener() {
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

        popupView.findViewById(R.id.share_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.postDelayed(new Runnable() {
                    public void run() {
                        callPostRoutine(routinesModel);
                        popupWindow.dismiss();
                    }
                }, 100);

            }
        });

    }

    private void callPostRoutine(RoutinesModel routinesModel){
        PostRoutine routine = new PostRoutine(this, routinesModel);
        routine.execute();
    }

    @Override
    public void DataHandlerInterface(ArrayList<RoutinesModel> routinesModel) {
            if(!routinesModel.isEmpty()) {
                String code = routinesModel.get(0).getRoutineName().trim();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, code);
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                activity.startActivity(shareIntent);
                new SaveShareCode().saveShareCode(activity,routinesModel.get(0));
            }
    }
}
