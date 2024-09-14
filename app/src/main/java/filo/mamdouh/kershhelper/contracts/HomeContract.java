package filo.mamdouh.kershhelper.contracts;

import filo.mamdouh.kershhelper.models.HomeFragmentRowData;

public interface HomeContract {
    interface View{
        void updateUI(HomeFragmentRowData item);
        void onSave(String toast);
    }
    interface Activity {
        void updateSavedNumberI(int n);
        void updateToolBarStatus(int n);
        void showToast(String text);
    }
}