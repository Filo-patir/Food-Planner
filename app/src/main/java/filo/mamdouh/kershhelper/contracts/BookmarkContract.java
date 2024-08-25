package filo.mamdouh.kershhelper.contracts;

import java.util.List;

import filo.mamdouh.kershhelper.models.MealsItem;

public interface BookmarkContract {
    interface View{
        void removeItem(String toast);
        void onGetSavedMeals(List<MealsItem> mealsItems);
    }
}
