package com.example.navigation.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.navigation.MainNavigationDirections;
import com.example.navigation.R;
import com.example.navigation.WebGraphDirections;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ModifyFragment extends Fragment implements View.OnClickListener {

    private NavController navController;

    public ModifyFragment() {
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
        View view = inflater.inflate(R.layout.fragment_modify, container, false);
        TextView mTvToMain = view.findViewById(R.id.tv_to_main_fragment);
        mTvToMain.setOnClickListener(this);
//        mTvToMain.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_global_mainFragment3));
        return view;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_to_main_fragment) {
            MainNavigationDirections.ActionGlobalMainFragment3 action = MainNavigationDirections.actionGlobalMainFragment3();
            action.setFromType("modify");
            navController.navigate(action);
        }
    }
}