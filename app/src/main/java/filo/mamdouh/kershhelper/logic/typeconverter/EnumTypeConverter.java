package filo.mamdouh.kershhelper.logic.typeconverter;

import android.util.Log;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import filo.mamdouh.kershhelper.models.DaysOfWeek;


public class EnumTypeConverter {
    @TypeConverter
    public String fromArrayList(ArrayList<DaysOfWeek> days){
        return new Gson().toJson(days);
    }
    @TypeConverter
    public ArrayList<DaysOfWeek> toArrayList(String json){
        Log.d("Filo", "toArrayList: "+json);
        TypeToken<ArrayList<DaysOfWeek>> days = new TypeToken<ArrayList<DaysOfWeek>>(){};
        return new Gson().fromJson(json,days.getType());
    }
}
