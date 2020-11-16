package com.example.lab_zero_programmable_android;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.lab_zero_programmable_android.task.PersonContent;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddTaskFragment extends Fragment {

    public AddTaskFragment() {
        // Required empty public constructor
    }

    public static AddTaskFragment newInstance() {
        AddTaskFragment fragment = new AddTaskFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_add_task, container, false);
        view.findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String personName =  ((EditText)view.findViewById(R.id.nameEdit)).getText().toString();
               String phoneNumber =  ((EditText)view.findViewById(R.id.phoneEdit)).getText().toString();
               //String drawable = ((Spinner)view.findViewById(R.id.spinner)).getSelectedItem().toString();
               if (personName.isEmpty())
                   personName = getString(R.string.umnamed);
               if (phoneNumber.isEmpty())
                   phoneNumber = getString(R.string.no_number);
                PersonContent.addItem(
                        new PersonContent.PersonItem(Integer.toString(PersonContent.ITEMS.size()+1)
                                ,personName,phoneNumber /*,drawable*/
                        ));
                NavHostFragment.findNavController(AddTaskFragment.this)
                        .navigate(R.id.action_addTaskFragment_to_person_Fragment);
            }
        });
        view.findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(AddTaskFragment.this)
                        .navigate(R.id.action_addTaskFragment_to_person_Fragment);
            }
        });

        return view;
    }
}