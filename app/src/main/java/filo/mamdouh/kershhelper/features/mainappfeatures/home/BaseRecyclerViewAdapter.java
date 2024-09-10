package filo.mamdouh.kershhelper.features.mainappfeatures.home;

import android.app.Activity;
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
import filo.mamdouh.kershhelper.features.communicators.OnItemClickListener;
import filo.mamdouh.kershhelper.features.mainappfeatures.mainfoodcard.FoodCardAdapter;
import filo.mamdouh.kershhelper.features.mainappfeatures.smallfoodcard.SmallCardAdapter;
import filo.mamdouh.kershhelper.models.Desserts;
import filo.mamdouh.kershhelper.models.HomeFragmentRowData;
import filo.mamdouh.kershhelper.models.MealsItem;

public class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseRecyclerViewHolder> {
    HashMap<String,List<MealsItem>> homeFragmentRowDataList;
    ArrayList<Desserts> desserts;
    Activity context;
    OnItemClickListener listener;
    private int counter= 0;
    private static final ArrayList<String> keys = new ArrayList<>(List.of("Daily Inspiration","Saved Meals","Desserts","Recently Viewed","More You Might Like"));
    public BaseRecyclerViewAdapter(Activity context, OnItemClickListener listener){
        this.context = context;
        this.listener = listener;
        homeFragmentRowDataList = new HashMap<>();
        desserts = new ArrayList<>();
    }

    public void setHomeFragmentRowDataList(HomeFragmentRowData item) {
        counter++;
        if (item.getDesserts().isEmpty()) {
            homeFragmentRowDataList.put(item.getTitle(), item.getItem());
        }
        else desserts = item.getDesserts();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseRecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.home_row_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder holder, int position) {
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        holder.cardRecyclerView.setRecycledViewPool(viewPool);
        if (position<4) {
            if (position == 2 && !desserts.isEmpty()){
                holder.title.setText(keys.get(position));
                FoodCardAdapter foodCardAdapter = new FoodCardAdapter(desserts, context, listener);
                holder.cardRecyclerView.setAdapter(foodCardAdapter);
            }
            else {
                holder.title.setText(keys.get(position));
                FoodCardAdapter foodCardAdapter = new FoodCardAdapter(homeFragmentRowDataList.get(keys.get(position)), context, listener);
                holder.cardRecyclerView.setAdapter(foodCardAdapter);
            }
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
        return counter;
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
