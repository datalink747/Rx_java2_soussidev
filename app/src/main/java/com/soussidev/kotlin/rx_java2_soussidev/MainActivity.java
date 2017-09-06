package com.soussidev.kotlin.rx_java2_soussidev;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.soussidev.kotlin.rx_java2_lib.RxBus_java;
import com.soussidev.kotlin.rx_java2_soussidev.bus.RxBusAdmin;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private CompositeDisposable disposables;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        disposables = new CompositeDisposable();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs1);
        tabLayout.setupWithViewPager(viewPager);




        registerToEvents();



    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new
                ViewPagerAdapter(getSupportFragmentManager());

        //change the fragmentName as per your need

        adapter.addFragment(new RxSharedpref_fragment(), "RxSharedPref");
        adapter.addFragment(new RxBusfragment(), "RxBus");
        adapter.addFragment(new Rxanimationfragment(), "RxAnimation");
        adapter.addFragment(new RxConnectionfragment(), "RxConnection");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


    }



    //For RxBusfragment
    private void registerToEvents() {
        final RxBusAdmin rxBusAdmin = RxBusAdmin.getInstance();

        rxBusAdmin.onAdminChanged()
                .doOnSubscribe(disposables::add)
                .subscribe(
                       /* this::display,
                        this::displayError*/
                        admin -> {
                            Toast.makeText(getBaseContext(), admin.getName(), Toast.LENGTH_SHORT).show();
                            Log.d("TAG :onAdminChanged", "Email: " + admin.getEmail());
                        }
                );

        rxBusAdmin.onEvent("myMessage")
                .doOnSubscribe(disposables::add)
                .subscribe(
                        this::display,
                        this::displayError

                );

        rxBusAdmin.allEvents()
                .doOnSubscribe(disposables::add)
                .subscribe(
                        this::display,
                        this::displayError
                );

        RxBus_java.getDefault()
                .onEvent("eventName")
                .doOnSubscribe(disposables::add)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.d("TAG :default event :",String.valueOf(s.toString()));
                    }
                });
    }

    @Override
    protected void onDestroy() {
        this.disposables.clear();
        super.onDestroy();
    }

    private void display(Object o) {
        Toast.makeText(getBaseContext(), o.toString(), Toast.LENGTH_SHORT).show();
        ;
    }

    private void displayError(Throwable t) {
        Toast.makeText(getBaseContext(), t.toString(), Toast.LENGTH_SHORT).show();
    }

}
