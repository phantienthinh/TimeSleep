package com.example.tienthinh.timesleep.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tienthinh.timesleep.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class FragmentOne extends Fragment  {
    BarChart barChart;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_one,container,false);
        initView();
        //khởi tạo barchart
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(24);
        barChart.setPinchZoom(false);
        barChart.setBackgroundColor(Color.rgb(0, 0, 0));
        barChart.setDrawGridBackground(false);
//        barChart.getAxisLeft().setDrawGridLines(false);
//        barChart.getXAxis().setDrawGridLines(false);

        barChart.getAxisLeft().setTextColor(Color.WHITE); // left y-axis
        barChart.getXAxis().setTextColor(Color.WHITE);
        barChart.getLegend().setTextColor(Color.WHITE);

        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getXAxis().setDrawGridLines(false);
        //thêm đối tượng arrlist +  BarDataSet
       addArrayList();
        return view;
    }

    private void addArrayList() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f,5));
         BarDataSet barDataSet  = new BarDataSet(entries ,"Time Sleep");

        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(barDataSet);
        data.setBarWidth(0.3f);
        barChart.setData(data);

    }

    private void initView() {
        barChart =(BarChart)view.findViewById(R.id.barchart);
    }
}
