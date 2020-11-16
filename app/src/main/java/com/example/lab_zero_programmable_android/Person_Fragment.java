package com.example.lab_zero_programmable_android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.lab_zero_programmable_android.task.PersonContent;

/**
 * A fragment representing a list of Items.
 */
public class Person_Fragment extends Fragment implements MyPersonRecyclerViewAdapter.EventListener {

    private int mColumnCount = 1;
    private MyPersonRecyclerViewAdapter personAdapter;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public Person_Fragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static Person_Fragment newInstance() {
        Person_Fragment fragment = new Person_Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_list, container, false);
        // Set the adapter
        RecyclerView recyclerView =  view.findViewById(R.id.list);

        view.findViewById(R.id.add_fab).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("Check", "clicked");
                NavHostFragment.findNavController(Person_Fragment.this)
                        .navigate(R.id.action_person_Fragment_to_addTaskFragment);
            }
        });

        if (recyclerView instanceof RecyclerView) {
            Context context = view.getContext();

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            personAdapter = new MyPersonRecyclerViewAdapter(PersonContent.ITEMS);
            personAdapter.SetInputListeners(this);
            recyclerView.setAdapter(personAdapter);
        }

        view.findViewById(R.id.add_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController( Person_Fragment.this).navigate(R.id.action_person_Fragment_to_addTaskFragment);
            }
        });
        return view;
    }

    @Override
    public void onItemClick(int person_id) {

    }

    @Override
    public void onDeleteClick(String person_id) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you sure to delete").setTitle("Delete");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PersonContent.deleteItem(person_id);
                personAdapter.notifyDataSetChanged();
            }
        });
    }
}