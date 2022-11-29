package com.iongames.projectsecond.ui.price_list;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.iongames.projectsecond.ClientAdapter;
import com.iongames.projectsecond.MainActivity;
import com.iongames.projectsecond.PrefClass;
import com.iongames.projectsecond.R;
import com.iongames.projectsecond.News;
import com.iongames.projectsecond.MyAdapter;
import com.iongames.projectsecond.databinding.FragmentPricelistBinding;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class PriseList extends Fragment {



    private  PrefClass pref;
    ArrayList<News> states = new ArrayList<News>();
    private RecyclerView recyclerview;
    MyAdapter adapter;


    private FragmentPricelistBinding binding;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PriselistViewModel PriselistViewModel =
                new ViewModelProvider(this).get(PriselistViewModel.class);


        binding = FragmentPricelistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerview= root.findViewById(R.id.Price);

        EditText name = root.findViewById(R.id.newName);
        EditText cost = root.findViewById(R.id.newCost);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        MyAdapter myAdapter = new MyAdapter(getContext(),states);
        Button button = (Button) root.findViewById(R.id.button);

        pref = new PrefClass(getActivity());
        LoadTasks();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.getText().toString()!="" || String.valueOf(name.getText())!="Name"
                 && String.valueOf(cost.getText())!="" || String.valueOf(cost.getText())!="Цена"){


                    setInitialData(name,cost);
                }


                myAdapter.notifyDataSetChanged();
                name.setText("");
                cost.setText("");
            }
        });
        recyclerview.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        return root;
    }



    private void setInitialData(EditText name,EditText cost){

        states.add(new News(String.valueOf(name.getText()), String.valueOf(cost.getText())));



    }
//    @Override
//    public void onSaveInstanceState(Bundle saveInstanceState){
//    super.onSaveInstanceState(saveInstanceState);
//        saveInstanceState.putStringArray("RecyclerView",states);
//    }


    private void SaveTasks(){

        pref.putInt("key",states.size());
        for(int i =0; i <states.size();i++){
            pref.putString("Names"+ String.valueOf(i),states.get(i).name);
            pref.putString("Costs"+ String.valueOf(i),states.get(i).cost);
        }

    }
    private void LoadTasks(){
        //if(states.size()==0){
            int item_size = pref.getInt("key");
            for(int i =0; i <item_size;i++){
                String name = pref.getString("Names"+ String.valueOf(i));
                String cost = pref.getString("Costs"+ String.valueOf(i));
                states.add(new News(name,cost));
                pref.clear();

            }

        //}

    }
    @Override
    public void onDestroyView() {
        SaveTasks();
        super.onDestroyView();
        binding = null;
    }
    @Override
    public void onPause() {
        SaveTasks();
        super.onPause();
        binding = null;
    }
}
