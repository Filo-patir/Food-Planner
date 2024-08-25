package filo.mamdouh.kershhelper.features.mainappfeatures.addtocalendardialog;

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

import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.contracts.WeekSetter;
import filo.mamdouh.kershhelper.features.communicators.Planner;

public class PlanDialog implements WeekSetter {
    private final Activity activity;
    private Planner planner;
    private AlertDialog alertDialog;
    private Button confirmButton;
    private Button cancelButton;
    private CheckBox saturdayCB,sundayCB,mondayCB,tuesdayCB,wednesdayCB,thursdayCB,fridayCB;
    private String mealId,mealName;
    private TextView mealNameView;


    public PlanDialog(Activity activity,String mealId,String mealName){
        this.activity = activity;
        planner = (Planner) activity;
        this.mealId = mealId;
        this.mealName = mealName;
        planner.getSavedWeek(mealId,this);
    }
    public void showDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.plan_dialog,null);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        alertDialog.show();
        confirmButton = view.findViewById(R.id.dialogAddToCalendarBtn);
        cancelButton = view.findViewById(R.id.dialogCancelBtn);
        saturdayCB = view.findViewById(R.id.saturdaycb);
        sundayCB = view.findViewById(R.id.sundayCB);
        mondayCB = view.findViewById(R.id.mondayCB);
        tuesdayCB = view.findViewById(R.id.tuesdayCB);
        wednesdayCB = view.findViewById(R.id.wednesdayCB);
        thursdayCB = view.findViewById(R.id.thursdayCB);
        fridayCB = view.findViewById(R.id.fridayCB);
        mealNameView = view.findViewById(R.id.dialogMealName);
        mealNameView.setText(mealName);

        confirmButton.setOnClickListener(v -> {
            ArrayList<String> data = getData();
            if (isAllNonNull(data))
                planner.showToast("Please Select a Day");
            else {
                planner.addToCalendar(mealId, data);
                planner.showToast("Added Successfuly");
                alertDialog.dismiss();
            }
        });
        cancelButton.setOnClickListener(v -> alertDialog.dismiss());

    }

    private ArrayList<String> getData(){
        return new ArrayList<>(List.of(
                saturdayCB.isChecked() ? "Saturday" : "",
                sundayCB.isChecked() ? "Sunday" : "",
                mondayCB.isChecked() ? "Monday" : "",
                tuesdayCB.isChecked() ? "Tuesday" : "",
                wednesdayCB.isChecked() ? "Wednesday" : "",
                thursdayCB.isChecked() ? "Thursday" : "",
                fridayCB.isChecked() ? "Friday" : ""
        ));
    }
    @Override
    public void setData(List<Boolean> savedWeek){
        Log.d("Filo", "setData: "+savedWeek);
         saturdayCB.setChecked(savedWeek.get(0));
         sundayCB.setChecked(savedWeek.get(1));
         mondayCB.setChecked(savedWeek.get(2));
         tuesdayCB.setChecked(savedWeek.get(3));
         wednesdayCB.setChecked(savedWeek.get(4));
         thursdayCB.setChecked(savedWeek.get(5));
         fridayCB.setChecked(savedWeek.get(6));
    }

    private boolean isAllNonNull(List<String> list) {
        Log.d("Filo", "isAllNonNull: ");
        for (String element : list) {
            Log.d("Filo", "isAllNonNull: "+element);
            if (!element.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}

