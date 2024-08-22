package filo.mamdouh.kershhelper.features.welcomescreenfragments;

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
import android.widget.Toast;

import filo.mamdouh.kershhelper.HomeActivity;
import filo.mamdouh.kershhelper.Navigator;
import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.contracts.AuthContract;
import filo.mamdouh.kershhelper.databinding.FragmentLoginBinding;
import filo.mamdouh.kershhelper.presenters.AuthPresenter;

public class LoginFragment extends Fragment implements AuthContract.View {
    FragmentLoginBinding binding;
    View view;
    Button loginbtn;
    ImageButton googleLoginBtn,facebookLoginBtn;
    EditText emailtxt,passwordtxt;
    TextView signupBtn;
    AuthPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AuthPresenter(this);
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
        this.view = view;
        loginbtn = binding.loginBtn;
        googleLoginBtn = binding.loginGooglebtn;
        facebookLoginBtn = binding.loginFacebookBtn;
        emailtxt = binding.loginEmail;
        passwordtxt = binding.loginPassword;
        signupBtn = binding.loginSignupBtn;
        loginbtn.setOnClickListener(v -> {
            presenter.onLogin(emailtxt.getText().toString(),passwordtxt.getText().toString());
        });
        signupBtn.setOnClickListener(l -> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signUpFragment));
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSucess() {
        Toast.makeText(getContext(), "Logged in Successfuly", Toast.LENGTH_SHORT).show();
        Navigator.toActivity(getContext(), HomeActivity.class);
        getActivity().finish();
    }
}