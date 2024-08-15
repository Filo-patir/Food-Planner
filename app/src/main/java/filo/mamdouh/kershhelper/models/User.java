package filo.mamdouh.kershhelper.models;

import android.graphics.Bitmap;

import lombok.Data;

@Data
public class User {
    private String username;
    private String email;
    private String password;
    private Bitmap img;
}
