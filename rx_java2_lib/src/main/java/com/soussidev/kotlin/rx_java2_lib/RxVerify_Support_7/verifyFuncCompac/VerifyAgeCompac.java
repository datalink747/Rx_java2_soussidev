package com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.verifyFuncCompac;

import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;

import com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.RxVerifyCompacResult;
import com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.VerifyCompac;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import rx.Observable;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

/**
 * Created by Soussi on 15/09/2017.
 */

public class VerifyAgeCompac implements VerifyCompac<AppCompatEditText> {

    private static final int DEFAULT_AGE = 18;
    private static final String DEFAULT_MESSAGE = "At least " + DEFAULT_AGE + "y old";
    private final String message;
    private final DateFormat sdf;
    private final int age;

    public VerifyAgeCompac() {
        message = DEFAULT_MESSAGE;
        age = DEFAULT_AGE;
        sdf = SimpleDateFormat.getDateInstance();
    }

    public VerifyAgeCompac(String message, SimpleDateFormat sdf, int age) {
        this.message = message;
        this.sdf = sdf;
        this.age = age;
    }

    @Override public Observable<RxVerifyCompacResult<AppCompatEditText>> verify(String text, AppCompatEditText item) {
        if(TextUtils.isEmpty(text)){
            return Observable.just(RxVerifyCompacResult.createImproper(item, message));
        }
        try {
            Date date = sdf.parse(text);
            if (ageIsValid(date)) {
                return Observable.just(RxVerifyCompacResult.createSuccess(item));
            }
            return Observable.just(RxVerifyCompacResult.createImproper(item, message));
        } catch (ParseException e) {
            return Observable.error(e);
        }
    }

    private int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) || (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(
                DATE))) {
            diff--;
        }
        return diff;
    }

    private Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    private boolean ageIsValid(Date dateOfBirth) {
        return getDiffYears(dateOfBirth, new Date()) >= age;
    }
}
