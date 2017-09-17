package com.soussidev.kotlin.rx_java2_lib.RxAlertDialog;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_NEUTRAL;
import static android.content.DialogInterface.BUTTON_POSITIVE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by Soussi on 15/09/2017.
 */
@Target({FIELD, PARAMETER, LOCAL_VARIABLE, METHOD})
@IntDef({BUTTON_POSITIVE, BUTTON_NEUTRAL, BUTTON_NEGATIVE})
@Retention(SOURCE)
public @interface DialogInterfaceButton{
}
