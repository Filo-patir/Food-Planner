package filo.mamdouh.kershhelper.views.mainappfragments;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import filo.mamdouh.kershhelper.models.HomeFragmentRowData;

public class BaseRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final int CATEGORY = 0;
     final int AREA = 1;
     final int MORE_YOU_MIGHT_LIKE = 2;
     final int DAILY_INSPIRATION = 3;
     final int HEADER_TEXT = 4;
     final int SAVED_MEALS = 5;
     final int DESSERTS = 6;
     final int RECENTLY_VIEWED = 7;
     static boolean flag;
    ArrayList<HomeFragmentRowData> homeFragmentRowDataList;
    ArrayList<HomeFragmentRowData.ItemData>itemList;
    Context context;

    BaseRecyclerViewAdapter(Context context, ArrayList items){
        this.context = context;
        if(items.getClass().getComponentType() == HomeFragmentRowData.class){
            flag = true;
            homeFragmentRowDataList = items;
        }
        else{
            flag = false;
            itemList = items;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
