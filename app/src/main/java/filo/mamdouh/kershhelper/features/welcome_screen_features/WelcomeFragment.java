package filo.mamdouh.kershhelper.features.welcome_screen_features;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import filo.mamdouh.kershhelper.HomeActivity;
import filo.mamdouh.kershhelper.Navigator;
import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.contracts.AuthContract;
import filo.mamdouh.kershhelper.databinding.FragmentWelcomeScreenBinding;
import filo.mamdouh.kershhelper.models.Client;
import filo.mamdouh.kershhelper.models.User;
import filo.mamdouh.kershhelper.presenters.AuthPresenter;


public class WelcomeFragment extends Fragment implements AuthContract.View{
    FragmentWelcomeScreenBinding binding;
    ImageButton googleLogin,facebookLogin;
    Button signupBtn;
    TextView loginBtn,guestBtn;
    AuthPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AuthPresenter(this);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWelcomeScreenBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in);
        Group group = binding.group;
        group.startAnimation(animation);
        googleLogin = binding.googlebtn;
        facebookLogin = binding.facebookbtn;
        signupBtn = binding.welcomeSignup;
        loginBtn = binding.welcomeLoginBtn;
        guestBtn = binding.continueAsGuest;
        signupBtn.setOnClickListener(l->
            Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_signUpFragment)
        );
        loginBtn.setOnClickListener(l-> Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_loginFragment));
        googleLogin.setOnClickListener(l->{
            // * Deprecated & Dont know how to use the new Method
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                Log.d("Filo", "Gooogle: ");
//                CredentialManager manager = CredentialManager.create(requireContext());
//                GetGoogleIdOption googleIdOption = new GetGoogleIdOption.Builder()
//                        .setFilterByAuthorizedAccounts(true)
//                        .setServerClientId(getString(R.string.default_web_client_id))
//                        .build();
            }

        });
        facebookLogin.setOnClickListener(l-> presenter.facebookLogin());
        guestBtn.setOnClickListener(l-> {
            Client.getInstance("",new User("","",""));
            Navigator.toActivity(getContext(), new HomeActivity());
        });
        signupBtn.setOnClickListener(l-> Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_signUpFragment));
    }

    @Override
    public void onFailed(String message) {

    }

    @Override
    public void onSuccess() {
    }

}