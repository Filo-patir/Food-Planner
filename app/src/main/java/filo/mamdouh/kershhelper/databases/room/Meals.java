package filo.mamdouh.kershhelper.databases.room;

import androidx.room.Entity;

import java.util.ArrayList;

@Entity(tableName = "meals")
public class Meals {
    private String mealid;
    private String name;
    private String category;
    private String area;
    private String src;
    private ArrayList<String> ingredients;
    private ArrayList<String> measures;
    private String instructions;
    private String imgSrc;
    private String youtubeLink;
    private boolean isSaved;
    private ArrayList<String> week;
}
