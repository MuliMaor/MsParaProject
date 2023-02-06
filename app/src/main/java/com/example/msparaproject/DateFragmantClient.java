package com.example.msparaproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DateFragmantClient#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DateFragmantClient extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    public DateFragmantClient() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment DateFragmantClient.
     */
    // TODO: Rename and change types and number of parameters
    public static DateFragmantClient newInstance(String param1) {
        DateFragmantClient fragment = new DateFragmantClient();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.date_fragmant_client, container, false);

        TextView textViewActiveFilters = view.findViewById(R.id.textViewActiveFilters);

        //String haircut = "";
        if(getArguments() != null)
        {
            textViewActiveFilters.setText(getArguments().getString("haircutChoiceText"));
        }
        else
        {
            textViewActiveFilters.setText("No filters were applied");
        }


        Button buttonToFilters = view.findViewById(R.id.buttonToFiltersClient);
        buttonToFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Bundle bundle = new Bundle();
                //bundle.putString("password", passwordText.getText().toString());
                Navigation.findNavController(view).navigate(R.id.action_dateFragmantClient_to_filterFragmentClient3);//(R.id.action_blankFragmentOne_to_blankFragmentTwo, bundle);
            }
        });

        return view;
    }

}