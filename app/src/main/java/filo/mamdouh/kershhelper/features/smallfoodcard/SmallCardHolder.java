package filo.mamdouh.kershhelper.features.smallfoodcard;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import filo.mamdouh.kershhelper.R;

public class SmallCardHolder extends RecyclerView.ViewHolder {
    ImageButton saveBtn;
    TextView mealName,ingredientsNumber;
    ImageView flag,mealImage;
    public SmallCardHolder(@NonNull View itemView) {
        super(itemView);
        saveBtn = itemView.findViewById(R.id.smallCardSaveBtn);
        mealName = itemView.findViewById(R.id.smallCardMealName);
        ingredientsNumber = itemView.findViewById(R.id.smallCardIngredients);
        flag = itemView.findViewById(R.id.smallCardFlag);
        mealImage = itemView.findViewById(R.id.smallCardBackground);
    }
}
