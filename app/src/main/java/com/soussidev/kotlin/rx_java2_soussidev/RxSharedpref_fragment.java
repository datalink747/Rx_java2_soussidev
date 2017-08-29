package com.soussidev.kotlin.rx_java2_soussidev;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soussidev.kotlin.rx_java2_lib.RxSharedPreferences_java;
import com.soussidev.kotlin.rx_java2_soussidev.model.Admin;

import io.reactivex.Observable;


public class RxSharedpref_fragment extends Fragment {


    public RxSharedpref_fragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rx_sharedpref, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        final RxSharedPreferences_java rxShared = RxSharedPreferences_java.with(sharedPreferences);

        rxShared.putString("dev", "Soussi")
                .flatMap( name-> rxShared.putString("name", "soussi"))
                .flatMap(email -> rxShared.putString("email", "soussi.mohamed747@gmail.com"))
                .flatMap(phone -> rxShared.putInteger("tel", 55055055))
                .flatMap(post -> rxShared.putString("post", "dev android"))

                .flatMap(post -> rxShared.getAll())
                .flatMap(stringMap -> Observable.fromIterable(stringMap.entrySet()))
                .map(Object::toString)

                .subscribe(s -> Log.d("TAG 1", s));

        rxShared.getInt("tel", 0)
                .subscribe(tel -> {
                    //display phone
                    Log.d("TAG 2", "phone: " + tel);
                });

        Observable.just(new Admin())
                .flatMap(user -> rxShared.getInt("id", 0), (admin, id) -> {
                    admin.setId(id);
                    return admin;
                })
                .flatMap(admin -> rxShared.getString("name", ""), (admin, name) -> {
                    admin.setName(name);
                    return admin;
                })

                .flatMap(admin -> rxShared.getString("post", ""), (admin, post) -> {
                    admin.setPost(post);
                    return admin;
                })

                .flatMap(admin -> rxShared.getString("email", ""), (admin, email) -> {
                    admin.setEmail(email);
                    return admin;
                })

                .flatMap(admin -> rxShared.getInt("tel", 0), (admin, phone) -> {
                    admin.setTel(phone);
                    return admin;
                })

                .subscribe(admin -> {
                    //display admin
                    Log.d("TAG 3", admin.toString());
                    Log.d("TAG 4", admin.getEmail());
                    Log.d("TAG 5", admin.getTel().toString());
                });

    }


}
