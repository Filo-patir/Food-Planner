package filo.mamdouh.kershhelper.contracts;

import java.util.List;

import filo.mamdouh.kershhelper.models.HomeFragmentRowData;

public interface HomeContract {
    interface View{
        void updateUI(HomeFragmentRowData item);
    }
}