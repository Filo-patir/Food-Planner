package filo.mamdouh.kershhelper.views.welcomescreenfragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import filo.mamdouh.kershhelper.HomeActivity;
import filo.mamdouh.kershhelper.Navigator;
import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.contracts.AuthContract;
import filo.mamdouh.kershhelper.databinding.FragmentWelcomeScreenBinding;
import filo.mamdouh.kershhelper.presenters.AuthPresenter;


public class WelcomeFragment extends Fragment implements AuthContract.View{
    FragmentWelcomeScreenBinding binding;
    ImageButton googleLogin,facebookLogin;
    Button signupBtn;
    TextView loginbtn,guestBtn;
    AuthPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AuthPresenter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWelcomeScreenBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        googleLogin = binding.googlebtn;
        facebookLogin = binding.facebookbtn;
        signupBtn = binding.welcomeSignup;
        loginbtn = binding.welcomeLoginBtn;
        guestBtn = binding.continueAsGuest;
        signupBtn.setOnClickListener(l->{
            Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_signUpFragment);
        });
        loginbtn.setOnClickListener(l-> Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_loginFragment));
        googleLogin.setOnClickListener(l->{
            presenter.googleLogin();
        });
        facebookLogin.setOnClickListener(l->{
            presenter.facebookLogin();
        });
        guestBtn.setOnClickListener(l->{
            Navigator.toActivity(getContext(), HomeActivity.class);
            getActivity().finish();
        });
        signupBtn.setOnClickListener(l-> Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_signUpFragment));
    }

    @Override
    public void onFailed(String message) {

    }

    @Override
    public void onSucess() {

    }
}