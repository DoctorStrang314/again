package com.iongames.projectsecond.ui.money;

import static androidx.fragment.app.FragmentManager.TAG;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.iongames.projectsecond.ClientAdapter;
import com.iongames.projectsecond.Clients;
import com.iongames.projectsecond.MainActivity;
import com.iongames.projectsecond.MyAdapter;
import com.iongames.projectsecond.News;
import com.iongames.projectsecond.PrefClass;
import com.iongames.projectsecond.R;
import com.iongames.projectsecond.databinding.FragmentClientListBinding;
import com.iongames.projectsecond.ui.gallery.GalleryFragment;
import com.iongames.projectsecond.ui.slideshow.SlideshowFragment;

import java.nio.file.attribute.UserDefinedFileAttributeView;


public class ClientFragment extends Fragment implements ClientAdapter.OnNoteListener  {
    private PrefClass pref;
    private FragmentClientListBinding binding;
    private RecyclerView recyclerview;
    //private RecyclerView.Adapter clientAdapter;
    private ClientAdapter clientAdapter;
    public static int id;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ClientViewModel ClientViewModel =
                new ViewModelProvider(this).get(ClientViewModel.class);

        binding = FragmentClientListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerview= root.findViewById(R.id.Clients_recycler);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        pref = new PrefClass(getActivity());

       clientAdapter = new ClientAdapter(getContext(), MainActivity.client_list,this);

        Button button = (Button) root.findViewById(R.id.AddClient);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=MainActivity.client_list.size();
                Navigation.findNavController(view).navigate(R.id.action_clientFragment_to_nav_gallery);

            }
        });
        LoadTasks();
        recyclerview.setAdapter(clientAdapter);
        clientAdapter.notifyDataSetChanged();

        return root;
    }

    private void LoadTasks() {
        int item_size = pref.getInt("key_Client");
        if (MainActivity.client_list.size() == 0) {
            for (int i = 0; i < item_size; i++) {
                String name = pref.getString("Name" + String.valueOf(i));
                String email = pref.getString("Email" + String.valueOf(i));
                String phone = pref.getString("Phone" + String.valueOf(i));
                String desc = pref.getString("Desc" + String.valueOf(i));
                MainActivity.client_list.add(new Clients(name, email, phone, desc));
            }

        }
        else {
            for (int i = 0; i < item_size; i++) {
                String name = pref.getString("Name" + String.valueOf(i));
                String email = pref.getString("Email" + String.valueOf(i));
                String phone = pref.getString("Phone" + String.valueOf(i));
                String desc = pref.getString("Desc" + String.valueOf(i));
            }
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onNoteListener(int position) {
        id=position;
        Navigation.findNavController(recyclerview).navigate(R.id.action_clientFragment_to_nav_gallery);


    }

    @Override
    public void onStart() {
        super.onStart();
        clientAdapter.notifyDataSetChanged();
    }
}
