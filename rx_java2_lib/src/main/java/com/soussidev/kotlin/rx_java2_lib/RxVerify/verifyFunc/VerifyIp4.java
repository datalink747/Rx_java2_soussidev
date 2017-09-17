package com.soussidev.kotlin.rx_java2_lib.RxVerify.verifyFunc;

import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by Soussi on 15/09/2017.
 */

public class VerifyIp4 extends VerifyPatternMatches {

    private static final String DEFAULT_MESSAGE = "Invalid IP4";

    public VerifyIp4() {
        super(DEFAULT_MESSAGE, Patterns.IP_ADDRESS);
    }

    public VerifyIp4(String invalidIpMessage) {
        super(invalidIpMessage, Patterns.IP_ADDRESS);
    }

    public VerifyIp4(String invalidIpMessage, Pattern pattern) {
        super(invalidIpMessage, pattern);
    }
}

