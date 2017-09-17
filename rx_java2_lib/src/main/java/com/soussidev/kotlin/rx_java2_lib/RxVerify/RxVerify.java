package com.soussidev.kotlin.rx_java2_lib.RxVerify;

import android.widget.EditText;
import android.widget.TextView;


import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.verifyFunc.VerifyAge;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.verifyFunc.VerifyDigit;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.verifyFunc.VerifyEmail;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.verifyFunc.VerifyInList;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.verifyFunc.VerifyIp4;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.verifyFunc.VerifyLength;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.verifyFunc.VerifyMaxLength;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.verifyFunc.VerifyMinLength;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.verifyFunc.VerifyNonEmpty;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.verifyFunc.VerifyPatternFind;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.verifyFunc.VerifyPatternMatches;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.verifyFunc.VerifyPhone;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.verifyFunc.VerifySameAs;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.verifyFunc.VerifyUrlWeb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;



/**
 * Created by Soussi on 15/09/2017.
 */

public class RxVerify {

    private List<Verify<EditText>>  verify= new ArrayList<>();
    private List<Verify<EditText>> externalVerify = new ArrayList<>();
    private Observable<String> changeEmitter;
    private EditText et;

    private RxVerify(EditText et) {
        this.et = et;
    }

    public static RxVerify createFor(EditText et) {
        return new RxVerify(et);
    }

    public RxVerify onFocusChanged() {
        this.changeEmitter = RxView.focusChanges(et).skip(1).filter(new Func1<Boolean, Boolean>() {
            @Override public Boolean call(Boolean hasFocus) {
                return !hasFocus;
            }
        }).map(new Func1<Boolean, String>() {
            @Override public String call(Boolean aBoolean) {
                return et.getText().toString();
            }
        });
        return this;
    }

    public RxVerify onValueChanged() {
        this.changeEmitter = RxTextView.textChanges(et).skip(1).map(new Func1<CharSequence, String>() {
            @Override public String call(CharSequence charSequence) {
                return charSequence.toString();
            }
        });
        return this;
    }

    public RxVerify onSubscribe() {
        this.changeEmitter = Observable.create(new Observable.OnSubscribe<String>() {
            @Override public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(et.getText().toString());
                subscriber.onCompleted();
            }
        });
        return this;
    }

    public RxVerify email() {
        this.verify.add(new VerifyEmail());
        return this;
    }

    public RxVerify email(String invalidEmailMessage) {
        this.verify.add(new VerifyEmail(invalidEmailMessage));
        return this;
    }

    public RxVerify email(String invalidEmailMessage, Pattern pattern) {
        this.verify.add(new VerifyEmail(invalidEmailMessage, pattern));
        return this;
    }

    public RxVerify ip4() {
        this.verify.add(new VerifyIp4());
        return this;
    }

    public RxVerify ip4(String invalidIp4Message) {
        this.verify.add(new VerifyIp4(invalidIp4Message));
        return this;
    }

    public RxVerify Url() {
        this.verify.add(new VerifyUrlWeb());
        return this;
    }

    public RxVerify Url(String invalidUrlMessage) {
        this.verify.add(new VerifyUrlWeb(invalidUrlMessage));
        return this;
    }

    public RxVerify Url(String invalidUrlMessage, Pattern pattern) {
        this.verify.add(new VerifyUrlWeb(invalidUrlMessage, pattern));
        return this;
    }

    public RxVerify Phone() {
        this.verify.add(new VerifyPhone());
        return this;
    }

    public RxVerify Phone(String invalidPhone) {
        this.verify.add(new VerifyPhone(invalidPhone));
        return this;
    }

    public RxVerify Phone(String invalidPhone, Pattern pattern) {
        this.verify.add(new VerifyPhone(invalidPhone, pattern));
        return this;
    }

    public RxVerify patternMatches(String invalidValueMessage, Pattern pattern) {
        this.verify.add(new VerifyPatternMatches(invalidValueMessage, pattern));
        return this;
    }

    public RxVerify patternMatches(String invalidValueMessage, String pattern) {
        this.verify.add(new VerifyPatternMatches(invalidValueMessage, pattern));
        return this;
    }

    public RxVerify patternFind(String invalidValueMessage, Pattern pattern) {
        this.verify.add(new VerifyPatternFind(invalidValueMessage, pattern));
        return this;
    }

    public RxVerify patternFind(String invalidValueMessage, String pattern) {
        this.verify.add(new VerifyPatternFind(invalidValueMessage, pattern));
        return this;
    }

    public RxVerify nonEmpty() {
        this.verify.add(new VerifyNonEmpty());
        return this;
    }

    public RxVerify nonEmpty(String cannotBeEmptyMessage) {
        this.verify.add(new VerifyNonEmpty(cannotBeEmptyMessage));
        return this;
    }

    public RxVerify digitOnly() {
        this.verify.add(new VerifyDigit());
        return this;
    }

    public RxVerify digitOnly(String digitOnlyErrorMessage) {
        this.verify.add(new VerifyDigit(digitOnlyErrorMessage));
        return this;
    }

    public RxVerify minLength(int length) {
        this.verify.add(new VerifyMinLength(length));
        return this;
    }

    public RxVerify minLength(int length, String badLengthMessage) {
        this.verify.add(new VerifyMinLength(length, badLengthMessage));
        return this;
    }

    public RxVerify maxLength(int length) {
        this.verify.add(new VerifyMaxLength(length));
        return this;
    }

    public RxVerify maxLength(int length, String badLengthMessage) {
        this.verify.add(new VerifyMaxLength(length, badLengthMessage));
        return this;
    }

    public RxVerify length(int length) {
        this.verify.add(new VerifyLength(length));
        return this;
    }

    public RxVerify length(int length, String badLengthMessage) {
        this.verify.add(new VerifyLength(length, badLengthMessage));
        return this;
    }

    public RxVerify age(String badAgeMessage, int age, SimpleDateFormat sdf) {
        this.verify.add(new VerifyAge(badAgeMessage, sdf, age));
        return this;
    }

    public RxVerify sameAs(TextView anotherTextView, String message) {
        this.verify.add(new VerifySameAs(anotherTextView, message));
        return this;
    }

    public RxVerify in(String message, List<String> properValues) {
        this.verify.add(new VerifyInList(message, properValues));
        return this;
    }

    public RxVerify with(Verify<EditText> externalVerify) {
        this.externalVerify.add(externalVerify);
        return this;
    }

    public Observable<RxVerifyResult<EditText>> toObservable() {
        if (changeEmitter == null) {
            throw new ChangeEmitterNotSetException(
                    "Change emitter have to be set. Did you forget to set onFocusChanged, onValueChanged or onSubscribe?");
        }
        Observable<RxVerifyResult<EditText>> verifyResultObservable =
                changeEmitter.concatMap(new Func1<String, Observable<RxVerifyResult<EditText>>>() {
                    @Override public Observable<RxVerifyResult<EditText>> call(final String value) {
                        return Observable.from(verify)
                                .concatMap(
                                        new Func1<Verify<EditText>, Observable<RxVerifyResult<EditText>>>() {
                                            @Override public Observable<RxVerifyResult<EditText>> call(
                                                    Verify<EditText> verify) {
                                                return verify.verify(value, et);
                                            }
                                        });
                    }
                })
                        .buffer(verify.size())
                        .map(new Func1<List<RxVerifyResult<EditText>>, RxVerifyResult<EditText>>() {
                            @Override
                            public RxVerifyResult<EditText> call(List<RxVerifyResult<EditText>> objects) {
                                return VerifyResultHelper.getFirstBadResultOrSuccess(objects);
                            }
                        });

        if (externalVerify.isEmpty()) {
            return verifyResultObservable;
        }
        return verifyResultObservable.flatMap(
                new Func1<RxVerifyResult<EditText>, Observable<RxVerifyResult<EditText>>>() {
                    @Override public Observable<RxVerifyResult<EditText>> call(
                            final RxVerifyResult<EditText> result) {
                        // if normal verify doesn't found error, launch external one
                        if (result.isProper()) {
                            return Observable.from(externalVerify)
                                    .concatMap(
                                            new Func1<Verify<EditText>, Observable<RxVerifyResult<EditText>>>() {
                                                @Override public Observable<RxVerifyResult<EditText>> call(
                                                        Verify<EditText> verify) {
                                                    return verify.verify(result.getValidatedText(), result.getItem());
                                                }
                                            })
                                    .buffer(externalVerify.size())
                                    .map(
                                            new Func1<List<RxVerifyResult<EditText>>, RxVerifyResult<EditText>>() {
                                                @Override public RxVerifyResult<EditText> call(
                                                        List<RxVerifyResult<EditText>> objects) {
                                                    return VerifyResultHelper.getFirstBadResultOrSuccess(objects);
                                                }
                                            });
                        } else {
                            return Observable.just(result);
                        }
                    }
                });
    }
}
