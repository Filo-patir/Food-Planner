package filo.mamdouh.kershhelper.features.communicators;

import filo.mamdouh.kershhelper.features.mainappfeatures.home.Updater;
import filo.mamdouh.kershhelper.models.MealsItem;

public interface OnItemClickListener {
    void addToCalendarListener();
    void saveItemListener(MealsItem mealsItem, Updater updater);
}
