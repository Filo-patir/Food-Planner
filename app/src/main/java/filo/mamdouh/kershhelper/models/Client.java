package filo.mamdouh.kershhelper.models;

public class Client {
    private final String uid;
    private final User user;
    private static Client instance =null;
    private Client(String uid, User user){
        this.user = user;
        this.uid = uid;
    }

    public static Client getInstance(String uid, User user) {
        if (instance == null) {
            instance = new Client(uid, user);
        }
        return instance;
    }
    public String getUserID(){
        return uid;
    }
    public String getUserName(){
        return user.getUsername();
    }
    public String getUserEmail(){
        return user.getEmail();
    }
    public String getUserImg(){
        return user.getImg();
    }
}
