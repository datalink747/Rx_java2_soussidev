package com.soussidev.kotlin.rx_java2_soussidev;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.soussidev.kotlin.rx_java2_lib.RxVerify.RxVerify;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.RxVerifyResult;
import com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.custom.RxTextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Pattern;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class RxVerifyfragment extends Fragment {
    private EditText edit_name,edit_email,edit_password,edit_ConfirmPassword,edit_Age,edit_ip,
            edit_url;
    private Button btn_verify;
    private static final String dateFormat = "dd-MM-yyyy";
    private static final SimpleDateFormat dateF = new SimpleDateFormat(dateFormat, Locale.FRANCE);
    // For Support 7
    private AppCompatEditText edit_phone_compac,edit_email_compac,edit_url_compac;
    private TextInputLayout inputphone_compac,inputemail_compac,inputurl_compac;

    public RxVerifyfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rx_verify, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edit_name=(EditText)view.findViewById(R.id.edit_name);
        edit_Age=(EditText)view.findViewById(R.id.edit_Age);
        edit_password=(EditText)view.findViewById(R.id.edit_password);
        edit_ConfirmPassword=(EditText)view.findViewById(R.id.edit_ConfirmPassword);
        edit_email=(EditText)view.findViewById(R.id.edit_email);
        edit_ip=(EditText)view.findViewById(R.id.edit_Ip4);
        edit_url=(EditText)view.findViewById(R.id.edit_Url);

        edit_phone_compac=(AppCompatEditText)view.findViewById(R.id.edit_phone_compac);
        inputphone_compac=(TextInputLayout)view.findViewById(R.id.input_phone_compac);
        edit_url_compac=(AppCompatEditText)view.findViewById(R.id.edit_url_compac);
        inputurl_compac=(TextInputLayout)view.findViewById(R.id.input_url_compac);
        edit_email_compac=(AppCompatEditText)view.findViewById(R.id.edit_email_compac);
        inputemail_compac=(TextInputLayout)view.findViewById(R.id.input_email_compac);

        btn_verify=(Button)view.findViewById(R.id.btn_verify);

        //RxVerify IP
        RxVerify.createFor(edit_ip)
                .ip4("Invalid IP4 format")
                .onValueChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxVerifyResult<EditText>>() {
                    @Override public void call(RxVerifyResult<EditText> result) {
                        result.getItem().setError(result.isProper() ? null : result.getMessage());
                        Log.i(TAG, "VerifyCompac result " + result.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        Log.e(TAG, "VerifyCompac error", throwable);
                    }
                });

        //RxVerify Name
        RxVerify.createFor(edit_name)
                .nonEmpty("Name Empty!")
                .onValueChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxVerifyResult<EditText>>() {
                    @Override public void call(RxVerifyResult<EditText> result) {
                        result.getItem().setError(result.isProper() ? null : result.getMessage());
                        Log.i(TAG, "VerifyCompac result " + result.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        Log.e(TAG, "VerifyCompac error", throwable);
                    }
                });
        //RxVerify Your Password
        RxVerify.createFor(edit_password)
                .nonEmpty()
                .minLength(8, "Min length is 8")
                .onFocusChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxVerifyResult<EditText>>() {
                    @Override public void call(RxVerifyResult<EditText> result) {
                        result.getItem().setError(result.isProper() ? null : result.getMessage());
                        Log.i(TAG, "VerifyCompac result " + result.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        Log.e(TAG, "VerifyCompac error", throwable);
                    }
                });

        //RxVerify Confirm Your Password Match or Not
        RxVerify.createFor(edit_ConfirmPassword)
                .sameAs(edit_password, "Password does not match")
                .onFocusChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxVerifyResult<EditText>>() {
                    @Override public void call(RxVerifyResult<EditText> result) {
                        result.getItem().setError(result.isProper() ? null : result.getMessage());
                        Log.i(TAG, "VerifyCompac result " + result.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        Log.e(TAG, "VerifyCompac error", throwable);
                    }
                });

        //RxVerify Your E-mail and Ckeck Validate Domain Name
        RxVerify.createFor(edit_email)
                .nonEmpty()
                .email()
                .with(new VerifyEmailDomain())
                .onValueChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxVerifyResult<EditText>>() {
                    @Override public void call(RxVerifyResult<EditText> result) {
                        result.getItem().setError(result.isProper() ? null : result.getMessage());
                        Log.i(TAG, "VerifyCompac result " + result.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        Log.e(TAG, "VerifyCompac error", throwable);
                    }
                });

        //RxVerify Url
        RxVerify.createFor(edit_url)
                .Url("Invalid Url format")
                .nonEmpty()
                .onValueChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxVerifyResult<EditText>>() {
                    @Override public void call(RxVerifyResult<EditText> result) {
                        result.getItem().setError(result.isProper() ? null : result.getMessage());
                        Log.i(TAG, "VerifyCompac result " + result.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        Log.e(TAG, "VerifyCompac error", throwable);
                    }
                });

        //RxVerify Age
        RxVerify.createFor(edit_Age)
                .age("You have to be 25 year old", 25, dateF)
                .onValueChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxVerifyResult<EditText>>() {
                    @Override public void call(RxVerifyResult<EditText> result) {
                        result.getItem().setError(result.isProper() ? null : result.getMessage());
                        Log.i(TAG, "VerifyCompac result " + result.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        Log.e(TAG, "VerifyCompac error", throwable);
                    }
                });
        //Call fun to showing DataPicker
        setDatePickerListener(edit_Age);

        //Now Using RxVerify For Support 7

        //RxVerifyCompac Phone
       /* RxVerifyCompac.createFor(edit_phone)
                .Phone("Invalid Phone Number")
                .onValueChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxVerifyCompacResult<AppCompatEditText>>() {
                    @Override public void call(RxVerifyCompacResult<AppCompatEditText> result) {
                        result.getItem().setError(result.isProper() ? null : result.getMessage());
                        Log.i(TAG, "VerifyCompac result " + result.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        Log.e(TAG, "VerifyCompac error", throwable);
                    }
                });*/



        RxTextInputLayout.createFor(edit_phone_compac,inputphone_compac).RxEmail("incorrect Phone Number !!");
        RxTextInputLayout.createFor(edit_email_compac,inputemail_compac).RxVerifyCustom(Patterns.EMAIL_ADDRESS,"Incorect E-mail !!");
        RxTextInputLayout.createFor(edit_url_compac,inputurl_compac).RxIp("Invalide IP Adresse !!");
       // RxTextInputLayout.ButtonValidate(btn_verify);
        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"ok",Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void showDatePicker(final EditText birthday) {
        DatePickerDialog dpd =
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Date selectedDate = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
                        birthday.setText(dateF.format(selectedDate));
                    }
                }, 2017, 0, 1);
        dpd.show();
    }

    private void setDatePickerListener(final EditText birthday) {
        birthday.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                showDatePicker((EditText) v);
            }
        });
    }

    }


