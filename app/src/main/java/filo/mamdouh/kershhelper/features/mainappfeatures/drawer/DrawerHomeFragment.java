package filo.mamdouh.kershhelper.features.mainappfeatures.drawer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import filo.mamdouh.kershhelper.MainActivity;
import filo.mamdouh.kershhelper.Navigator;
import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.contracts.DrawerContract;
import filo.mamdouh.kershhelper.databinding.FragmentDrawerHomeBinding;
import filo.mamdouh.kershhelper.features.communicators.DrawerCommunicator;
import filo.mamdouh.kershhelper.features.mainappfeatures.drawer.presenters.DrawerPresenter;
import filo.mamdouh.kershhelper.models.Client;
import filo.mamdouh.kershhelper.models.Repostiry;
import filo.mamdouh.kershhelper.models.User;


public class DrawerHomeFragment extends Fragment implements DrawerCommunicator, DrawerContract.View {
    private ImageView profileImg;
    private TextView userName,editProfileBtn,aboutUsBtn,logOutBtn;
    private FragmentDrawerHomeBinding binding;
    private DrawerPresenter presenter;
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
        presenter = new DrawerPresenter(this, Repostiry.getInstance(null,null,null,null,null));
        profileImg = binding.circleImageView;
        userName = binding.drawerUserName;
        editProfileBtn = binding.drawerEditProfileBtn;
        aboutUsBtn = binding.drawerAboutUs;
        logOutBtn = binding.logOut;
        String name = null;
        if (Client.getInstance(null, null).getUserName().isEmpty())
             name = Client.getInstance("",new User()).getUserName();
        Log.d("Filo", "onViewCreated: " + name + " " + Client.getInstance("",new User()).getUserName());
        if(name == null || name.isEmpty()) name = "Guest";
        userName.setText(name);
        aboutUsBtn.setOnClickListener(l-> Navigation.findNavController(view).navigate(R.id.action_drawerHomeFragment_to_aboutUsFragment));
        editProfileBtn.setOnClickListener(l->Navigation.findNavController(view).navigate(R.id.action_drawerHomeFragment_to_editProfileFragment));
        logOutBtn.setOnClickListener(l-> {
            presenter.logOut();
        });
    }

    @Override
    public void toHomepage() {

    }

    @Override
    public void logOut() {
        Navigator.toActivity(getContext(), new MainActivity());
    }
}