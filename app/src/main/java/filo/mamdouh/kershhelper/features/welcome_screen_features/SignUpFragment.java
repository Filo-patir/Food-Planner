package filo.mamdouh.kershhelper.features.welcome_screen_features;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;

import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.contracts.AuthContract;
import filo.mamdouh.kershhelper.databinding.FragmentSignUpBinding;
import filo.mamdouh.kershhelper.presenters.AuthPresenter;

public class SignUpFragment extends Fragment implements AuthContract.View {
    FragmentSignUpBinding binding;
    View view;
    EditText displayname,email,password,confirmpassword;
    Button signupBtn;
    TextView loginBtn;
    AuthPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AuthPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        FirebaseApp.initializeApp(requireContext());
        displayname = binding.signUpName;
        email = binding.signUpEmail;
        password = binding.signUpPassword;
        confirmpassword = binding.signUpConfirmpass;
        signupBtn = binding.signupbtn;
        loginBtn = binding.signupLoginBtn;
        loginBtn.setOnClickListener(l-> Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_loginFragment));
        signupBtn.setOnClickListener(l->{
            presenter.onSignup(displayname.getText().toString(),email.getText().toString(),password.getText().toString());
        });
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSucess() {
        Toast.makeText(getContext(), "Sign Up Succesfuly", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_loginFragment);
    }
}