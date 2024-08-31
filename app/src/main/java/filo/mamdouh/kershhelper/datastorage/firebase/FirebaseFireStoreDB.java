package filo.mamdouh.kershhelper.datastorage.firebase;


import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import filo.mamdouh.kershhelper.models.User;
import io.reactivex.rxjava3.core.Observable;

public class FirebaseFireStoreDB {
    private FirebaseFirestore db;
    private static FirebaseFireStoreDB innstance = null;

    private FirebaseFireStoreDB() {
        db = FirebaseFirestore.getInstance();
    }
    public static FirebaseFireStoreDB getInnstance() {
        if(innstance == null) innstance= new FirebaseFireStoreDB();
        return innstance;
    }
    public Observable<User> getUser(String uid){
        return Observable.create(emitter -> {
            db.collection("user").document(uid).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    Log.d("Filo", "getUser: "+ task.getResult().getData());
                    task.getResult().getData().forEach((key, value) -> Log.d("Filo", "getUser: "+ key + " " + value));
                    Log.d("Filo", "getUser: "+ task.getResult().getString("username"));
                    emitter.onNext(new User(task.getResult().getString("username"),task.getResult().getString("email"),task.getResult().getString("img")));
                    emitter.onComplete();
                }
                else emitter.onError(task.getException());
            });
        });
    }

    public void saveUser(String uid, String name, String email,String img){
        db.collection("user").document(uid).set(new User(name,email,img)).addOnCompleteListener(task -> {
            if(task.isSuccessful()) Log.d("TAG", "saveUser: Success");
            else Log.d("Filo", "saveUser: "+task.getException().getMessage());;
        });
    }

}