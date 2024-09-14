package filo.mamdouh.kershhelper.features.mainappfeatures.drawer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;

import filo.mamdouh.kershhelper.MainActivity;
import filo.mamdouh.kershhelper.Navigator;
import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.contracts.DrawerContract;
import filo.mamdouh.kershhelper.databinding.FragmentDrawerHomeBinding;
import filo.mamdouh.kershhelper.datastorage.local.FileHandler;
import filo.mamdouh.kershhelper.datastorage.local.SharedPrefrenceHandler;
import filo.mamdouh.kershhelper.datastorage.network.RetrofitClient;
import filo.mamdouh.kershhelper.datastorage.room.savedmeals.SavedMealsDataSourceImpl;
import filo.mamdouh.kershhelper.features.communicators.DrawerCommunicator;
import filo.mamdouh.kershhelper.features.dialogs.guestdialog.GuestDialog;
import filo.mamdouh.kershhelper.features.mainappfeatures.drawer.presenters.DrawerPresenter;
import filo.mamdouh.kershhelper.models.Client;
import filo.mamdouh.kershhelper.models.Repository;
import filo.mamdouh.kershhelper.models.User;


public class DrawerHomeFragment extends Fragment implements DrawerCommunicator, DrawerContract.View {
    private FragmentDrawerHomeBinding binding;
    private DrawerPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDrawerHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new DrawerPresenter(this, Repository.getInstance(FileHandler.getInstance(requireContext()), SavedMealsDataSourceImpl.getInstance(requireContext()),
                RetrofitClient.getInstance(requireContext()), SharedPrefrenceHandler.getInstance(getContext())));
        ImageView profileImg = binding.circleImageView;
        TextView userName = binding.drawerUserName;
        TextView editProfileBtn = binding.drawerEditProfileBtn;
        TextView aboutUsBtn = binding.drawerAboutUs;
        TextView logOutBtn = binding.logOut;
        Glide.with(requireContext()).load(Client.getInstance(null, null).getUserImg()).placeholder(R.drawable.profile_icon).into(profileImg);
        String name = Client.getInstance("",new User()).getUserName();
        Log.d("Filo", "onViewCreated: " + name + " " + Client.getInstance("",new User()).getUserName());
        if(name == null || name.isEmpty()) {
            name = "Guest";
            editProfileBtn.setOnClickListener(l-> new GuestDialog(requireActivity()).showDialog());
        }
        else
            editProfileBtn.setOnClickListener(l->Navigation.findNavController(view).navigate(R.id.action_drawerHomeFragment_to_editProfileFragment));
        userName.setText(name);
        aboutUsBtn.setOnClickListener(l-> Navigation.findNavController(view).navigate(R.id.action_drawerHomeFragment_to_aboutUsFragment));
        logOutBtn.setOnClickListener(l-> presenter.logOut());
    }

    @Override
    public void toHomepage() {

    }

    @Override
    public void logOut() {
        Navigator.toActivity(getContext(), new MainActivity());
    }
}