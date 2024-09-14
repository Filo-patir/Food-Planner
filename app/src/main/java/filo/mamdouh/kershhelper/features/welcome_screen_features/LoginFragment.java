package filo.mamdouh.kershhelper.features.welcome_screen_features;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import filo.mamdouh.kershhelper.HomeActivity;
import filo.mamdouh.kershhelper.Navigator;
import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.contracts.LoginContract;
import filo.mamdouh.kershhelper.databinding.FragmentLoginBinding;
import filo.mamdouh.kershhelper.datastorage.local.FileHandler;
import filo.mamdouh.kershhelper.datastorage.local.SharedPrefrenceHandler;
import filo.mamdouh.kershhelper.datastorage.network.RetrofitClient;
import filo.mamdouh.kershhelper.datastorage.room.savedmeals.SavedMealsDataSourceImpl;
import filo.mamdouh.kershhelper.features.welcome_screen_features.presenters.LoginPresenter;
import filo.mamdouh.kershhelper.models.Repository;

public class LoginFragment extends Fragment implements LoginContract.View {
    FragmentLoginBinding binding;
    View view;
    Button loginbtn;
    ImageButton googleLoginBtn,facebookLoginBtn;
    EditText emailtxt,passwordtxt;
    TextView signupBtn;
    LoginPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginPresenter(this, Repository.getInstance(FileHandler.getInstance(getContext()),
                SavedMealsDataSourceImpl.getInstance(getContext()), RetrofitClient.getInstance(getContext()), SharedPrefrenceHandler.getInstance(getContext())));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
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
        loginbtn.setOnClickListener(v ->
            presenter.onLogin(emailtxt.getText().toString(),passwordtxt.getText().toString())
        );
        signupBtn.setOnClickListener(l -> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signUpFragment));
    }



    @Override
    public void onSuccess() {
        Toast.makeText(getContext(), "Logged in Successfuly", Toast.LENGTH_SHORT).show();
        Navigator.toActivity(getContext(), new HomeActivity());
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}