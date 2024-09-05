package filo.mamdouh.kershhelper.datastorage.caching;

import androidx.annotation.NonNull;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Data
@RequiredArgsConstructor()
@AllArgsConstructor
public class Desserts {
    @Id
    public long id;
    private final String idMeal;
    private final String name;
    private final String areaFlag;
    private final int ingredientsCount;
    private final String thumbnail;
    private boolean isSaved = false;

    public boolean getIsSaved() {
        return isSaved;
    }

    @NonNull
    @Override
    public String toString(){
        return idMeal + " " + name + " " + areaFlag + " " + ingredientsCount + " " + thumbnail;
    }

}
