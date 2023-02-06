package com.example.msparaproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FilterFragmentClient#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilterFragmentClient extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FilterFragmentClient() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FilterFragmentClient.
     */
    // TODO: Rename and change types and number of parameters
    public static FilterFragmentClient newInstance(String param1, String param2) {
        FilterFragmentClient fragment = new FilterFragmentClient();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    CheckBox CheckBoxWomenRegular;
    CheckBox CheckBoxMenRegular;
    CheckBox CheckBoxWomenColor;
    CheckBox CheckBoxMenColor;

    TextView textViewTimeAndCost;

    int currentTime = 0;
    int currentCost = 0;

    //String male[] = { "Regular", "Color" };
    //String female[] = { "Regular", "Color" };
    String male[] = { "", "" };
    String female[] = { "", "" };
    String bundleTimeAndCostString = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filter_client, container, false);

        CheckBoxWomenRegular = view.findViewById(R.id.CheckBoxWomenRegular);
        CheckBoxMenRegular = view.findViewById(R.id.CheckBoxMenRegular);
        CheckBoxWomenColor = view.findViewById(R.id.CheckBoxWomenColor);
        CheckBoxMenColor = view.findViewById(R.id.CheckBoxMenColor);

        textViewTimeAndCost = view.findViewById(R.id.textViewTimeAndCostClient);

        updateTimeAndCost();

        CheckBoxWomenRegular.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    //is checked
                    uncheckChoices(true);
                    adjustTimeAndCost(true, "womenRegular");
                    female[0] = "Regular";
                }
                else
                {
                    adjustTimeAndCost(false, "womenRegular");
                    female[0] = "";
                }
            }
        });
        CheckBoxWomenColor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    //is checked
                    uncheckChoices(true);
                    adjustTimeAndCost(true, "womenColor");
                    female[1] = "Color";
                }
                else
                {
                    adjustTimeAndCost(false, "womenColor");
                    female[1] = "";
                }
            }
        });
        CheckBoxMenRegular.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    //is checked
                    uncheckChoices(false);
                    adjustTimeAndCost(true, "menRegular");
                    male[0] = "Regular";
                }
                else
                {
                    adjustTimeAndCost(false, "menRegular");
                    male[0] = "";
                }
            }
        });
        CheckBoxMenColor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    //is checked
                    uncheckChoices(false);
                    adjustTimeAndCost(true, "menColor");
                    male[1] = "Color";
                }
                else
                {
                    adjustTimeAndCost(false, "menColor");
                    male[1] = "";
                }
            }
        });

        //Apply Button
        Button buttonApplyFilters = view.findViewById(R.id.buttonApplyFilterClient);
        buttonApplyFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String haircut = "No filters were applied";
                int itemsSelected = 0;
                for (String element : male)
                {
                    if (element != "")
                    {
                        itemsSelected++;
                    }
                }
                //no male haircut selected
                if(itemsSelected == 0)
                {
                    for (String element : female)
                    {
                        if (element != "")
                        {
                            itemsSelected++;
                        }
                        if(itemsSelected != 0)
                        {
                            haircut = formatHaircutString(false, itemsSelected);
                        }
                    }
                }
                else
                //male haircut selected
                {
                    haircut = formatHaircutString(true, itemsSelected);
                }

                Bundle bundle = new Bundle();
                bundle.putString("haircutChoiceText", haircut + "\n" + bundleTimeAndCostString);
                Navigation.findNavController(view).navigate(R.id.action_filterFragmentClient3_to_dateFragmantClient, bundle);
            }
        });

        return view;
    }

    public void uncheckChoices(boolean cancelMale)
    {
        if(cancelMale)
        {
            if(CheckBoxMenRegular.isChecked() || CheckBoxMenColor.isChecked())
            {
                CheckBoxMenRegular.setChecked(false);
                CheckBoxMenColor.setChecked(false);
                for (String element : male)
                {
                    element = "";
                }
                currentTime = 0;
                currentCost = 0;
            }
        }
        else
        {
            if(CheckBoxWomenRegular.isChecked() || CheckBoxWomenColor.isChecked())
            {
                CheckBoxWomenRegular.setChecked(false);
                CheckBoxWomenColor.setChecked(false);
                for (String element : female)
                {
                    element = "";
                }
                currentTime = 0;
                currentCost = 0;
            }
        }
    }

    //show the correct time and cost on the textview
    void updateTimeAndCost()
    {
        int hours = currentTime/60;
        int minutes = currentTime%60;
        String hoursString = "";

        switch (hours)
        {
            case 0:
                hoursString = "Time: ";
                break;
            case 1:
                hoursString = "Time: 1H and ";
                break;
            default:
                hoursString = "Time: " + hours + "H and ";
        }
        textViewTimeAndCost.setText(hoursString + minutes + " M" +"\nCost: " + currentCost + " ILS");
        bundleTimeAndCostString = hoursString + minutes + " M, Cost: " + currentCost + " ILS";
    }

    //add or subtract from current time and cost
    void adjustTimeAndCost(boolean add, String option)
    {
        //for now, static time and cost values:
        int timeWomenRegular = 60;
        int timeMenRegular = 30;
        int timeWomenColor = 45;
        int timeMenColor = 45;

        int costWomenRegular = 100;
        int costMenRegular = 50;
        int costWomenColor = 50;
        int costMenColor = 30;

        int tempTime = 0;
        int tempCost = 0;

        switch (option)
        {
            case "womenRegular":
                tempTime = timeWomenRegular;
                tempCost = costWomenRegular;
                break;
            case "womenColor":
                tempTime = timeWomenColor;
                tempCost = costWomenColor;
                break;
            case "menRegular":
                tempTime = timeMenRegular;
                tempCost = costMenRegular;
                break;
            case "menColor":
                tempTime = timeMenColor;
                tempCost = costMenColor;
                break;
        }
        if(!add)
        {
            tempTime *= -1;
            tempCost *= -1;
        }
        currentTime += tempTime;
        currentCost += tempCost;

        updateTimeAndCost();
    }

    String formatHaircutString(boolean itemsMale, int numItemsSelected)
    {
        String formattedHaircutString = "Looking for: ";

        numItemsSelected--;

        if(itemsMale)
        {
            for (String element : male)
            {
                if(element != "")
                {
                    formattedHaircutString += element;
                    if(numItemsSelected > 0)
                    {
                        numItemsSelected--;
                        formattedHaircutString += ", ";
                    }
                }
            }
        }
        else
        {
            for (String element : female)
            {
                if(element != "")
                {
                    formattedHaircutString += element;
                    if(numItemsSelected > 0)
                    {
                        numItemsSelected--;
                        formattedHaircutString += ", ";
                    }
                }
            }
        }

        return formattedHaircutString;
    }
}