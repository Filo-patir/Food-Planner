package filo.mamdouh.kershhelper.views.welcomescreenfragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.databinding.FragmentLoginBinding;
import filo.mamdouh.kershhelper.databinding.FragmentWelcomeScreenBinding;

public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
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
        Button loginbtn = binding.loginBtn;
        loginbtn.setOnClickListener(v -> {

        });
    }
}