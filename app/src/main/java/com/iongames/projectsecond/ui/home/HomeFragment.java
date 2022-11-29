package com.iongames.projectsecond.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iongames.projectsecond.ClientAdapter;
import com.iongames.projectsecond.Clients;
import com.iongames.projectsecond.MainActivity;
import com.iongames.projectsecond.MyAdapter;
import com.iongames.projectsecond.News;
import com.iongames.projectsecond.PrefClass;
import com.iongames.projectsecond.R;
import com.iongames.projectsecond.databinding.FragmentHomeBinding;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    PrefClass pref;
    ArrayList<News> states = new ArrayList<News>();
    private RecyclerView recyclerview;

    MyAdapter adapter;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        pref = new PrefClass(getActivity());
        CalendarView CalendarView= root.findViewById(R.id.calendarView);




        recyclerview= root.findViewById(R.id.ClientOfDay);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        MyAdapter myAdapter = new MyAdapter(getContext(),states);
        recyclerview.setAdapter(myAdapter);
        CalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                String  curDate = String.valueOf(dayOfMonth);
                String  Year = String.valueOf(year);
                String  Month = String.valueOf(month);

                states.clear();
                LoadTasks(Year, Month, curDate);
                myAdapter.notifyDataSetChanged();
            }
        });
        myAdapter.notifyDataSetChanged();
        return root;
    }

    private void LoadTasks(String y,String m,String d) {
        int item_size = pref.getInt("key_Client");
        if (states.size() == 0) {
            for (int i = 0; i < item_size; i++) {
                String name = pref.getString("Name" + String.valueOf(i));
                String nameof = pref.getString("Names" + String.valueOf(i));
                String time = pref.getString("Time" + String.valueOf(i));
                String year = pref.getString("Year" + String.valueOf(i));
                String mounth = pref.getString("Mouth" + String.valueOf(i));
                String day = pref.getString("day" + String.valueOf(i));
                if(time!=""&& y.equals(year) && m.equals(mounth) && d.equals(day)){// я понятия не имею почему 2022!=2022 бред
                    states.add(new News(name,time+" "+nameof));
                }

            }


        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}