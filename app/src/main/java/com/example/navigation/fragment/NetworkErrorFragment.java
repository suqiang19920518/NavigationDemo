package com.example.navigation.fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDeepLinkRequest;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigation.MainNavigationDirections;
import com.example.navigation.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class NetworkErrorFragment extends Fragment implements View.OnClickListener {

    private NavController navController;

    public NetworkErrorFragment() {
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
        View view = inflater.inflate(R.layout.fragment_network_error, container, false);
        TextView mTvToMain = view.findViewById(R.id.tv_to_main_fragment);
        mTvToMain.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getContext(), getArguments().getString("errorType"), Toast.LENGTH_LONG).show();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_to_main_fragment) {
            //方法一
            // Navigation.findNavController(v).navigate(R.id.action_global_mainFragment3);
            //方法二
            // navController.navigate(R.id.action_global_mainFragment3);
            //方法三
            MainNavigationDirections.ActionGlobalMainFragment3 action = MainNavigationDirections.actionGlobalMainFragment3();
            navController.navigate(action);
        }
    }
}