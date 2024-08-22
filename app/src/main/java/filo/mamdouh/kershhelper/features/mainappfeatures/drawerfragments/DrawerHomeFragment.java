package filo.mamdouh.kershhelper.features.mainappfeatures.drawerfragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.databinding.FragmentDrawerHomeBinding;
import filo.mamdouh.kershhelper.features.communicators.DrawerCommunicator;


public class DrawerHomeFragment extends Fragment implements DrawerCommunicator {
    private ImageView profileImg;
    private TextView userName,editProfileBtn,aboutUsBtn,logOutBtn;
    private FragmentDrawerHomeBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDrawerHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profileImg = binding.circleImageView;
        userName = binding.drawerUserName;
        editProfileBtn = binding.drawerEditProfileBtn;
        aboutUsBtn = binding.drawerAboutUs;
        logOutBtn = binding.logOut;

        aboutUsBtn.setOnClickListener(l-> Navigation.findNavController(view).navigate(R.id.action_drawerHomeFragment_to_aboutUsFragment));
        editProfileBtn.setOnClickListener(l->Navigation.findNavController(view).navigate(R.id.action_drawerHomeFragment_to_editProfileFragment));
    }

    @Override
    public void toHomepage() {

    }
}