package com.example.androidempresax.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidempresax.R;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EmprestimoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public EmprestimoFragment() {
        // Required empty public constructor
    }

    public static EmprestimoFragment newInstance(String param1, String param2) {
        EmprestimoFragment fragment = new EmprestimoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_emprestimo, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}