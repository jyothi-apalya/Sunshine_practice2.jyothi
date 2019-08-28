package com.example.menu;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menu.Data.Header_data_model;
import com.example.menu.Data.RecyclerViewItems;
import com.example.menu.Data.WethearDetailsmodel;

import java.util.List;

public class WetherRecyclerAdapter extends RecyclerView.Adapter {

    private final WetherRecyclerAdapter.ForecastAdapterOnClickListener mclickListener;

   public List<RecyclerViewItems> recyclerViewItems;
    private static final int HEADER_ITEM = 0;
    private static final int ROW_ITEM = 1;
    Context context;
    public WetherRecyclerAdapter(List<RecyclerViewItems> wethearDetailsmodelList_header_view, WetherRecyclerAdapter.ForecastAdapterOnClickListener clickListener) {
      this.recyclerViewItems = wethearDetailsmodelList_header_view;
      Log.d("TAG","array_size"+recyclerViewItems.size());
        this.mclickListener=clickListener;
        this.context=context;

    }


    public interface ForecastAdapterOnClickListener {
        void onItemClickListener(int onClickPostion, String weatherForDay, double pressure, double humidity, double wind, double maximum_temp, double minimum_temp,String date);
    }

    @Override
    public int getItemViewType(int position) {
        Log.d("TAG","GETITEM");
        RecyclerViewItems recyclerViewItem = recyclerViewItems.get(position);
        Log.d("item","postion"+recyclerViewItem);
        if(recyclerViewItem instanceof Header_data_model){
            Log.d("tag","ROW_ITEM"+HEADER_ITEM);
            return HEADER_ITEM;
        }
        else {
            if (recyclerViewItem instanceof WethearDetailsmodel) {
                Log.d("tag","ROW_ITEM"+ROW_ITEM);
                return ROW_ITEM;
            } else
                Log.d("tag","RETURN"+super.getItemViewType(position));
                return super.getItemViewType(position);

        }

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("TAG","ONCREATE");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row;
        if (viewType == HEADER_ITEM) {
            Log.d("tag", "header");
            row = inflater.inflate(R.layout.recyler_header_view, parent, false);
            return new HeaderHolder(row);
        }
        else {
            if (viewType == ROW_ITEM) {
                Log.d("tag", "row");
                row = inflater.inflate(R.layout.list_item, parent, false);
                return new RowHolder(row);
            }
        }

            return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("TAG","ONBIND");
        RecyclerViewItems recyclerViewItem = recyclerViewItems.get(position);

        if (holder instanceof HeaderHolder) {
            HeaderHolder headerHolder = (HeaderHolder) holder;
            Header_data_model header_data_model = (Header_data_model) recyclerViewItem;
            headerHolder.header_temp_max.setText((int)header_data_model.getHeader_temp_maximum() +"°");
            headerHolder.header_temp_min.setText((int)header_data_model.getHeader_temp_minimum() +"°");
            headerHolder.header_descryption.setText(header_data_model.getDiscryption());
            headerHolder.today_date.setText(header_data_model.getHeader_date());
            Log.d("TAG","HEADER"+header_data_model.getHeader_temp_maximum());


        } else {
            if (holder instanceof RowHolder) {
                RowHolder rowHolder = (RowHolder) holder;
                WethearDetailsmodel wethearDetailsmodel = (WethearDetailsmodel) recyclerViewItem;
                rowHolder.wetheardata.setText(wethearDetailsmodel.getDICRYPTION());
                Log.d("TAG","CHECKINGDATA"+wethearDetailsmodel.getRow_temp_max());
                rowHolder.high_temp2.setText((int)wethearDetailsmodel.getRow_temp_max() + "°");
                rowHolder.low_temp2.setText((int)wethearDetailsmodel.getRow_temp_min() + "°");
                rowHolder. date.setText(wethearDetailsmodel.getROW_DATE());

            }
        }

    }

    @Override
    public int getItemCount() {
        Log.d("TAG","count"+recyclerViewItems.size());
        return recyclerViewItems.size();
    }

    public class HeaderHolder extends RecyclerView.ViewHolder  {
         TextView header_temp_max,header_temp_min,header_descryption,today_date;
        public HeaderHolder(@NonNull View itemView) {
            super(itemView);
             header_temp_max=(TextView)itemView.findViewById(R.id.highTempHeader);
            header_temp_min=(TextView)itemView.findViewById(R.id.lowTempHeader);
            header_descryption = (TextView) itemView.findViewById(R.id.discryption);
            today_date=(TextView)itemView.findViewById(R.id.today_date);
        }


    }

    public class RowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
         TextView wetheardata,high_temp2,low_temp2,date,today_date;
        public RowHolder(@NonNull View itemView) {
            super(itemView);

              wetheardata = (TextView) itemView.findViewById(R.id.mWeatherTextView);
             high_temp2 = (TextView) itemView.findViewById(R.id.highTempValue);
             low_temp2 = (TextView) itemView.findViewById(R.id.lowTempValue);
             date = (TextView) itemView.findViewById(R.id.date);
             itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            RecyclerViewItems recyclerViewItem = recyclerViewItems.get(adapterPosition);
            WethearDetailsmodel wethearDetailsmodel = (WethearDetailsmodel) recyclerViewItem;
            double pressure = wethearDetailsmodel.getRowPressure();
            double humidity = wethearDetailsmodel.getRowHumidity();
            double wind =wethearDetailsmodel.getRowWindSpeed();
            double tempareture_max =wethearDetailsmodel.getRow_temp_max();
            double tempareture_min = wethearDetailsmodel.getRow_temp_min();
            String row_date=wethearDetailsmodel.getROW_DATE();
          String discryption=wethearDetailsmodel.getDICRYPTION();
            mclickListener.onItemClickListener(adapterPosition, discryption, pressure, humidity, wind, tempareture_max, tempareture_min,row_date);
        }
////
        }
    public void setWeatherData(List<RecyclerViewItems> weatherData) {
//        mwetheardata = weatherData;

        notifyDataSetChanged();
    }


    }





