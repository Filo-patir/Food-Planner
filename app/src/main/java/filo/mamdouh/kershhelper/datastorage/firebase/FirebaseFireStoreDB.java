package filo.mamdouh.kershhelper.datastorage.firebase;


import com.google.firebase.firestore.FirebaseFirestore;

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
    
}
