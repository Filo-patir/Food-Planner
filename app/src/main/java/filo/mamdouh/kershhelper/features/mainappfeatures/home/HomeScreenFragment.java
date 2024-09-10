package filo.mamdouh.kershhelper.features.mainappfeatures.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.contracts.HomeContract;
import filo.mamdouh.kershhelper.databinding.FragmentHomeScreenBinding;
import filo.mamdouh.kershhelper.datastorage.caching.Boxes;
import filo.mamdouh.kershhelper.datastorage.local.FileHandler;
import filo.mamdouh.kershhelper.datastorage.local.SharedPrefrenceHandler;
import filo.mamdouh.kershhelper.datastorage.network.RetrofitClient;
import filo.mamdouh.kershhelper.datastorage.room.calendar.CalendarDataSourceImpl;
import filo.mamdouh.kershhelper.datastorage.room.savedmeals.SavedMealsDataSourceImpl;
import filo.mamdouh.kershhelper.features.communicators.OnItemClickListener;
import filo.mamdouh.kershhelper.features.dialogs.addtocalendardialog.PlanDialog;
import filo.mamdouh.kershhelper.features.mainappfeatures.home.presenter.HomePresenter;
import filo.mamdouh.kershhelper.models.CachingRepositry;
import filo.mamdouh.kershhelper.models.Desserts;
import filo.mamdouh.kershhelper.models.HomeFragmentRowData;
import filo.mamdouh.kershhelper.models.MealsItem;
import filo.mamdouh.kershhelper.models.Repostiry;


public class HomeScreenFragment extends Fragment implements HomeContract.View , OnItemClickListener {
    RecyclerView homeRecyclerView;
    FragmentHomeScreenBinding binding;
    BaseRecyclerViewAdapter adapter;
    HomePresenter presenter;
    View view;
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
        this.view = view;
        presenter = new HomePresenter(this,Repostiry.getInstance(FileHandler.getInstance(getContext()),
                SavedMealsDataSourceImpl.getInstance(getContext()), CalendarDataSourceImpl.getInstance(getContext())
                , RetrofitClient.getInstance(getContext()), SharedPrefrenceHandler.getInstance(getContext())), CachingRepositry.getInstance(Boxes.getInstance(getContext())));
        homeRecyclerView = binding.homeScreenRecycleView;
        adapter = new BaseRecyclerViewAdapter(requireActivity(),this);
        homeRecyclerView.setAdapter(adapter);
        presenter.getHomeItems();
    }
    @Override
    public void updateUI(HomeFragmentRowData item){
        Log.d("Filo", "updateUI: 68   " +item.getTitle() );
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
    public void saveItemListener(Desserts meal, Updater updater) {
        presenter.saveItemListener(meal,updater);
    }

    @Override
    public void onItemClick(String mealID, boolean isSaved) {
        Bundle bundle = new Bundle();
        bundle.putString("mealID",mealID);
        bundle.putBoolean("isSaved",isSaved);
        Navigation.findNavController(view).navigate(R.id.action_homeScreenFragment_to_mealDetailsFragment,bundle);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Filo", "onDestroy: ");
        presenter.onDestroy();
    }
}