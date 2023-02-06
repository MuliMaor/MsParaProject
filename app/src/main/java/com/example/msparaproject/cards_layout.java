package com.example.msparaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class cards_layout extends Fragment {

    //buttons should return Reservation class object that contains int starting hour (8:00-20:00 = 0-12), and int duration (in half hours)
    private ArrayList<DataModel> dataSet=new ArrayList<>();
    private RecyclerView recycleView;
    private LinearLayoutManager layoutManager;
    private CustomAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cards_layout, container, false);
        recycleView = view.findViewById(R.id.my_recycler_view);

        for(int i = 0 ; i < MyData.nameArray.length ; i++)//to Create card from whole array
        {
            dataSet.add(new DataModel(MyData.nameArray[i]));
        }

        adapter = new CustomAdapter(dataSet);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycleView.setLayoutManager(linearLayoutManager);
        recycleView.setAdapter(adapter);

        return view;
    }
}