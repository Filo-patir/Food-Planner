package filo.mamdouh.kershhelper.models;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.lang.reflect.Field;
import java.util.ArrayList;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Data
@Entity(tableName = "meals")
public class MealsItem {
	@PrimaryKey @NonNull
	private String idMeal;
	private String strMeal;
	private String strArea;
	private String strCategory;
	private String strYoutube;
	private String strInstructions;
	private String strMealThumb;
	private String strIngredient1;
	private String strIngredient3;
	private String strIngredient2;
	private String strIngredient5;
	private String strIngredient4;
	private String strIngredient6;
	private String strIngredient7;
	private String strIngredient8;
	private String strIngredient9;
	private String strIngredient10;
	private String strIngredient11;
	private String strIngredient12;
	private String strIngredient13;
	private String strIngredient14;
	private String strIngredient15;
	private String strIngredient16;
	private String strIngredient18;
	private String strIngredient17;
	private String strIngredient19;
	private String strIngredient20;
	private String strMeasure1;
	private String strMeasure2;
	private String strMeasure3;
	private String strMeasure4;
	private String strMeasure5;
	private String strMeasure6;
	private String strMeasure7;
	private String strMeasure8;
	private String strMeasure9;
	private String strMeasure10;
	private String strMeasure11;
	private String strMeasure12;
	private String strMeasure13;
	private String strMeasure14;
	private String strMeasure15;
	private String strMeasure16;
	private String strMeasure17;
	private String strMeasure18;
	private String strMeasure19;
	private String strMeasure20;
	private String strSource;
	private boolean isSaved = false;
	private int quantity = 0;

	public ArrayList<String> getIngredients() {
		ArrayList<String> ingredients = new ArrayList<>();
		Field[] fields = this.getClass().getDeclaredFields();

		for (Field field : fields) {
			if (field.getName().startsWith("strIngredient")) {
				try {
					field.setAccessible(true);
					String value = (String) field.get(this);
					if (value != null && !value.isEmpty()) {
						ingredients.add(value);
					}
				} catch (IllegalAccessException e) {
					Log.d("Reflect Error", "getIngredients: " + e.getMessage());
				}
			}
		}
		return ingredients;
	}

	public ArrayList<String> getMeasures() {
		ArrayList<String> measures = new ArrayList<>();
		Field[] fields = this.getClass().getDeclaredFields();

		for (Field field : fields) {
			if (field.getName().startsWith("strMeasure")) {
				try {
					field.setAccessible(true);
					String value = (String) field.get(this);
					if (value != null && !value.isEmpty()) {
						measures.add(value);
					}
				} catch (IllegalAccessException e) {
					Log.d("Reflect Error", "getIngredients: " + e.getMessage());
				}
			}
		}
		return measures;
	}

	public String getMeasureAndIngredients(){
		StringBuilder result = new StringBuilder();
		ArrayList<String> ingredients = getIngredients();
		ArrayList<String> measures = getMeasures();
		for (int i = 0; i < ingredients.size(); i++) {
			if ((i+1)%2 == 0) result.append("\n");
			result.append(measures.get(i)).append(" ").append(ingredients.get(i)).append("\t\t");
		}
		return result.toString();
	}

	public String getStrInstructions() {
		StringBuilder instruction = new StringBuilder();
		for (String line : strInstructions.split("[.]"))
			instruction.append(line).append('.').append("\n");
		return instruction.toString();
	}

	public String getStrYoutube() {
		String youtubeID = strYoutube.substring(strYoutube.lastIndexOf("=") + 1);
		return String.format("https://www.youtube.com/embed/%s", youtubeID);
	}
}
