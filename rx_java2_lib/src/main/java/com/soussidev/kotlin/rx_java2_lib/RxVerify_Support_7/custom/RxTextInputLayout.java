package com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.custom;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.FuncN;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Soussi on 16/09/2017.
 */

public class RxTextInputLayout {

    // region Member Variables
    protected static CompositeSubscription compositeSubscription = new CompositeSubscription();
    // endregion
// region Member Variables
   // private static Pattern patternEmail = Patterns.EMAIL_ADDRESS;
    private static Matcher matcher;
    private TextInputLayout textInputLayout;
    private AppCompatEditText appCompatEditText;
    private String message;
    private static Pattern pattern;
    private  Observable<CharSequence> customChangeObservable ;

    /**
     * @author Soussi
     *
     * @param appCompatEditText
     * @param textInputLayout
     */
    public  RxTextInputLayout(final AppCompatEditText appCompatEditText, final TextInputLayout textInputLayout)
    {
        this.appCompatEditText=appCompatEditText;
        this.textInputLayout=textInputLayout;
        customChangeObservable = RxTextView.textChanges(appCompatEditText);

    }

    /**
     * @author Soussi
     *
     * @param appCompatEditText
     * @param textInputLayout
     */
    public static RxTextInputLayout createFor(final AppCompatEditText appCompatEditText, final TextInputLayout textInputLayout) {
        return new RxTextInputLayout(appCompatEditText,textInputLayout);
    }

    public static RxTextInputLayout ButtonValidate(final Button button)
    {
        return ButtonValidate(button);
    }

    /**
     * @author Soussi
     *
     * @param patterns
     * @param message
     *
     * Checks for validity of the Custom input field
     */
    public void RxVerifyCustom(final Pattern patterns, final String message)
    {

      //  this.message=message;
        this.pattern=patterns;

        Subscription VerifyCustomSubscription = customChangeObservable
                .doOnNext(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {

                        disableError(textInputLayout);
                    }
                })
                .debounce(400, TimeUnit.MILLISECONDS)
                .filter(new Func1<CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence charSequence) {
                        return !TextUtils.isEmpty(charSequence);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()) // UI Thread
                .subscribe(new Subscriber<CharSequence>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(CharSequence charSequence) {
                        boolean isAppEdittextValid = validatAppEdittext(charSequence.toString());
                        if (!isAppEdittextValid) {

                            enableError(textInputLayout,message);

                        } else {

                            disableError(textInputLayout);

                        }
                    }
                });

        compositeSubscription.add(VerifyCustomSubscription);
    }

    /**
     * @author Soussi
     *
     * @param message
     * Checks for validity of the phone input field
     */
    public void RxPhone(final String message)
    {

        Subscription phoneSubscription = customChangeObservable
                .doOnNext(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {

                        disableError(textInputLayout);
                    }
                })
                .debounce(400, TimeUnit.MILLISECONDS)
                .filter(new Func1<CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence charSequence) {
                        return !TextUtils.isEmpty(charSequence);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()) // UI Thread
                .subscribe(new Subscriber<CharSequence>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(CharSequence charSequence) {
                        boolean isEmailValid = validatePhone(charSequence.toString());
                        if (!isEmailValid) {

                            enableError(textInputLayout,message);

                        } else {

                            disableError(textInputLayout);

                        }
                    }
                });

       compositeSubscription.add(phoneSubscription);


    }

    /**
     * @author Soussi
     *
     * @param message
     * Checks for validity of the Email input field
     */

    public void RxEmail(final String message)
    {
        //  this.message=message;

        //    Observable<CharSequence> customChangeObservable = RxTextView.textChanges(appCompatEditText);

        Subscription emailSubscription = customChangeObservable
                .doOnNext(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {

                        disableError(textInputLayout);
                    }
                })
                .debounce(400, TimeUnit.MILLISECONDS)
                .filter(new Func1<CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence charSequence) {
                        return !TextUtils.isEmpty(charSequence);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()) // UI Thread
                .subscribe(new Subscriber<CharSequence>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(CharSequence charSequence) {
                        boolean isEmailValid = validateEmail(charSequence.toString());
                        if (!isEmailValid) {

                            enableError(textInputLayout,message);

                        } else {

                            disableError(textInputLayout);

                        }
                    }
                });

        compositeSubscription.add(emailSubscription);


    }

    /**
     * @author Soussi
     *
     * @param message
     * Checks for validity of the webUrl input field
     */
    public void RxWebUrl(final String message)
    {


        Subscription webSubscription = customChangeObservable
                .doOnNext(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {

                        disableError(textInputLayout);
                    }
                })
                .debounce(400, TimeUnit.MILLISECONDS)
                .filter(new Func1<CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence charSequence) {
                        return !TextUtils.isEmpty(charSequence);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()) // UI Thread
                .subscribe(new Subscriber<CharSequence>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(CharSequence charSequence) {
                        boolean isEmailValid = validateWeb(charSequence.toString());
                        if (!isEmailValid) {

                            enableError(textInputLayout,message);

                        } else {

                            disableError(textInputLayout);

                        }
                    }
                });

        compositeSubscription.add(webSubscription);


    }

    /**
     * @author Soussi
     *
     * @param message
     * Checks for validity of the IsEmpty input field
     */

    public void RxIsEmpty(final String message)
    {

        Subscription EmptySubscription = customChangeObservable
                .doOnNext(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {

                        disableError(textInputLayout);
                    }
                })
                .debounce(400, TimeUnit.MILLISECONDS)
                .filter(new Func1<CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence charSequence) {
                        return !TextUtils.isEmpty(charSequence);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()) // UI Thread
                .subscribe(new Subscriber<CharSequence>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(CharSequence charSequence) {
                        boolean isEmailValid = isEmpty(charSequence.toString());
                        if (!isEmailValid) {

                            enableError(textInputLayout,message);

                        } else {

                            disableError(textInputLayout);

                        }
                    }
                });

        compositeSubscription.add(EmptySubscription);


    }

    /**
     * @author Soussi
     *
     * @param message
     * Checks for validity of the IP input field
     */

    public void RxIp(final String message)
    {

        Subscription ipSubscription = customChangeObservable
                .doOnNext(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {

                        disableError(textInputLayout);
                    }
                })
                .debounce(400, TimeUnit.MILLISECONDS)
                .filter(new Func1<CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence charSequence) {
                        return !TextUtils.isEmpty(charSequence);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()) // UI Thread
                .subscribe(new Subscriber<CharSequence>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(CharSequence charSequence) {
                        boolean isEmailValid = validateIP(charSequence.toString());
                        if (!isEmailValid) {

                            enableError(textInputLayout,message);

                        } else {

                            disableError(textInputLayout);

                        }
                    }
                });

        compositeSubscription.add(ipSubscription);


    }

    /**
     * @author Soussi
     *
     * @param button
     * Checks for validity of the Validate Button
     */

    public void RxValidateButton(final Button button)
    {
        Observable<CharSequence> signInFieldsSubscription = (Observable<CharSequence>) Observable.combineLatest((List<? extends Observable<?>>) customChangeObservable, new FuncN<Boolean>() {
            @Override
            public Boolean call(Object... args) {
                for(int i = 0; i < args.length; i++){
                    if(!args[i].toString().isEmpty()) {
                        return false;
                    }
                }
                return true;


            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe((Observer<? super Boolean>) new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if(aBoolean)
                        {
                            button.setEnabled(true);
                        }
                        else
                        {
                            button.setEnabled(false);
                        }

                    }


                });
        compositeSubscription.add((Subscription) signInFieldsSubscription);
    }

    /**
     * @author Soussi
     *
     * @param email
     * Function Checks for validity Email and return true or false
     */

    private static boolean validateEmail(String email) {
        Pattern patternEmail = Patterns.EMAIL_ADDRESS;
        if (TextUtils.isEmpty(email))
            return false;

        matcher = patternEmail.matcher(email);
        return matcher.matches();
    }

    /**
     * @author Soussi
     *
     * @param phone
     * Function Checks for validity Phone and return true or false
     */
    private static boolean validatePhone(String phone) {
        Pattern patternEmail = Patterns.PHONE;
        if (TextUtils.isEmpty(phone))
            return false;

        matcher = patternEmail.matcher(phone);
        return matcher.matches();
    }

    /**
     * @author Soussi
     *
     * @param url
     * Function Checks for validity Url Web and return true or false
     */

    private static boolean validateWeb(String url) {
        Pattern patternEmail = Patterns.WEB_URL;
        if (TextUtils.isEmpty(url))
            return false;

        matcher = patternEmail.matcher(url);
        return matcher.matches();
    }

    /**
     * @author Soussi
     *
     * @param ip
     * Function Checks for validity IP Adresse and return true or false
     */

    private static boolean validateIP(String ip) {
        Pattern patternEmail = Patterns.IP_ADDRESS;
        if (TextUtils.isEmpty(ip))
            return false;

        matcher = patternEmail.matcher(ip);
        return matcher.matches();
    }

    /**
     * @author Soussi
     *
     * @param text1
     * Function isEmpty return true or false
     */

    private static boolean isEmpty(String text1) {

        if (TextUtils.isEmpty(text1)){
            return false;}
        else
        {
        return true;}
    }

    /**
     * @author Soussi
     *
     * @param text
     * Function Checks for validity AppEditText and return true or false
     */

    private static boolean validatAppEdittext(String text) {
        if (TextUtils.isEmpty(text))
            return false;

        matcher = pattern.matcher(text);
        return matcher.matches();
    }

    /**
     * @author Soussi
     *
     * @param msg
     * @param textInputLayout
     * Function Checks for validity  TextInputLayout (Error = true)
     */

    private static void enableError(TextInputLayout textInputLayout, String msg)

    {
        textInputLayout.setVisibility(View.VISIBLE);
        textInputLayout.setError(msg.toString());

    }

    /**
     * @author Soussi
     *
     * @param textInputLayout
     * Function Checks for validity  TextInputLayout (Error = null)
     */

    private static void disableError(TextInputLayout textInputLayout)

    {
       // textInputLayout.setVisibility(View.GONE);
        textInputLayout.setError(null);

    }


}
