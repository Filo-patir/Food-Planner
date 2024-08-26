package filo.mamdouh.kershhelper.contracts;

import java.util.ArrayList;

import filo.mamdouh.kershhelper.models.MealsItem;

public interface CalendarContract {
    interface Listner {
        void onClick(String mealID,boolean isSaved);
        void removeItemListener(String day,MealsItem mealsItem);
    }
    interface View{
        void updateUI(String key, ArrayList<MealsItem> item);
    }
}
