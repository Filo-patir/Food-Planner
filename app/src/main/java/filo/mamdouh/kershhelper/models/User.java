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
    private Bitmap img;

    public User(String uid, String username, String email, String imgurl) {
        this.uid = uid;
        this.username = username;
        this.email = email;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(imgurl).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                InputStream inputStream = response.body().byteStream();
                img = BitmapFactory.decodeStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
