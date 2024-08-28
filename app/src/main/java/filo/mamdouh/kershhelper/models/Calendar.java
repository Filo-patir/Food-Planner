package filo.mamdouh.kershhelper.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity(tableName = "calendar")
public class Calendar {
    @PrimaryKey @NonNull
    private String mealID;
    private boolean saturday = false;
    private boolean sunday= false;
    private boolean monday= false;
    private boolean tuesday= false;
    private boolean wednesday= false;
    private boolean thursday= false;
    private boolean friday= false;
    public Calendar(String mealID, ArrayList<String> days){
        this.mealID = mealID;
        for (String day : days){
            switch (day){
                case "Saturday":
                    saturday = true;
                    break;
                case "Sunday":
                    sunday = true;
                    break;
                case "Monday":
                    monday = true;
                    break;
                case "Tuesday":
                    tuesday = true;
                    break;
                case "Wednesday":
                    wednesday = true;
                    break;
                case "Thursday":
                    thursday = true;
                    break;
                case "Friday":
                    friday = true;
            }
        }
    }
    public List<Boolean> getWeek(){
        return List.of(saturday,sunday,monday,tuesday,wednesday,thursday,friday);
    }
}
