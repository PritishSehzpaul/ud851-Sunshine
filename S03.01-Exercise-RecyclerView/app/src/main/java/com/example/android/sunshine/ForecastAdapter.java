package com.example.android.sunshine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder>{
    private String[] mWeatherData;

    public ForecastAdapter(){

    }

    @NonNull
    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int forecastLayoutId = R.layout.forecast_list_item;
        boolean attachToParentImediately = false;

        View view = inflater.inflate(forecastLayoutId, viewGroup, attachToParentImediately);

        ForecastAdapterViewHolder viewHolder = new ForecastAdapterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastAdapterViewHolder forecastAdapterViewHolder, int i) {
        forecastAdapterViewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        if(mWeatherData == null)
            return 0;
        return mWeatherData.length;
    }

    public void setWeatherData(String[] weatherData) {
        this.mWeatherData = weatherData;
        notifyDataSetChanged();

    }

    class ForecastAdapterViewHolder extends RecyclerView.ViewHolder{
        final TextView mWeatherTextView;

        ForecastAdapterViewHolder(View itemView){
            super(itemView);
            mWeatherTextView = itemView.findViewById(R.id.tv_weather_data);
        }

        void bind(int position){
            mWeatherTextView.setText(mWeatherData[position]);
        }
    }
}
