package filo.mamdouh.kershhelper.models;


import lombok.Data;

@Data
public class User {
    private String username;
    private String email;
    private String img;
    public User(String username, String email, String img) {
        this.username = username;
        this.email = email;
        this.img = img;
    }
    public User(){}
}
