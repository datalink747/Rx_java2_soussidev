package com.soussidev.kotlin.rx_java2_soussidev;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

public class Result extends AppCompatActivity {
    public static String GET_TEXT = "RESULT_TEXT";
    private SwitchCompat switchresult;
    private Button sendresult;
    private Intent data = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        switchresult=(SwitchCompat)findViewById(R.id.switchresult);
        sendresult=(Button)findViewById(R.id.sendresult);

        switchresult.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b) {
                    data.putExtra(GET_TEXT, "Service is Enable");
                }


                else {
                    data.putExtra(GET_TEXT, "Service is Disable");
                }


            }
        });

        sendresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 if(!switchresult.isChecked())
                {
                    data.putExtra(GET_TEXT, "Service is Disable");
                }

                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });
    }
}
