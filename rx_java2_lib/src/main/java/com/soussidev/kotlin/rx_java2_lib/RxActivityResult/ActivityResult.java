package com.soussidev.kotlin.rx_java2_lib.RxActivityResult;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Soussi on 13/09/2017.
 */

public class ActivityResult {
    private int requestCode;
    private int resultCode;
    private Intent data;

    public ActivityResult(int requestCode, int resultCode, Intent data) {
        this.requestCode = requestCode;
        this.resultCode = resultCode;
        this.data = data;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public Intent getData() {
        return data;
    }

    public boolean isOk() {
        return resultCode == Activity.RESULT_OK;
    }

    public boolean isCanceled() {
        return resultCode == Activity.RESULT_CANCELED;
    }
}
