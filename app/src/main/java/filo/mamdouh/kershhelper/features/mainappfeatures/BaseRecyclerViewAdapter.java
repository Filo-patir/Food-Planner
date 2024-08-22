package filo.mamdouh.kershhelper.features.mainappfeatures;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.models.HomeFragmentRowData;
import filo.mamdouh.kershhelper.features.mainappfeatures.mainfoodcard.FoodCardAdapter;
import filo.mamdouh.kershhelper.features.mainappfeatures.mainfoodcard.OnItemClickListener;
import lombok.Setter;

public class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseRecyclerViewHolder> {
    HashMap<String,List<HomeFragmentRowData.ItemData>> homeFragmentRowDataList;
    ArrayList<String> keys;
    Context context;
    OnItemClickListener listener;
    public BaseRecyclerViewAdapter(Context context, OnItemClickListener listener){
        this.context = context;
        this.listener = listener;
        keys = new ArrayList<>();
        homeFragmentRowDataList = new HashMap<>();
    }

    public void setHomeFragmentRowDataList(HomeFragmentRowData item) {
        Log.d("TAG", "setHomeFragmentRowDataList: HERE");
        keys.add(item.getTitle());
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
        String key = keys.get(position);
        Log.d("TAG", "onBindViewHolder: Bind");
        holder.title.setText(key);
        if(position<6){
            FoodCardAdapter foodCardAdapter = new FoodCardAdapter(homeFragmentRowDataList.get(key),context,listener);
            holder.cardRecyclerView.setAdapter(foodCardAdapter);
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
