package com.soussidev.kotlin.rx_java2_soussidev;


import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.soussidev.kotlin.rx_java2_lib.RxActivityResult.ActivityResult;
import com.soussidev.kotlin.rx_java2_lib.RxActivityResult.compac.RxActivityResultCompact;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


/**
 * A simple {@link Fragment} subclass.
 */
public class RxActivityResult_fragment extends Fragment {
    private TextView textresult;
    private Button buttonresult;
    private static int REQUEST_CODE = 707;

    public RxActivityResult_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rx_activity_result_fragment, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

            textresult=(TextView)view.findViewById(R.id.textresult);
            buttonresult=(Button)view.findViewById(R.id.buttonresult);
            buttonresult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   getResultActivity();
                }
            });

    }

    private void getResultActivity()
    {
        final Intent intent = new Intent(getActivity(), Result.class);

        RxActivityResultCompact.startActivityForResult(this, intent, REQUEST_CODE)
                .subscribe(new Consumer<ActivityResult>() {
                    @Override
                    public void accept(@NonNull ActivityResult result) throws Exception {
                        if (result.isOk()) {
                            final String txt = result.getData().getStringExtra(Result.GET_TEXT);
                            textresult.setText(txt);
                        }
                    }
                });

    }

}
