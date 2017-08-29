package com.soussidev.kotlin.rx_java2_soussidev;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.soussidev.kotlin.rx_java2_soussidev.bus.RxBusAdmin;
import com.soussidev.kotlin.rx_java2_soussidev.model.Admin;


/**
 * A simple {@link Fragment} subclass.
 */
public class RxBusfragment extends Fragment {

    private Admin admin;
    private Button textEvent,userEvent;

    public RxBusfragment() {
        // Required empty public constructor
    }

   /* public static RxBusfragment newInstance() {

        Bundle args = new Bundle();

        RxBusfragment fragment = new RxBusfragment();
        fragment.setArguments(args);
        return fragment;
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rx_bus, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textEvent =(Button)view.findViewById(R.id.textEvent);
        userEvent =(Button)view.findViewById(R.id.userEvent);
        textEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBusAdmin.getInstance().post("myMessage1");
            }
        });


        admin = new Admin();
        admin.setName("soussi");
        admin.setTel(55055055);
        admin.setPost("Dev Android");
        admin.setEmail("soussi.mohamed747@gmail.com");

        userEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBusAdmin.getInstance().userChanged(admin);
            }
        });


    }

}
