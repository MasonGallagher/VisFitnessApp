package com.diss.mason.visfitness.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.diss.mason.visfitness.Activities.MainActivity;
import com.diss.mason.visfitness.Fragments.SupportFragment;
import com.diss.mason.visfitness.Models.RoutinesModel;
import com.diss.mason.visfitness.R;
import com.diss.mason.visfitness.utils.MyPopupClass;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Mason on 04/12/2019.
 */

/*
    An adapter responsible for populating the workout cards on the ViewWorkoutsFragment
    items have two types, item or header, header allows for a title and support page button
    within the recycler view
 */

public class viewWorkoutAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private final ArrayList<RoutinesModel> routineList;
        private final LayoutInflater mInflater;
        private final Context context;
        private static final int TYPE_HEADER = 0;
        private static final int TYPE_ITEM = 1;

        public viewWorkoutAdapter(Context context,ArrayList<RoutinesModel> routines) {
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
            this.routineList = routines;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_ITEM) {
                View view = mInflater.inflate(R.layout.recyclerview_workout_item, parent, false);
                return new ViewHolderItem(view);
            } else if (viewType == TYPE_HEADER) {
                View view = mInflater.inflate(R.layout.layout_header_view_routines, parent, false);
                return new VHHeader(view);
            }
            throw new RuntimeException("error "+viewType);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof ViewHolderItem) {
                int[] drawable_list = new int[]{R.drawable.workout_gradient1, R.drawable.workout_gradient2,
                        R.drawable.workout_gradient3, R.drawable.workout_gradient4, R.drawable.workout_gradient5};
                RoutinesModel routine = getItem(position);
                Drawable drawable = context.getDrawable(
                        drawable_list[routineList.indexOf(routine) % getItemCount()]);
                ((ViewHolderItem)holder).frameLayout.setBackground(drawable);
                ((ViewHolderItem)holder).routine_name.setText(routine.getRoutineName());
                ((ViewHolderItem)holder).exercise_recycler.setLayoutManager(new LinearLayoutManager(context));
                WorkoutAdapterExerciseAdapter adapter = new WorkoutAdapterExerciseAdapter(context,
                        routine.getExercises());
                ((ViewHolderItem)holder).exercise_recycler.setAdapter(adapter);
            }
        }

        @Override
        public int getItemCount() {
            return routineList.size()+1;
        }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }
    private boolean isPositionHeader(int position) {
        return position == 0;
    }
    private RoutinesModel getItem(int position) {
        return routineList.get(position - 1);
    }

    class VHHeader extends RecyclerView.ViewHolder {
        final ImageView help;
        VHHeader(View itemView) {
            super(itemView);
            help = itemView.findViewById(R.id.help);
            help.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)context).setFragment(new SupportFragment());
                }
            });
        }
    }

    class ViewHolderItem extends RecyclerView.ViewHolder {
            final TextView routine_name;
            final ImageView editBtn;
            final ConstraintLayout frameLayout;
            final RecyclerView exercise_recycler;
            ViewHolderItem(View itemView) {
                super(itemView);
                routine_name = itemView.findViewById(R.id.routine_name);
                editBtn = itemView.findViewById(R.id.edit_btn);
                frameLayout = itemView.findViewById(R.id.frame);
                exercise_recycler = itemView.findViewById(R.id.exercise_recycler);
                editBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MyPopupClass popUpClass = new MyPopupClass();
                        popUpClass.showPopupWindow(view,getItem(getAdapterPosition()),
                                context);
                    }
                });
            }
        }
}
