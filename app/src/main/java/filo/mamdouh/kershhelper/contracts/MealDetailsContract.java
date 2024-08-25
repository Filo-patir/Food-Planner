package filo.mamdouh.kershhelper.contracts;

import filo.mamdouh.kershhelper.models.MealsItem;

public interface MealDetailsContract {
    interface View{
        void updateUI(MealsItem meal);

        void updateSaveBtn(boolean b);
    }
}
