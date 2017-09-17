package com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7;

import android.widget.TextView;

/**
 * Created by Soussi on 15/09/2017.
 */

public class RxVerifyCompacResult<T extends TextView> {

private T item;
private boolean isProper;
private String message;

private RxVerifyCompacResult(T item, boolean isProper, String message) {
        this.item = item;
        this.isProper = isProper;
        this.message = message;
        }

public static <T extends TextView> RxVerifyCompacResult<T> createImproper(T item, String message) {
        return new RxVerifyCompacResult<>(item, false, message);
        }

public static <T extends TextView> RxVerifyCompacResult<T> createSuccess(T item) {
        return new RxVerifyCompacResult<>(item, true, "");
        }

public String getValidatedText() {
        return item.getText().toString();
        }

public T getItem() {
        return item;
        }

public void setItem(T item) {
        this.item = item;
        }

public boolean isProper() {
        return isProper;
        }

public String getMessage() {
        return message;
        }

@Override public String toString() {
        return "RxVerifyCompacResult{" +
        "itemValue=" + item.getText().toString() +
        ", isProper=" + isProper +
        ", message='" + message + '\'' +
        '}';
        }
        }
