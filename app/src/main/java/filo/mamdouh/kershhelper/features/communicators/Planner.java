package filo.mamdouh.kershhelper.features.communicators;

import java.util.ArrayList;

import filo.mamdouh.kershhelper.contracts.WeekSetter;

public interface Planner {
    void addToCalendar(String id , ArrayList<String> data);
    void showToast(String text);
    void getSavedWeek(String id, WeekSetter setter);

}
