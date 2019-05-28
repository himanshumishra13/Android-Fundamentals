package com.example.himanshu.droidcafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private TextView orderText;
    private String mDeliveryMessage;
    private RadioButton mDefaultSelected;
    private Spinner mSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        orderText = findViewById(R.id.orderText);
        mDefaultSelected = findViewById(R.id.sameday);
        mDefaultSelected.setChecked(true);
        mSpinner = findViewById(R.id.label_spinner);
        if(mSpinner!=null)
        {
            mSpinner.setOnItemSelectedListener(this);
        }
        Intent intent = getIntent();
        orderText.setText(intent.getStringExtra(MainActivity.EXTRA_MESSAGE));
        ArrayAdapter<CharSequence> mArrayAdapter = ArrayAdapter.createFromResource(this,R.array.labels_array,android.R.layout.simple_spinner_item);
        mArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if(mSpinner!=null)
        {
            mSpinner.setAdapter(mArrayAdapter);
        }

    }

    public void onRadioButtonClicked(View view) {
        int viewId = view.getId();
        boolean isSelected = ((RadioButton) view).isChecked();

        switch (viewId)
        {
            case R.id.sameday:
            {
                mDeliveryMessage = getText(R.string.sameday_text).toString();
                break;
            }
            case R.id.nextday:
            {
                mDeliveryMessage = getText(R.string.nextday_text).toString();
                break;
            }
            case R.id.pickup:
            {
                mDeliveryMessage = getText(R.string.pickup_text).toString();
                break;
            }
            default:
            {

            }
        }

        displayToast(mDeliveryMessage);
    }

    public void displayToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String spinnerLabel = adapterView.getItemAtPosition(i).toString();
        displayToast(spinnerLabel);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
