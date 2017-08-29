package com.soussidev.kotlin.rx_java2_soussidev;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jakewharton.rxbinding2.view.RxView;
import com.soussidev.kotlin.rx_java2_lib.RxAnimator_java;

import io.reactivex.Observable;


/**
 * A simple {@link Fragment} subclass.
 */
public class Rxanimationfragment extends Fragment {


    public Rxanimationfragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rxanimationfragment, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         view.findViewById(R.id.text);

        //run sequencially
        RxView.clicks(view)
                .flatMap(o ->
                        RxAnimator_java.ofFloat(view, "translationY", 0, 300).animationDuration(500)
                )
                .flatMap(o ->
                        RxAnimator_java.ofFloat(view, "translationX", 0, 300)
                );

        //run in parallel
        RxView.longClicks(view)
                .flatMap(animation ->
                        Observable.zip(
                                RxAnimator_java.ofFloat(view, "scaleX", 5),
                                RxAnimator_java.ofFloat(view, "scaleY", 5),
                                RxAnimator_java.ofFloat(view, "translationY", 300),
                                RxAnimator_java.ofFloat(view, "translationX", 200),
                                (a, a2, a3, a4) -> a
                        )
                )
                .flatMap(animation ->
                        Observable.zip(
                                RxAnimator_java.ofFloat(view, "scaleX", 1),
                                RxAnimator_java.ofFloat(view, "scaleY", 1),
                                RxAnimator_java.ofFloat(view, "translationX", 0),
                                RxAnimator_java.ofFloat(view, "translationY", 0),
                                (a, a2, a3, a4) -> a
                        )
                )
                .subscribe(animation -> {});

    }

}
