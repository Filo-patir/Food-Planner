package filo.mamdouh.kershhelper.features.mainappfeatures.savedmeals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.contracts.BookmarkContract;
import filo.mamdouh.kershhelper.datastorage.local.FileHandler;
import filo.mamdouh.kershhelper.datastorage.local.SharedPrefrenceHandler;
import filo.mamdouh.kershhelper.datastorage.network.RetrofitClient;
import filo.mamdouh.kershhelper.datastorage.room.savedmeals.SavedMealsDataSourceImpl;
import filo.mamdouh.kershhelper.features.communicators.OnItemClickListener;
import filo.mamdouh.kershhelper.features.dialogs.addtocalendardialog.PlanDialog;
import filo.mamdouh.kershhelper.features.mainappfeatures.home.Updater;
import filo.mamdouh.kershhelper.features.mainappfeatures.savedmeals.presenter.BookmarkPresenter;
import filo.mamdouh.kershhelper.models.Desserts;
import filo.mamdouh.kershhelper.models.MealsItem;
import filo.mamdouh.kershhelper.models.Repository;

public class BookmarkFragment extends Fragment implements BookmarkContract.View , OnItemClickListener {
    RecyclerView bookmarkRV;
    BookmarkPresenter presenter;
    SavedMealsAdapter adapter;
    View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BookmarkPresenter(this, Repository.getInstance(FileHandler.getInstance(getContext()),
                SavedMealsDataSourceImpl.getInstance(getContext()), RetrofitClient.getInstance(getContext()),
                SharedPrefrenceHandler.getInstance(getContext())));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_bookmark,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        bookmarkRV = view.findViewById(R.id.bookmarkRV);
        presenter.getSavedMeals();
        adapter = new SavedMealsAdapter(requireActivity(),this);
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
    public void addToCalendarListener(MealsItem meal) {
        PlanDialog dialog = new PlanDialog(getActivity(),meal);
        dialog.showDialog();
    }

    @Override
    public void addToCalendarListener(Desserts desserts) {
        //TODO
    }

    @Override
    public void saveItemListener(MealsItem mealsItem, Updater updater) {
        presenter.deleteMeal(mealsItem);
    }

    @Override
    public void saveItemListener(Desserts meal, Updater updater) {

    }

    @Override
    public void onItemClick(String mealID, boolean isSaved) {
        Bundle bundle = new Bundle();
        bundle.putString("mealID",mealID);
        bundle.putBoolean("isSaved",isSaved);
        Navigation.findNavController(view).navigate(R.id.action_bookmarkFragment_to_mealDetailsFragment,bundle);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}