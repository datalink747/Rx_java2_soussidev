package com.soussidev.kotlin.rx_java2_soussidev;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxCheckedTextView;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;

import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class RxHandlerfragment extends Fragment {
    private Button btnrxhandler;
    private  Disposable subscription;
    private CheckBox checkboxbinding;
    public RxHandlerfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rx_handlerfragment, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        checkboxbinding =(CheckBox)view.findViewById(R.id.checkboxbinding);
        btnrxhandler =(Button)view.findViewById(R.id.btn_rxhandler);
       //Using RxBinding view
        Disposable buttonSub = RxView.clicks(btnrxhandler)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        // do some work here
                        get_function_subscription();
                    }
                });
        // if Checkbox checked button is enable to click
        RxCompoundButton.checkedChanges(checkboxbinding).subscribe(
                RxView.enabled(btnrxhandler));



    }

    //start func
    private void getFun()
    {

        Toast.makeText(getActivity(),"Hello RxHandler",Toast.LENGTH_SHORT).show();
    }
   // Show Fun After 10 second
    private void get_function_subscription()
    {
        subscription = Observable.timer(10, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> getFun());
    }

}
