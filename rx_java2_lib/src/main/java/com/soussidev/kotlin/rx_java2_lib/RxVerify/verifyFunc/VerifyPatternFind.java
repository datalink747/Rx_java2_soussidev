package com.soussidev.kotlin.rx_java2_lib.RxVerify.verifyFunc;


import android.text.TextUtils;
import android.widget.EditText;

import com.soussidev.kotlin.rx_java2_lib.RxVerify.RxVerifyResult;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.Verify;


import java.util.regex.Pattern;

import rx.Observable;

/**
 * Created by Soussi on 15/09/2017.
 */

public class VerifyPatternFind implements Verify<EditText> {

    private static final String DEFAULT_MESSAGE = "Invalid value";
    private String invalidValueMessage;
    private Pattern pattern;

    public VerifyPatternFind() {
        this.invalidValueMessage = DEFAULT_MESSAGE;
        this.pattern = android.util.Patterns.EMAIL_ADDRESS;
    }

    public VerifyPatternFind(String invalidValueMessage) {
        this.invalidValueMessage = invalidValueMessage;
        this.pattern = android.util.Patterns.EMAIL_ADDRESS;
    }

    public VerifyPatternFind(String invalidValueMessage, Pattern pattern) {
        this.invalidValueMessage = invalidValueMessage;
        this.pattern = pattern;
    }

    public VerifyPatternFind(String invalidValueMessage, String pattern) {
        this.invalidValueMessage = invalidValueMessage;
        this.pattern = Pattern.compile(pattern);
    }

    @Override public Observable<RxVerifyResult<EditText>> verify(String text, EditText item) {
        return Observable.just(validatePattern(item, text));
    }

    private RxVerifyResult<EditText> validatePattern(EditText view, String value) {
        if (nonEmptyAndFoundPattern(value)) {
            return RxVerifyResult.createSuccess(view);
        }
        return RxVerifyResult.createImproper(view, invalidValueMessage);
    }

    private boolean nonEmptyAndFoundPattern(String value) {
        return !TextUtils.isEmpty(value) && pattern.matcher(value).find();
    }
}
