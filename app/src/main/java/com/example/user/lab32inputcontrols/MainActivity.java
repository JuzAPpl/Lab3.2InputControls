package com.example.user.lab32inputcontrols;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerAge;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale, radioButtonFemale;
    private CheckBox checkBoxSmoker;
    private TextView textViewPremium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link UI to program
        spinnerAge = (Spinner)findViewById(R.id.spinnerAge);
        radioGroupGender = (RadioGroup)findViewById(R.id.radioGroupGender);
        radioButtonMale = (RadioButton)findViewById(R.id.radioButtonMale);
        radioButtonFemale = (RadioButton)findViewById(R.id.radioButtonFemale);
        checkBoxSmoker = (CheckBox)findViewById(R.id.checkBoxSmoker);
        textViewPremium = (TextView)findViewById(R.id.textViewPremium);

        //create adapter for spinner
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.age_group,
                        android.R.layout.simple_spinner_item
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_dropdown_item_1line
        );

        spinnerAge.setOnItemSelectedListener(this);
        spinnerAge.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),"Position : " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void calculatePremium(View view){
        int premium = 0;
        int maleExtra = 0;
        int smokerExtra = 0;
        int pos;
        pos = spinnerAge.getSelectedItemPosition();

        switch (pos){
            case 0:
                premium=50;
                maleExtra=0;
                smokerExtra=0;
                break;
            case 1:
                premium=55;
                maleExtra=0;
                smokerExtra=0;
                break;
            case 2:
                premium=60;
                maleExtra=50;
                smokerExtra=0;
                break;
            case 3:
                premium=70;
                maleExtra=100;
                smokerExtra=100;
                break;
            case 4:
                premium=120;
                maleExtra=100;
                smokerExtra=150;
                break;
            case 5:
                premium=160;
                maleExtra=50;
                smokerExtra=150;
                break;
            case 6:
                premium=200;
                maleExtra=0;
                smokerExtra=250;
                break;
            case 7:
                premium=250;
                maleExtra=0;
                smokerExtra=250;
                break;
        }

        int indexGender;
        indexGender = radioGroupGender.getCheckedRadioButtonId();
        if(indexGender == R.id.radioButtonMale){
            premium+=maleExtra;
        }

        if(checkBoxSmoker.isChecked()){
            premium+=smokerExtra;
        }

        //Identify current locale
        Locale locale = Locale.getDefault();

        //format the result with currency sign attached
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        String currencyText = fmt.format(premium);

        textViewPremium.setText( getString(R.string.premium) + currencyText);



    }

    public void resetPremium(View view){
        textViewPremium.setText(getString(R.string.premium));
    }
}
