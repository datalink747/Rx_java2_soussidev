package com.soussidev.kotlin.rx_java2_lib.RxVerify.verifyFunc;

import android.widget.EditText;
import android.widget.TextView;

import com.soussidev.kotlin.rx_java2_lib.RxVerify.RxVerifyResult;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.Verify;

import rx.Observable;

/**
 * Created by Soussi on 15/09/2017.
 */

public class VerifySameAs implements Verify<EditText> {
    public static final String DEFAULT_MESSAGE = "Must be the same";
    private String sameAsMessage;
    private TextView anotherTextView;

    public VerifySameAs() {
        sameAsMessage = DEFAULT_MESSAGE;
    }

    public VerifySameAs(TextView anotherTextView, String message) {
        this.anotherTextView = anotherTextView;
        this.sameAsMessage = message;
    }

    @Override public Observable<RxVerifyResult<EditText>> verify(String text, EditText item) {
        if (anotherTextView.getText().toString().equals(text)) {
            return Observable.just(RxVerifyResult.createSuccess(item));
        }

        return Observable.just(RxVerifyResult.createImproper(item, sameAsMessage));
    }
}

