package com.soussidev.kotlin.rx_java2_soussidev;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.soussidev.kotlin.rx_java2_lib.RxRecyclerview.ObservableAdapter;
import com.soussidev.kotlin.rx_java2_lib.RxRecyclerview.SimpleAdapterSource;
import com.soussidev.kotlin.rx_java2_soussidev.model.Admin;

import java.util.Arrays;
import java.util.Collections;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import timber.log.Timber;


/**
 * A simple {@link Fragment} subclass.
 */
public class RxRecyclerviewfragment extends Fragment {

    private final SimpleAdapterSource<Admin> source = new SimpleAdapterSource<>(Collections.<Admin>emptyList(), R.layout.view_data);
    private final ObservableAdapter<Admin> adapter = new ObservableAdapter<>(source);
    private final CompositeDisposable disposable = new CompositeDisposable();
    private RecyclerView recyclerView;


    public RxRecyclerviewfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rx_recyclerview, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        source.setData(Arrays.asList(
                new Admin("User 1", "user1@gmail.com"),
                new Admin("User 2", "user2@gmail.com"),
                new Admin("User 3", "user3@gmail.com"),
                new Admin("User 4", "user4@gmail.com"),
                new Admin("User 5", "user5@gmail.com"),
                new Admin("User 6", "user6@gmail.com"),
                new Admin("User 7", "user7@gmail.com")
        ));

    }

    @Override
    public void onPause() {
        disposable.clear();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        disposable.add(adapter.onItemEvent()
                .map(new Function<ObservableAdapter.ViewEvent, Object>() {
                    @Override
                    public Object apply(@NonNull ObservableAdapter.ViewEvent viewEvent) throws Exception {
                        return viewEvent.getData();
                    }
                })
                .ofType(Admin.class)
                .subscribe(new Consumer<Admin>() {
                    @Override public void accept(@NonNull Admin data) throws Exception {
                        Toast.makeText(getActivity(), "Clicked " + data.getName(), Toast.LENGTH_SHORT).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override public void accept(@NonNull Throwable throwable) throws Exception {
                        Timber.e(throwable, "Error watching adapter");
                    }
                }));
    }

}
