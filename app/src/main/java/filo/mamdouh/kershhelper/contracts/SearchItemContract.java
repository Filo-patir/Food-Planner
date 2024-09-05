package filo.mamdouh.kershhelper.contracts;

import filo.mamdouh.kershhelper.features.mainappfeatures.home.Updater;
import filo.mamdouh.kershhelper.models.MealsItem;

public interface SearchItemContract {
    interface View{
        void updateUI(MealsItem mealsItem);
        void removeList();
    }

     interface Listener {
        void saveItemListener(MealsItem mealsItem, Updater updater);
        void onItemClick(String mealID,boolean isSaved);
    }
}
