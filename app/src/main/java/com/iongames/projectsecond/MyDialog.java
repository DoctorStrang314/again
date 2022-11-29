package com.iongames.projectsecond;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.DialogFragment;

import com.iongames.projectsecond.ui.money.ClientFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class MyDialog extends DialogFragment {

    private PrefClass pref;
    ArrayList price = new ArrayList();
    int myHour = 14;
    int myMinute = 35;
    Calendar c = Calendar.getInstance();
    int myYear = c.get(Calendar.YEAR);
    int myMonth = c.get(Calendar.MONTH);
    int myDay = c.get(Calendar.DAY_OF_MONTH);
    int id = ClientFragment.id;
    EditText edittime;
    EditText date;
    Spinner sp;
    Button ok;
    Button cancellation;


    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialog, null);
        builder.setView(view);
        pref = new PrefClass(getActivity());
        // Остальной код
        LoadTasks();
        sp = view.findViewById(R.id.spinner);
        edittime = view.findViewById(R.id.editTextTime2);
        date = view.findViewById(R.id.editTextDate);

        Spinner spinner = view.findViewById(R.id.spinner);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) dateCallBack, myYear, myMonth, myDay).show();

            }
        });
        edittime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(getActivity(), myCallBack, 12, 1, false).show();//атрибуты добавить
            }
        });
        builder.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,
                                int id) {
                SaveTasks();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Отмена",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int id) {
                        dialog.cancel();
                    }
                });


        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, price);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);
        return builder.create();

    }

    TimePickerDialog.OnTimeSetListener myCallBack = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myHour = hourOfDay;
            myMinute = minute;
            if (myMinute < 10) {
                edittime.setText(myHour + ":0" + myMinute);
            } else {
                edittime.setText(myHour + ":" + myMinute);
            }

        }
    };
    DatePickerDialog.OnDateSetListener dateCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear;
            myDay = dayOfMonth;
            date.setText(myDay + "." + myMonth + "." + myYear);
        }
    };

    private void LoadTasks() {
        //if(states.size()==0){
        int item_size = pref.getInt("key");
        for (int i = 0; i < item_size; i++) {
            String name = pref.getString("Names" + String.valueOf(i));
            String cost = pref.getString("Costs" + String.valueOf(i));
            price.add(name + " - " + cost + " рублей");
        }
    }

    private void SaveTasks() {
            pref.putString("Time" + String.valueOf(id), edittime.getText().toString());
            pref.putString("SpinnerCost" + String.valueOf(id), sp.getSelectedItem().toString());
            pref.putString("Year" + String.valueOf(id), ""+myYear);
            pref.putString("Mouth" + String.valueOf(id), ""+myMonth);
            pref.putString("day" + String.valueOf(id), ""+myDay);
    }


}
