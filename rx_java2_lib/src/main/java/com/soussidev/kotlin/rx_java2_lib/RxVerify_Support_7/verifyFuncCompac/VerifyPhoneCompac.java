package com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.verifyFuncCompac;

import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by Soussi on 16/09/2017.
 */

public class VerifyPhoneCompac extends VerifyPatternMatchesCompac {

    private static final String DEFAULT_MESSAGE = "Invalid Phone Number";

    public VerifyPhoneCompac() {
        super(DEFAULT_MESSAGE, Patterns.PHONE);
    }

    public VerifyPhoneCompac(String invalidPhone) {
        super(invalidPhone, Patterns.PHONE);
    }

    public VerifyPhoneCompac(String invalidPhone, Pattern pattern) {
        super(invalidPhone, pattern);
    }
}


