package filo.mamdouh.kershhelper.features.dialogs.addtocalendardialog;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.contracts.WeekSetter;
import filo.mamdouh.kershhelper.features.communicators.Planner;
import filo.mamdouh.kershhelper.models.DaysOfWeek;
import filo.mamdouh.kershhelper.models.MealsItem;

public class PlanDialog implements WeekSetter {
    private final Activity activity;
    private final Planner planner;
    private AlertDialog alertDialog;
    private CheckBox saturdayCB,sundayCB,mondayCB,tuesdayCB,wednesdayCB,thursdayCB,fridayCB;
    private final MealsItem item;

    public PlanDialog(Activity activity,MealsItem item){
        this.activity = activity;
        planner = (Planner) activity;
        this.item = item;
        planner.getSavedWeek(item.getIdMeal(),this);
    }

    public void showDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.plan_dialog,null);
        builder.setView(view);
        alertDialog = builder.create();
        Objects.requireNonNull(alertDialog.getWindow()).setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        alertDialog.show();
        Button confirmButton = view.findViewById(R.id.dialogAddToCalendarBtn);
        Button cancelButton = view.findViewById(R.id.dialogCancelBtn);
        saturdayCB = view.findViewById(R.id.saturdaycb);
        sundayCB = view.findViewById(R.id.sundayCB);
        mondayCB = view.findViewById(R.id.mondayCB);
        tuesdayCB = view.findViewById(R.id.tuesdayCB);
        wednesdayCB = view.findViewById(R.id.wednesdayCB);
        thursdayCB = view.findViewById(R.id.thursdayCB);
        fridayCB = view.findViewById(R.id.fridayCB);
        TextView mealNameView = view.findViewById(R.id.dialogMealName);
        mealNameView.setText(item.getStrMeal());

        confirmButton.setOnClickListener(v -> {
            ArrayList<DaysOfWeek> data = getData();
            planner.addToCalendar(item, data);
            alertDialog.dismiss();
        });
        cancelButton.setOnClickListener(v -> alertDialog.dismiss());

    }

    private ArrayList<DaysOfWeek> getData(){
        ArrayList<DaysOfWeek> days = new ArrayList<>();
        if (saturdayCB.isChecked())
            days.add(DaysOfWeek.SATURDAY);
        if (sundayCB.isChecked())
            days.add(DaysOfWeek.SUNDAY);
        if (mondayCB.isChecked())
            days.add(DaysOfWeek.MONDAY);
        if (tuesdayCB.isChecked())
            days.add(DaysOfWeek.TUESDAY);
        if (wednesdayCB.isChecked())
            days.add(DaysOfWeek.WEDNESDAY);
        if (thursdayCB.isChecked())
            days.add(DaysOfWeek.THURSDAY);
        if (fridayCB.isChecked())
            days.add(DaysOfWeek.FRIDAY);
        Log.d("Filo", "getData: " + days);
        return days;
    }

    @Override
    public void setData(List<DaysOfWeek> savedWeek){
        for(DaysOfWeek day : savedWeek){
            switch (day){
                case SATURDAY:
                    saturdayCB.setChecked(true);
                    break;
                case SUNDAY:
                    sundayCB.setChecked(true);
                    break;
                case MONDAY:
                    mondayCB.setChecked(true);
                    break;
                case TUESDAY:
                    tuesdayCB.setChecked(true);
                    break;
                case WEDNESDAY:
                    wednesdayCB.setChecked(true);
            }
        }
    }
}

