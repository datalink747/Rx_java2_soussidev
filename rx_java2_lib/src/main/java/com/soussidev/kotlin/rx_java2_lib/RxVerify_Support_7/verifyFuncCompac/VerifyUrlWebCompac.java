package com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.verifyFuncCompac;

import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by Soussi on 15/09/2017.
 */

public class VerifyUrlWebCompac extends VerifyPatternMatchesCompac {

    private static final String DEFAULT_MESSAGE = "Invalid Url";

    public VerifyUrlWebCompac() {
        super(DEFAULT_MESSAGE, Patterns.WEB_URL);
    }

    public VerifyUrlWebCompac(String invalidUrlMessage) {
        super(invalidUrlMessage, Patterns.WEB_URL);
    }

    public VerifyUrlWebCompac(String invalidUrlMessage, Pattern pattern) {
        super(invalidUrlMessage, pattern);
    }
}

