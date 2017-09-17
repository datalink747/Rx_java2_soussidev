package com.soussidev.kotlin.rx_java2_lib.RxAlertDialog.android;

import android.content.Context;
import android.support.annotation.NonNull;

import com.soussidev.kotlin.rx_java2_lib.RxAlertDialog.AlertDialogBuilderOnSubscribe;
import com.soussidev.kotlin.rx_java2_lib.RxAlertDialog.AlertDialogDialogEvent;
import com.soussidev.kotlin.rx_java2_lib.RxAlertDialog.AlertDialogEvent;
import com.soussidev.kotlin.rx_java2_lib.RxAlertDialog.RxAlertDialogBuilder;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Soussi on 15/09/2017.
 */

public class RxAlertDialog {

    private RxAlertDialog()
    {
        throw new IllegalStateException("No instance");
    }

    public static class Builder extends RxAlertDialogBuilder<Builder>
    {
        public Builder(@NonNull Context context)
        {
            super(context);
        }

        @NonNull @Override protected Builder self()
        {
            return this;
        }

        @NonNull public Observable<AlertDialogEvent> create()
        {
            return Observable.create(new AlertDialogBuilderOnSubscribe(
                    new BuilderJoinerAndroid(getContext()),
                    Builder.this))
                    .subscribeOn(AndroidSchedulers.mainThread());
        }

        @NonNull @Override public Observable<AlertDialogEvent> show()
        {
            return create()
                    .doOnNext(new Action1<AlertDialogEvent>()
                    {
                        @Override public void call(AlertDialogEvent dialogEvent)
                        {
                            if (dialogEvent instanceof AlertDialogDialogEvent)
                            {
                                ((AlertDialogDialogEvent) dialogEvent).getDialog().show();
                            }
                        }
                    });
        }
    }

}
