package filo.mamdouh.kershhelper.datastorage.firebase;


import android.util.Log;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

import filo.mamdouh.kershhelper.models.User;
import io.reactivex.rxjava3.core.Observable;

public class FirebaseFireStoreDB {
    private final FirebaseFirestore db;
    private static FirebaseFireStoreDB instance = null;

    private FirebaseFireStoreDB() {
        db = FirebaseFirestore.getInstance();
    }
    public static FirebaseFireStoreDB getInstance() {
        if(instance == null) instance = new FirebaseFireStoreDB();
        return instance;
    }
    public Observable<User> getUser(String uid){
        return Observable.create(emitter ->
            db.collection("user").document(uid).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    Log.d("Filo", "getUser: "+ task.getResult().getData());
                    Objects.requireNonNull(task.getResult().getData()).forEach((key, value) -> Log.d("Filo", "getUser: "+ key + " " + value));
                    Log.d("Filo", "getUser: "+ task.getResult().getString("username"));
                    emitter.onNext(new User(task.getResult().getString("username"),task.getResult().getString("email"),task.getResult().getString("img")));
                    emitter.onComplete();
                }
                else emitter.onError(task.getException());
            })
        );
    }

    public void saveUser(String uid, String name, String email,String img){
        db.collection("user").document(uid).set(new User(name,email,img)).addOnCompleteListener(task -> {
            if(task.isSuccessful()) Log.d("TAG", "saveUser: Success");
            else Log.d("Filo", "saveUser: "+ Objects.requireNonNull(task.getException()).getMessage());
        });
    }

}
