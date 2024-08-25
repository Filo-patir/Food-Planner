package filo.mamdouh.kershhelper.features.mainappfeatures.savedmeals;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.contracts.BookmarkContract;
import filo.mamdouh.kershhelper.datastorage.local.FileHandler;
import filo.mamdouh.kershhelper.datastorage.room.calendar.CalendarDataSourceImpl;
import filo.mamdouh.kershhelper.datastorage.room.savedmeals.SavedMealsDataSourceImpl;
import filo.mamdouh.kershhelper.features.communicators.OnItemClickListener;
import filo.mamdouh.kershhelper.features.mainappfeatures.addtocalendardialog.PlanDialog;
import filo.mamdouh.kershhelper.features.mainappfeatures.home.Updater;
import filo.mamdouh.kershhelper.features.mainappfeatures.savedmeals.presenter.BookmarkPresenter;
import filo.mamdouh.kershhelper.models.MealsItem;
import filo.mamdouh.kershhelper.models.Repostiry;

public class BookmarkFragment extends Fragment implements BookmarkContract.View , OnItemClickListener {
    RecyclerView bookmarkRV;
    BookmarkPresenter presenter;
    SavedMealsAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BookmarkPresenter(this, Repostiry.getInstance(FileHandler.getInstance(getContext()), SavedMealsDataSourceImpl.getInstance(getContext()), CalendarDataSourceImpl.getInstance(getContext())));
        Log.d("TAG", "onCreate: Bookmark");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_bookmark,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bookmarkRV = view.findViewById(R.id.bookmarkRV);
        presenter.getSavedMeals();
        adapter = new SavedMealsAdapter(getContext(),this);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        bookmarkRV.setAdapter(adapter);
        bookmarkRV.setLayoutManager(layoutManager);
    }

    @Override
    public void removeItem(String toast) {
        Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onGetSavedMeals(List<MealsItem> mealsItems) {
        adapter.setItems(mealsItems);
    }

    @Override
    public void addToCalendarListener(String mealID,String mealName) {
        PlanDialog dialog = new PlanDialog(getActivity(),mealID,mealName);
        dialog.showDialog();
    }

    @Override
    public void saveItemListener(MealsItem mealsItem, Updater updater) {
        presenter.deleteMeal(mealsItem);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}