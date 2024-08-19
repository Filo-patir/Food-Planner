package filo.mamdouh.kershhelper.models;

import lombok.Getter;
import lombok.Setter;

public class Repostiry {
    @Getter
    @Setter
    private User user;
    private static Repostiry repostiry = null;
    private Repostiry(){
    }

    public static Repostiry getInstance() {
        if(repostiry==null) repostiry = new Repostiry();
        return repostiry;
    }
}
