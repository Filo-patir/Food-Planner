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
import filo.mamdouh.kershhelper.models.Categories;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ItemHolder> {
    private List<Categories.Category> categoryList;
    private SearchContract.Listener listener;
    private Context context;

    public CategoryAdapter(SearchContract.Listener listener, Context context) {
        categoryList = new ArrayList<>();
        this.listener = listener;
        this.context = context;
    }

    public void setCategoryList(List<Categories.Category> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(context).inflate(R.layout.category_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.txt.setText(categoryList.get(position).getName());
        Glide.with(context).load(categoryList.get(position).getImgurl()).placeholder(R.drawable.ic_launcher_foreground).into(holder.img);
        holder.item.setOnClickListener(l-> listener.onCategoryClick(categoryList.get(position).getName()));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        LinearLayout item;
        TextView txt;
        ImageView img;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.categoryItem);
            txt = itemView.findViewById(R.id.categoryItemName);
            img = itemView.findViewById(R.id.categoryItemImg);
        }
    }
}
