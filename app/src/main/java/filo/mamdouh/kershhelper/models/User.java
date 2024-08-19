package filo.mamdouh.kershhelper.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

import lombok.Data;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Data
public class User {
    private String uid;
    private String username;
    private String email;
    private String img;

    public User(String uid, String username, String email, String img) {
        this.uid = uid;
        this.username = username;
        this.email = email;
        this.img = img;
    }
}
