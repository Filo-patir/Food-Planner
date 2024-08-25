package filo.mamdouh.kershhelper.features.mainappfeatures.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import filo.mamdouh.kershhelper.contracts.HomeContract;
import filo.mamdouh.kershhelper.databinding.FragmentHomeScreenBinding;
import filo.mamdouh.kershhelper.datastorage.local.FileHandler;
import filo.mamdouh.kershhelper.datastorage.room.calendar.CalendarDataSourceImpl;
import filo.mamdouh.kershhelper.datastorage.room.savedmeals.SavedMealsDataSourceImpl;
import filo.mamdouh.kershhelper.features.mainappfeatures.BaseRecyclerViewAdapter;
import filo.mamdouh.kershhelper.features.mainappfeatures.addtocalendardialog.PlanDialog;
import filo.mamdouh.kershhelper.features.mainappfeatures.home.presenter.HomePresenter;
import filo.mamdouh.kershhelper.features.communicators.OnItemClickListener;
import filo.mamdouh.kershhelper.models.HomeFragmentRowData;
import filo.mamdouh.kershhelper.models.MealsItem;
import filo.mamdouh.kershhelper.models.Repostiry;


public class HomeScreenFragment extends Fragment implements HomeContract.View , OnItemClickListener {
    RecyclerView homeRecyclerView;
    FragmentHomeScreenBinding binding;
    BaseRecyclerViewAdapter adapter;
    HomePresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "onCreate: Home");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =FragmentHomeScreenBinding.inflate(inflater,container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new HomePresenter(this,Repostiry.getInstance(FileHandler.getInstance(getContext()), SavedMealsDataSourceImpl.getInstance(getContext()), CalendarDataSourceImpl.getInstance(getContext())));
        presenter.getHomeItems();
        homeRecyclerView = binding.homeScreenRecycleView;
        adapter = new BaseRecyclerViewAdapter(view.getContext(),this);
        homeRecyclerView.setAdapter(adapter);
    }
    @Override
    public void updateUI(HomeFragmentRowData item){
        adapter.setHomeFragmentRowDataList(item);
    }

    @Override
    public void onSave(String toast) {
        Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void addToCalendarListener(String mealID,String mealName) {
        PlanDialog dialog = new PlanDialog(getActivity(),mealID,mealName);
        dialog.showDialog();
    }


    @Override
    public void saveItemListener(MealsItem meal,Updater updater) {
        Log.d("TAG", "saveItemListener: HERE");
        presenter.saveMeal(meal,updater);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}