package com.iongames.projectsecond.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iongames.projectsecond.ClientAdapter;
import com.iongames.projectsecond.MyAdapter;
import com.iongames.projectsecond.News;
import com.iongames.projectsecond.PrefClass;
import com.iongames.projectsecond.R;
import com.iongames.projectsecond.databinding.FragmentSlideshowBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class SlideshowFragment extends Fragment {

    PrefClass pref;
    ArrayList<News> states = new ArrayList<News>();
    private RecyclerView recyclerview;
    MyAdapter adapter;
    private FragmentSlideshowBinding binding;
    Calendar c = Calendar.getInstance();
    int myDay = c.get(Calendar.DAY_OF_MONTH);
    int myYear = c.get(Calendar.YEAR);
    int total=0;
    TextView totalView;
    Spinner mounthSpin;
    String [] mounth = new String[]{"Январь","Февраль","Март","Апрель","Май","Июнь",
            "Июль","Август","Сентябрь","Ноябрь","Декабрь"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        pref = new PrefClass(getActivity());
        //setInitialData();
        recyclerview= root.findViewById(R.id.recyclerView);
        mounthSpin = root.findViewById(R.id.spinner2);
        totalView = root.findViewById(R.id.Total);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        MyAdapter myAdapter = new MyAdapter(getContext(),states);
        recyclerview.setAdapter(myAdapter);

        mounthSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                states.clear();
                total=0;
                LoadTasks(myYear,mounthSpin.getSelectedItem().toString(),myDay);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, mounth);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        mounthSpin.setAdapter(adapter);

        myAdapter.notifyDataSetChanged();
        return root;
    }
//    private void setInitialData(){
//        states = new ArrayList<>();
//        states.add(new News("Чистка", "1000"));
//        states.add(new News ("Установка windows", "500"));
//        states.add(new News ("Ремонт", "2000"));
//        states.add(new News ("Удаление вирусов", "750"));
//    }

    private void LoadTasks(int y,String m,int d) {
        int item_size = pref.getInt("key_Client");
        if (states.size() == 0) {
            for (int i = 0; i < item_size; i++) {
                //String nameof = pref.getString("Names" + String.valueOf(i));
                String year = pref.getString("Year" + String.valueOf(i));
                String cost = pref.getString("Costs"+String.valueOf(i));
                String localMounth = pref.getString("Mouth" + String.valueOf(i));
                String day = pref.getString("day" + String.valueOf(i));
                if(y==Integer.parseInt(year) && m.equals(mounth[Integer.parseInt(localMounth)-1]) && d>=Integer.parseInt(day)){
                    states.add(new News(""+mounth[Integer.parseInt(localMounth)-1] +" "+ day,cost));
                    total += Integer.parseInt(cost);
                }
            }
            totalView.setText("Итог: " + total);

        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}