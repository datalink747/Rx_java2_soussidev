package com.soussidev.kotlin.rx_java2_soussidev.RxrecyclerDataview;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.soussidev.kotlin.rx_java2_lib.RxRecyclerview.BindableView;
import com.soussidev.kotlin.rx_java2_soussidev.R;
import com.soussidev.kotlin.rx_java2_soussidev.model.Admin;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by Soussi on 14/09/2017.
 */

public class DataView extends RelativeLayout implements BindableView<Admin> {

    /**
     * No need for strong reference, because internal viewHolder implementation
     * keeps strong reference to shown item
     */
    private WeakReference<Admin> itemReference;

    private TextView admin_name;
    private TextView admin_mail;
    private ImageView admin_icon;

    public DataView(@NonNull Context context) {
        super(context);
    }

    public DataView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DataView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();

        admin_name = (TextView) findViewById(R.id.admin_name);
        admin_mail = (TextView) findViewById(R.id.admin_email);
        admin_icon =(ImageView)findViewById(R.id.admin_icon);
    }

    @Override public void bindItem(Admin item) {
        admin_name.setText(item.getName());
        admin_mail.setText(item.getEmail());

        itemReference = new WeakReference<Admin>(item);
    }

    @Override public Observable<Object> onObservableEvent() {
        return RxView.clicks(this)
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(@io.reactivex.annotations.NonNull Object o) throws Exception {
                        return itemReference.get();
                    }
                });
    }
}

