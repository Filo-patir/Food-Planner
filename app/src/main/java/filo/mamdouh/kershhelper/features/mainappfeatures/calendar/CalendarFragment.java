package filo.mamdouh.kershhelper.features.mainappfeatures.calendar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.contracts.CalendarContract;
import filo.mamdouh.kershhelper.datastorage.local.FileHandler;
import filo.mamdouh.kershhelper.datastorage.local.SharedPrefrenceHandler;
import filo.mamdouh.kershhelper.datastorage.network.RetrofitClient;
import filo.mamdouh.kershhelper.datastorage.room.calendar.CalendarDataSourceImpl;
import filo.mamdouh.kershhelper.datastorage.room.savedmeals.SavedMealsDataSourceImpl;
import filo.mamdouh.kershhelper.features.mainappfeatures.calendar.presenter.CalendarPresenter;
import filo.mamdouh.kershhelper.models.MealsItem;
import filo.mamdouh.kershhelper.models.Repostiry;

public class CalendarFragment extends Fragment implements CalendarContract.View , CalendarContract.Listner {
    CalendarPresenter presenter;
    RecyclerView recyclerView;
    BaseCalendarAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "onCreate: Calendar");
        presenter = new CalendarPresenter(Repostiry.getInstance(FileHandler.getInstance(getContext()),
                SavedMealsDataSourceImpl.getInstance(getContext()), CalendarDataSourceImpl.getInstance(getContext())
                , RetrofitClient.getInstance(getContext()), SharedPrefrenceHandler.getInstance(getContext())),this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.calendarMainRV);
        adapter = new BaseCalendarAdapter(getContext(),this);
        recyclerView.setAdapter(adapter);
        presenter.getCalendar();
    }

    @Override
    public void updateUI(String key, ArrayList<MealsItem> item) {
        adapter.setCalendarRowArrayList(key,item);
    }

    @Override
    public void onClick(String mealID, boolean isSaved) {

    }

    @Override
    public void removeItemListener(String day,MealsItem mealsItem) {
        presenter.removeItem(day,mealsItem);
    }
}