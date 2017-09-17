package com.soussidev.kotlin.rx_java2_lib.RxAlertDialog;

import android.app.Dialog;
import android.support.annotation.NonNull;

/**
 * Created by Soussi on 15/09/2017.
 */

public class AlertDialogDialogEvent implements AlertDialogEvent
{
    @NonNull
    private final Dialog dialog;

    public AlertDialogDialogEvent(@NonNull Dialog dialog)
    {
        this.dialog = dialog;
    }

    @NonNull public Dialog getDialog()
    {
        return dialog;
    }
}
