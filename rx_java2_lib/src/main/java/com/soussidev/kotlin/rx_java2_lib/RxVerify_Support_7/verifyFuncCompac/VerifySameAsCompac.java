package com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.verifyFuncCompac;

import android.support.v7.widget.AppCompatEditText;
import android.widget.EditText;
import android.widget.TextView;

import com.soussidev.kotlin.rx_java2_lib.RxVerify.RxVerifyResult;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.Verify;
import com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.RxVerifyCompacResult;
import com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.VerifyCompac;

import rx.Observable;

/**
 * Created by Soussi on 15/09/2017.
 */

public class VerifySameAsCompac implements VerifyCompac<AppCompatEditText> {
    public static final String DEFAULT_MESSAGE = "Must be the same";
    private String sameAsMessage;
    private TextView anotherTextView;

    public VerifySameAsCompac() {
        sameAsMessage = DEFAULT_MESSAGE;
    }

    public VerifySameAsCompac(TextView anotherTextView, String message) {
        this.anotherTextView = anotherTextView;
        this.sameAsMessage = message;
    }

    @Override public Observable<RxVerifyCompacResult<AppCompatEditText>> verify(String text, AppCompatEditText item) {
        if (anotherTextView.getText().toString().equals(text)) {
            return Observable.just(RxVerifyCompacResult.createSuccess(item));
        }

        return Observable.just(RxVerifyCompacResult.createImproper(item, sameAsMessage));
    }
}

