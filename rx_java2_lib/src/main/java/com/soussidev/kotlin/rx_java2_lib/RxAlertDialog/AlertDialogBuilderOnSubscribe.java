package com.soussidev.kotlin.rx_java2_lib.RxAlertDialog;


import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

/**
 * Created by Soussi on 15/09/2017.
 */

public class AlertDialogBuilderOnSubscribe implements Observable.OnSubscribe<AlertDialogEvent>
{
    @NonNull
    private final BuilderJoiner joiner;
    @NonNull private final RxAlertDialogBuilder rxBuilder;

    public AlertDialogBuilderOnSubscribe(
            @NonNull BuilderJoiner joiner,
            @NonNull RxAlertDialogBuilder rxBuilder)
    {
        this.joiner = joiner;
        this.rxBuilder = rxBuilder;
    }

    @Override public void call(@NonNull final Subscriber<? super AlertDialogEvent> subscriber)
    {
        joiner.setTitle(rxBuilder.getTitle());
        if (rxBuilder.getTitleRes() != null)
        {
            joiner.setTitle(rxBuilder.getTitleRes());
        }
        joiner.setMessage(rxBuilder.getMessage());
        if (rxBuilder.getMessageRes() != null)
        {
            joiner.setMessage(rxBuilder.getMessageRes());
        }
        joiner.setIcon(rxBuilder.getIcon());
        if (rxBuilder.getIconRes() != null)
        {
            joiner.setIcon(rxBuilder.getIconRes());
        }
        if (rxBuilder.getIconAttribute() != null)
        {
            joiner.setIconAttribute(rxBuilder.getIconAttribute());
        }

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()
        {
            @Override public void onClick(DialogInterface dialog, int which)
            {
                subscriber.onNext(new AlertDialogButtonEvent(which));
                subscriber.onCompleted();
            }
        };
        joiner.setPositiveButton(rxBuilder.getPositiveButton(), listener);
        if (rxBuilder.getPositiveButtonRes() != null)
        {
            joiner.setPositiveButton(rxBuilder.getPositiveButtonRes(), listener);
        }
        joiner.setNegativeButton(rxBuilder.getNegativeButton(), listener);
        if (rxBuilder.getNegativeButtonRes() != null)
        {
            joiner.setNegativeButton(rxBuilder.getNegativeButtonRes(), listener);
        }
        joiner.setNeutralButton(rxBuilder.getNeutralButton(), listener);
        if (rxBuilder.getNeutralButtonRes() != null)
        {
            joiner.setNeutralButton(rxBuilder.getNeutralButtonRes(), listener);
        }

        if (rxBuilder.getCancellable() != null)
        {
            joiner.setCancelable(rxBuilder.getCancellable());
        }

        final Dialog created = joiner.create();

        subscriber.add(Subscriptions.create(new Action0()
        {
            @Override public void call()
            {
                if (!subscriber.isUnsubscribed())
                {
                    created.dismiss();
                }
            }
        }));

        if (rxBuilder.getCanceledOnTouchOutside() != null)
        {
            created.setCanceledOnTouchOutside(rxBuilder.getCanceledOnTouchOutside());
        }

        DialogInterface.OnDismissListener dismissListener = new DialogInterface.OnDismissListener()
        {
            @Override public void onDismiss(DialogInterface dialog)
            {
                subscriber.onCompleted();
            }
        };
        created.setOnDismissListener(dismissListener);

        subscriber.onNext(new AlertDialogDialogEvent(created));
    }
}

