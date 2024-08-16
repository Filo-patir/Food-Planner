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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.databinding.FragmentLoginBinding;
import filo.mamdouh.kershhelper.databinding.FragmentWelcomeScreenBinding;

public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
    Button loginbtn;
    ImageButton googleLoginBtn,facebookLoginBtn;
    EditText emailtxt,passwordtxt;
    TextView signupBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginbtn = binding.loginBtn;
        googleLoginBtn = binding.loginGooglebtn;
        facebookLoginBtn = binding.loginFacebookBtn;
        emailtxt = binding.loginEmail;
        passwordtxt = binding.loginPassword;
        signupBtn = binding.loginSignupBtn;
        loginbtn.setOnClickListener(v -> {
        });
        signupBtn.setOnClickListener(l -> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signUpFragment));
    }
}