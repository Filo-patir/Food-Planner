package filo.mamdouh.kershhelper.contracts;

import java.util.List;

import filo.mamdouh.kershhelper.models.DaysOfWeek;
import filo.mamdouh.kershhelper.models.MealsItem;

public interface CalendarContract {
    interface Listener {
        void onClick(String mealID,boolean isSaved);
        void removeItemListener(DaysOfWeek day, MealsItem mealsItem);
    }
    interface View{
        void updateUI(DaysOfWeek key, List<MealsItem> item);
    }
}
