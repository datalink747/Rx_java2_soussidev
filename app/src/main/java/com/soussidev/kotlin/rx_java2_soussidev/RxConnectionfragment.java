package com.soussidev.kotlin.rx_java2_soussidev;


import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soussidev.kotlin.rx_java2_lib.RxConnexion.ReactiveNetwork;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class RxConnectionfragment extends Fragment {

    private static final String TAG = "ReactiveNetwork";
    private TextView tvConnectivityStatus;
    private TextView tvInternetStatus;
    private Disposable networkDisposable;
    private Disposable internetDisposable;


    public RxConnectionfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rx_connection, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvConnectivityStatus = (TextView) view.findViewById(R.id.connectivity_status);
        tvInternetStatus = (TextView) view.findViewById(R.id.internet_status);

    }
    @Override
    public void onResume() {
        super.onResume();

        networkDisposable = ReactiveNetwork.observeNetworkConnectivity(getActivity())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(connectivity -> {
                    Log.d(TAG, connectivity.toString());
                    final NetworkInfo.State state = connectivity.getState();
                    final String name = connectivity.getTypeName();
                    tvConnectivityStatus.setText(String.format("state: %s, typeName: %s", state, name));
                });

        internetDisposable = ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isConnected -> tvInternetStatus.setText(isConnected.toString()));
    }

    @Override
    public void onPause() {
        super.onPause();
        safelyDispose(networkDisposable, internetDisposable);
    }

    private void safelyDispose(Disposable... disposables) {
        for (Disposable subscription : disposables) {
            if (subscription != null && !subscription.isDisposed()) {
                subscription.dispose();
            }
        }
    }

}
