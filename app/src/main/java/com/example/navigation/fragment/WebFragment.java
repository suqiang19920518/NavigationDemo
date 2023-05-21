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
public class WebFragment extends Fragment implements View.OnClickListener {

    private NavController navController;

    public WebFragment() {
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
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        TextView mTvStartNetworkError = view.findViewById(R.id.tv_start_network_error_activity);
        mTvStartNetworkError.setOnClickListener(this);
        return view;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_start_network_error_activity) {
            Bundle bundle = new Bundle();
            bundle.putString("errorType","404");
            navController.navigate(R.id.action_webFragment_to_networkErrorFragment, bundle);
        }
    }
}