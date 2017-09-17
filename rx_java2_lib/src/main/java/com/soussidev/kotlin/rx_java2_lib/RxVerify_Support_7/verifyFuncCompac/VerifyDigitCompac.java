package com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.verifyFuncCompac;

import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.widget.EditText;

import com.soussidev.kotlin.rx_java2_lib.RxVerify.RxVerifyResult;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.Verify;
import com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.RxVerifyCompacResult;
import com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.VerifyCompac;

import rx.Observable;

/**
 * Created by Soussi on 15/09/2017.
 */

public class VerifyDigitCompac implements VerifyCompac<AppCompatEditText> {
    private static final String DEFAULT_MESSAGE = "Digits only";
    private String digitOnlyMessage;

    public VerifyDigitCompac() {
        digitOnlyMessage = DEFAULT_MESSAGE;
    }

    public VerifyDigitCompac(String digitOnlyMessage) {
        this.digitOnlyMessage = digitOnlyMessage;
    }

    @Override public Observable<RxVerifyCompacResult<AppCompatEditText>> verify(String text, AppCompatEditText item) {
        if (TextUtils.isDigitsOnly(text)) {
            return Observable.just(RxVerifyCompacResult.createSuccess(item));
        }
        return Observable.just(RxVerifyCompacResult.createImproper(item, digitOnlyMessage));
    }
}
