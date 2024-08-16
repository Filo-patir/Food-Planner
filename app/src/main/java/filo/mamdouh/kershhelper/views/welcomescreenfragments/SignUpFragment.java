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
import android.widget.TextView;

import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.databinding.FragmentSignUpBinding;
import filo.mamdouh.kershhelper.databinding.FragmentWelcomeScreenBinding;

public class SignUpFragment extends Fragment {
    FragmentSignUpBinding binding;
    EditText displayname,email,password,confirmpassword;
    Button signupBtn;
    TextView loginBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        displayname = binding.signUpName;
        email = binding.signUpEmail;
        password = binding.signUpPassword;
        confirmpassword = binding.signUpConfirmpass;
        signupBtn = binding.signupbtn;
        loginBtn = binding.signupLoginBtn;
        loginBtn.setOnClickListener(l-> Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_loginFragment));

    }
}