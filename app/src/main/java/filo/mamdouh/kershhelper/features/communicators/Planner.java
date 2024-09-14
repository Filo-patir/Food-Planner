package filo.mamdouh.kershhelper.features.communicators;

import java.util.ArrayList;

import filo.mamdouh.kershhelper.contracts.WeekSetter;
import filo.mamdouh.kershhelper.models.DaysOfWeek;
import filo.mamdouh.kershhelper.models.MealsItem;

public interface Planner {
    void addToCalendar(MealsItem item , ArrayList<DaysOfWeek> data);
    void getSavedWeek(String id, WeekSetter setter);
}
