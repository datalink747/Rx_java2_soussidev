package com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.verifyFuncCompac;

import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by Soussi on 15/09/2017.
 */

public class VerifyIp4Compac extends VerifyPatternMatchesCompac {

    private static final String DEFAULT_MESSAGE = "Invalid IP4";

    public VerifyIp4Compac() {
        super(DEFAULT_MESSAGE, Patterns.IP_ADDRESS);
    }

    public VerifyIp4Compac(String invalidIpMessage) {
        super(invalidIpMessage, Patterns.IP_ADDRESS);
    }

    public VerifyIp4Compac(String invalidIpMessage, Pattern pattern) {
        super(invalidIpMessage, pattern);
    }
}

