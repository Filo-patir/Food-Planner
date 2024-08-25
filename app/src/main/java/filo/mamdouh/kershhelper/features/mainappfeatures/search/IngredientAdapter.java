package filo.mamdouh.kershhelper.features.mainappfeatures.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.contracts.SearchContract;
import filo.mamdouh.kershhelper.models.IngredientsRoot;


public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ItemHolder> {
    List<IngredientsRoot.Ingredient> ingredientList;
    SearchContract.Listener listener;
    Context context;

    public IngredientAdapter(SearchContract.Listener listener, Context context) {
        ingredientList = new ArrayList<>();
        this.listener = listener;
        this.context = context;
    }

    public void setIngredientList(List<IngredientsRoot.Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(context).inflate(R.layout.ingredient_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        String name = ingredientList.get(position).getIngredientName();
        holder.txt.setText(name);
        Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+name+"-small.png").placeholder(R.drawable.ic_launcher_foreground).into(holder.img);
        holder.item.setOnClickListener(l-> listener.onIngredientClick(name));
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView txt;
        LinearLayout item;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.ingredientImg);
            txt = itemView.findViewById(R.id.ingredientItemName);
            item = itemView.findViewById(R.id.totalItem);
        }
    }
}
