package filo.mamdouh.kershhelper.features.mainappfeatures;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.features.mainappfeatures.smallfoodcard.SmallCardAdapter;
import filo.mamdouh.kershhelper.models.HomeFragmentRowData;
import filo.mamdouh.kershhelper.features.mainappfeatures.mainfoodcard.FoodCardAdapter;
import filo.mamdouh.kershhelper.features.communicators.OnItemClickListener;
import filo.mamdouh.kershhelper.models.MealsItem;

public class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseRecyclerViewHolder> {
    HashMap<String,List<MealsItem>> homeFragmentRowDataList;
    Context context;
    OnItemClickListener listener;
    private static ArrayList<String> keys = new ArrayList<>(List.of("Daily Inspiration","Saved Meals","Desserts","Recently Viewed","More You Might Like"));
    public BaseRecyclerViewAdapter(Context context, OnItemClickListener listener){
        this.context = context;
        this.listener = listener;
        homeFragmentRowDataList = new HashMap<>();
    }

    public void setHomeFragmentRowDataList(HomeFragmentRowData item) {
        Log.d("TAG", "setHomeFragmentRowDataList: HERE");
        homeFragmentRowDataList.put(item.getTitle(), item.getItem());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseRecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.home_row_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder holder, int position) {
        Log.d("TAG", "onBindViewHolder: Bind"+position);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        holder.cardRecyclerView.setRecycledViewPool(viewPool);
        if (position<4) {
            holder.title.setText(keys.get(position));
            FoodCardAdapter foodCardAdapter = new FoodCardAdapter(homeFragmentRowDataList.get(keys.get(position)), context, listener);
            holder.cardRecyclerView.setAdapter(foodCardAdapter);
        }
        else {
            holder.title.setText(keys.get(position));
            SmallCardAdapter smallCardAdapter= new SmallCardAdapter(homeFragmentRowDataList.get(keys.get(position)), context, listener);
            GridLayoutManager layoutManager = new GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false);
            holder.cardRecyclerView.setLayoutManager(layoutManager);
            holder.cardRecyclerView.setAdapter(smallCardAdapter);
        }

    }

    @Override
    public int getItemCount() {
        return homeFragmentRowDataList.size();
    }

    public static class BaseRecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        RecyclerView cardRecyclerView;
        public BaseRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.homeRowItemName);
            cardRecyclerView = itemView.findViewById(R.id.homeRowRecycleView);
        }
    }
}
