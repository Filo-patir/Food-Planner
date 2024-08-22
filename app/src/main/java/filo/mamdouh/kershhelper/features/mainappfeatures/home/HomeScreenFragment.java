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

import java.util.List;

import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.contracts.HomeContract;
import filo.mamdouh.kershhelper.databinding.FragmentHomeScreenBinding;
import filo.mamdouh.kershhelper.features.mainappfeatures.BaseRecyclerViewAdapter;
import filo.mamdouh.kershhelper.features.mainappfeatures.home.presenter.HomePresenter;
import filo.mamdouh.kershhelper.features.mainappfeatures.mainfoodcard.OnItemClickListener;
import filo.mamdouh.kershhelper.models.HomeFragmentRowData;


public class HomeScreenFragment extends Fragment implements HomeContract.View {
    RecyclerView homeRecyclerView;
    FragmentHomeScreenBinding binding;
    OnItemClickListener listener;
    BaseRecyclerViewAdapter adapter;
    HomePresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "onCreate: Home");
        presenter = new HomePresenter();
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
        presenter.getHomeItems(this);
        homeRecyclerView = binding.homeScreenRecycleView;
        adapter = new BaseRecyclerViewAdapter(view.getContext(),listener);
        homeRecyclerView.setAdapter(adapter);
    }
    @Override
    public void updateUI(HomeFragmentRowData item){
        adapter.setHomeFragmentRowDataList(item);
    }
}