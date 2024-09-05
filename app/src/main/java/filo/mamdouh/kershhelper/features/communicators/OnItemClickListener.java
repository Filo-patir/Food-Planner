package filo.mamdouh.kershhelper.features.communicators;

import filo.mamdouh.kershhelper.features.mainappfeatures.home.Updater;
import filo.mamdouh.kershhelper.models.MealsItem;

public interface OnItemClickListener {
    void addToCalendarListener(String mealID,String mealName);
    void saveItemListener(MealsItem mealsItem, Updater updater);
    void saveItemListener(String mealID, Updater updater);
    void onItemClick(String mealID,boolean isSaved);
}
