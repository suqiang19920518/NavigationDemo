package com.example.navigation.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.navigation.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class PersonalInfoFragment extends Fragment implements View.OnClickListener {

    private NavController navController;

    public PersonalInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_info, container, false);
        TextView mTvModify = view.findViewById(R.id.tv_modify);
        TextView mTvReset = view.findViewById(R.id.tv_reset);
        mTvModify.setOnClickListener(this);
        mTvReset.setOnClickListener(this);
        return view;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_modify) {
            navController.navigate(R.id.action_personalInfoFragment_to_modifyFragment);
        } else if (v.getId() == R.id.tv_reset) {
            navController.navigate(R.id.action_personalInfoFragment_to_resetFragment);
        }
    }
}