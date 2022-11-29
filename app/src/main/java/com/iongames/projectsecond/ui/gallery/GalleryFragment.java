package com.iongames.projectsecond.ui.gallery;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.iongames.projectsecond.MainActivity;
import com.iongames.projectsecond.Clients;
import com.iongames.projectsecond.MyDialog;
import com.iongames.projectsecond.News;
import com.iongames.projectsecond.PrefClass;
import com.iongames.projectsecond.R;
import com.iongames.projectsecond.databinding.FragmentGalleryBinding;
import com.iongames.projectsecond.ui.money.ClientFragment;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {


    private PrefClass pref;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        pref = new PrefClass(getActivity());

        EditText name = root.findViewById(R.id.ClientName);
        EditText email = root.findViewById(R.id.EmailAddress);
        EditText phone = root.findViewById(R.id.Phone);
        EditText description = root.findViewById(R.id.Description);
        Button button = (Button) root.findViewById(R.id.button_save);
        Button record = (Button) root.findViewById(R.id.record);


        LoadTasks(name,email,phone,description, ClientFragment.id);

        record.setOnClickListener(view->{
            MyDialog dialog = new MyDialog();
            dialog.show(getParentFragmentManager(), "custom");
        });
        button.setOnClickListener(view -> {
            if (ClientFragment.id==MainActivity.client_list.size()){
                setInitialData(name,email,phone,description);
            }
            SaveTasks(name,email,phone,description,ClientFragment.id);
            Navigation.findNavController(view).navigate(R.id.action_nav_gallery_to_clientFragment);
        });
        return root;
    }



    private void SaveTasks(EditText name, EditText email, EditText phone, EditText description,int id){

        pref.putInt("key_Client",MainActivity.client_list.size());// уточнить что значит
            pref.putString("Name"+ String.valueOf(id),name.getText().toString());
            pref.putString("Email"+ String.valueOf(id),email.getText().toString());
            pref.putString("Phone"+ String.valueOf(id),phone.getText().toString());
            pref.putString("Desc"+ String.valueOf(id),description.getText().toString());
    }
    private void LoadTasks(EditText name, EditText email, EditText phone, EditText description,int id){


            name.setText(pref.getString("Name"+ String.valueOf(id)));
            email.setText(pref.getString("Email"+ String.valueOf(id)));
            phone.setText(pref.getString("Phone"+ String.valueOf(id)));
            description.setText(pref.getString("Desc"+ String.valueOf(id)));
    }
    private void setInitialData(EditText name, EditText email, EditText phone, EditText description){

        MainActivity.client_list.add(new Clients (String.valueOf(name.getText()), String.valueOf(email.getText()),String.valueOf(phone.getText()),String.valueOf(description.getText())));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}