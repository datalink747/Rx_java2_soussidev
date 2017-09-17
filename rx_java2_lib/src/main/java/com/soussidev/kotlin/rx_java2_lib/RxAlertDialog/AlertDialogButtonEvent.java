package com.soussidev.kotlin.rx_java2_lib.RxAlertDialog;

/**
 * Created by Soussi on 15/09/2017.
 */

public class AlertDialogButtonEvent implements AlertDialogEvent {

    @DialogInterfaceButton private final int which;

    public AlertDialogButtonEvent(@DialogInterfaceButton int which)
    {
        this.which = which;
    }

    @DialogInterfaceButton public int getWhich()
    {
        return which;
    }
}

