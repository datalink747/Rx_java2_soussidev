package com.soussidev.kotlin.rx_java2_soussidev;

import android.util.Log;
import android.widget.EditText;

import com.soussidev.kotlin.rx_java2_lib.RxVerify.RxVerify;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.RxVerifyResult;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.Verify;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by Soussi on 15/09/2017.
 */

public class VerifyEmailDomain implements Verify<EditText> {
    private static final String TAG = "RxVerifyCompac";

    @Override public Observable<RxVerifyResult<EditText>> verify(String text, EditText item) {
        Log.i(TAG, "Checking domain");
        RxVerifyResult<EditText> result;
        if (text.endsWith("gmail.com")) {
            result = RxVerifyResult.createSuccess(item);
        } else {
            result = RxVerifyResult.createImproper(item, "Improper domain");
        }

        return Observable.just(result).delay(2, TimeUnit.SECONDS);
    }
}
