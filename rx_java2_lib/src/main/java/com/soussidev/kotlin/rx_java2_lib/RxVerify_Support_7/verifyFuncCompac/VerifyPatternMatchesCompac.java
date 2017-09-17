package com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.verifyFuncCompac;

import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.widget.EditText;

import com.soussidev.kotlin.rx_java2_lib.RxVerify.RxVerifyResult;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.Verify;
import com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.RxVerifyCompac;
import com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.RxVerifyCompacResult;
import com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.VerifyCompac;

import java.util.regex.Pattern;

import rx.Observable;

/**
 * Created by Soussi on 15/09/2017.
 */

public class VerifyPatternMatchesCompac implements VerifyCompac<AppCompatEditText> {

    private static final String DEFAULT_MESSAGE = "Invalid value";
    private String invalidValueMessage;
    private Pattern pattern;

    public VerifyPatternMatchesCompac() {
        this.invalidValueMessage = DEFAULT_MESSAGE;
        this.pattern = android.util.Patterns.EMAIL_ADDRESS;
    }

    public VerifyPatternMatchesCompac(String invalidValueMessage) {
        this.invalidValueMessage = invalidValueMessage;
        this.pattern = android.util.Patterns.EMAIL_ADDRESS;
    }

    public VerifyPatternMatchesCompac(String invalidValueMessage, Pattern pattern) {
        this.invalidValueMessage = invalidValueMessage;
        this.pattern = pattern;
    }

    public VerifyPatternMatchesCompac(String invalidValueMessage, String pattern) {
        this.invalidValueMessage = invalidValueMessage;
        this.pattern = Pattern.compile(pattern);
    }

    @Override public Observable<RxVerifyCompacResult<AppCompatEditText>> verify(String text, AppCompatEditText item) {
        return Observable.just(validatePattern(item, text));
    }

    private RxVerifyCompacResult<AppCompatEditText> validatePattern(AppCompatEditText view, String value) {
        if (nonEmptyAndMatchPattern(value)) {
            return RxVerifyCompacResult.createSuccess(view);
        }
        return RxVerifyCompacResult.createImproper(view, invalidValueMessage);
    }

    private boolean nonEmptyAndMatchPattern(String value) {
        return !TextUtils.isEmpty(value) && pattern.matcher(value).matches();
    }
}
