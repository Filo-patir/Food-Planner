package filo.mamdouh.kershhelper.features.mainappfeatures.calendar;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.contracts.CalendarContract;
import filo.mamdouh.kershhelper.models.MealsItem;

public class BaseCalendarAdapter extends RecyclerView.Adapter<BaseCalendarAdapter.BaseCalendarHolder> {
    public static final List<String> KEYS = List.of("Saturday","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday");
    Map<String, List<MealsItem>> calendarRowArrayList;
    private Context context;
    private CalendarContract.Listner listener;
    ChildCalendarAdapter adapter;
    public BaseCalendarAdapter( Context context, CalendarContract.Listner listener) {
        this.calendarRowArrayList = new HashMap<>();
        this.context = context;
        this.listener = listener;
    }
    public void setCalendarRowArrayList(String key , List<MealsItem> item){
        calendarRowArrayList.put(key,item);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public BaseCalendarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseCalendarHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_row_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseCalendarHolder holder, int position) {
        Log.d("Filo", "onBindViewHolder: "+calendarRowArrayList.get(position));
            holder.title.setText(KEYS.get(position));
            adapter = new ChildCalendarAdapter(calendarRowArrayList.get(KEYS.get(position)),context,listener);
            holder.childRecyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return calendarRowArrayList.size();
    }

    public class BaseCalendarHolder extends RecyclerView.ViewHolder {
        TextView title;
        RecyclerView childRecyclerView;
        public BaseCalendarHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.calendarDay);
            childRecyclerView = itemView.findViewById(R.id.calendarRowRV);
        }
    }
}
