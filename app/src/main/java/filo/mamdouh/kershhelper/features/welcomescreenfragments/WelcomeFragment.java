package filo.mamdouh.kershhelper.features.welcomescreenfragments;

import android.credentials.CredentialManager;
import android.credentials.CredentialOption;
import android.credentials.GetCredentialRequest;
import android.credentials.GetCredentialResponse;
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

import com.google.android.libraries.identity.googleid.GetGoogleIdOption;

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
            // * Deprecated & Dont know how to use the new Method
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
//                CredentialManager manager =  CredentialManager.;
//                GetGoogleIdOption googleIdOption = new GetGoogleIdOption.Builder()
//                        .setFilterByAuthorizedAccounts(true)
//                        .setServerClientId("149599402004-97u8vv25errhlgja9li7vvrsh41vd6uq.apps.googleusercontent.com").build();
//                Bundle data = new Bundle();
//                GetCredentialRequest request = new GetCredentialRequest.Builder(googleIdOption.getRequestData())
//                        .addCredentialOption(new CredentialOption.Builder("GMAIL",googleIdOption.getRequestData(), googleIdOption.getCandidateQueryData()).build())
//                        .build();
//                GetCredentialResponse response = ;
//            }

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