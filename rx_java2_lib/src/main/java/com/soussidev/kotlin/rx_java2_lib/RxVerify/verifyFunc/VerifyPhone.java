package com.soussidev.kotlin.rx_java2_lib.RxVerify.verifyFunc;

import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by Soussi on 16/09/2017.
 */

public class VerifyPhone extends VerifyPatternMatches {

    private static final String DEFAULT_MESSAGE = "Invalid Phone Number";

    public VerifyPhone() {
        super(DEFAULT_MESSAGE, Patterns.PHONE);
    }

    public VerifyPhone(String invalidPhone) {
        super(invalidPhone, Patterns.PHONE);
    }

    public VerifyPhone(String invalidPhone, Pattern pattern) {
        super(invalidPhone, pattern);
    }
}


