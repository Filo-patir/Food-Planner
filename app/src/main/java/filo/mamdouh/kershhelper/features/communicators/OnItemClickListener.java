package filo.mamdouh.kershhelper.features.communicators;

import filo.mamdouh.kershhelper.models.Desserts;
import filo.mamdouh.kershhelper.features.mainappfeatures.home.Updater;
import filo.mamdouh.kershhelper.models.MealsItem;

public interface OnItemClickListener {
    void addToCalendarListener(MealsItem meal);
    void addToCalendarListener(Desserts desserts);
    void saveItemListener(MealsItem mealsItem, Updater updater);
    void saveItemListener(Desserts desserts, Updater updater);
    void onItemClick(String mealID,boolean isSaved);
}
