package com.soussidev.kotlin.rx_java2_soussidev;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.soussidev.kotlin.rx_java2_lib.RxAlertDialog.AlertDialogButtonEvent;
import com.soussidev.kotlin.rx_java2_lib.RxAlertDialog.AlertDialogEvent;
import com.soussidev.kotlin.rx_java2_lib.RxAlertDialog.RxAlertDialogBuilder;
import com.soussidev.kotlin.rx_java2_lib.RxAlertDialog.support7.RxAlertDialogSupport;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;
import rx.internal.util.SubscriptionList;

import static android.R.id.message;


/**
 * A simple {@link Fragment} subclass.
 */
public class RxAlertDialog extends Fragment {
    private Button showrxdailog;
    private SubscriptionList subscriptionList;


    public RxAlertDialog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rx_alert_dialog, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showrxdailog =(Button)view.findViewById(R.id.showRxalerdialog);
        //Using RxBinding view
        Disposable buttonSub = RxView.clicks(showrxdailog)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        // do some work here
                   new RxAlertDialogSupport.Builder(getActivity())
                             .title("Soussidev")
                             .message("RxAlertDialog")
                             .positiveButton("OK")
                             .negativeButton("NO")
                             //.neutralButton("LATER")
                             .show()
                             .subscribe(new Observer<AlertDialogEvent>() {
                                 @Override
                                 public void onCompleted() {

                                 }

                                 @Override
                                 public void onError(Throwable e) {

                                 }

                                 @Override
                                 public void onNext(AlertDialogEvent alertDialogEvent) {

                                 }
                             });



                    }
                });






    }

}
