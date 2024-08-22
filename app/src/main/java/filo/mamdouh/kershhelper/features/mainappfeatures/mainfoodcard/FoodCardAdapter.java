package filo.mamdouh.kershhelper.features.mainappfeatures.mainfoodcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.List;

import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.models.HomeFragmentRowData;
import filo.mamdouh.kershhelper.models.Repostiry;


public class FoodCardAdapter extends RecyclerView.Adapter<FoodCardHolder> {
    private List<HomeFragmentRowData.ItemData> items;
    private Context context;
    private OnItemClickListener listener;
    public FoodCardAdapter(List<HomeFragmentRowData.ItemData> items,Context context,OnItemClickListener listener) {
        this.items = items;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FoodCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodCardHolder(LayoutInflater.from(context).inflate(R.layout.food_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodCardHolder holder, int position) {
        HomeFragmentRowData.ItemData item = items.get(position);
        holder.mealname.setText(item.getMealName());
        holder.ingredients.setText(item.getIngredientsNumber() + " Ingredients");
        String imgUrl = Repostiry.getCOUNTERIES().get(item.getAreaImg());
        Glide.with(context).load(imgUrl).placeholder(R.drawable.unknown_flag_icon).into(holder.areaFlag);
        Glide.with(context).load(item.getMealImg()).placeholder(R.drawable.ic_launcher_background).into(holder.backgroundImg);
        holder.addToCalendarBtn.setOnClickListener(l-> listener.addToCalendarListener());
        holder.saveBtn.setOnClickListener(l->listener.saveItemListener());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
