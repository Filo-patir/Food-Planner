package filo.mamdouh.kershhelper.features.mainappfeatures.mainfoodcard;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import filo.mamdouh.kershhelper.R;

public class FoodCardHolder extends RecyclerView.ViewHolder {
    ImageView areaFlag,backgroundImg;
    TextView mealname,ingredients;
    Button addToCalendarBtn;
    ImageButton saveBtn;
    public FoodCardHolder(@NonNull View itemView) {
        super(itemView);
        areaFlag = itemView.findViewById(R.id.cardAreaFlag);
        mealname = itemView.findViewById(R.id.cardMealName);
        ingredients = itemView.findViewById(R.id.cardIngredient);
        addToCalendarBtn = itemView.findViewById(R.id.cardAddToCalendar);
        saveBtn = itemView.findViewById(R.id.card_save_btn2);
        backgroundImg = itemView.findViewById(R.id.cardBackgroundImg);
    }
}
