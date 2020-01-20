package com.example.validation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName, editTextEmail, editTextMobile,
            editTextPassword,confTextPassword;

    private Button buttonSubmit;
    private AwesomeValidation awesomeValidation;
    EditText editText;
    public static final String DATE_FORMAT_3 = "yyyy-MM-dd";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //initializing view objects
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextMobile = (EditText) findViewById(R.id.editTextMobile);
        confTextPassword = (EditText) findViewById(R.id.confTextPassword);
        editText = findViewById(R.id.editText1);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c=Calendar.getInstance();
                int mYear=c.get(Calendar.YEAR);
                int mMonth=c.get(Calendar.MONTH);
                int mDay=c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String nd = "" + dayOfMonth;
                        String nm = "" + month ;
                        if ( dayOfMonth < 10 ){
                            nd = "0"+ dayOfMonth;
                        }

                        if ( (month + 1) < 10){
                            nm = "0"+ ( month +1 ) ;
                        }else {
                            nm = ""+ ( month + 1) ;
                        }
                        Log.d("testValue" , String.valueOf( month ));

                        editText.setText(( year + "-" +  nm + "-" + nd));
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });

        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        
        //adding validation to edittexts
        awesomeValidation.addValidation(this, R.id.editTextName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.editTextEmail, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.editTextPassword, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.passworderror);
        awesomeValidation.addValidation(this, R.id.editTextMobile, "^[2-9]{2}[0-9]{8}$", R.string.mobileerror);
        awesomeValidation.addValidation(this,R.id.confTextPassword,R.id.editTextPassword,R.string.invalidpass);


        buttonSubmit.setOnClickListener(this);
    }



    private void submitForm() {
        //first validate the form then move ahead
        //if this becomes true that means validation is successfull
        if (awesomeValidation.validate()) {
            Toast.makeText(this, "Registration Successfull", Toast.LENGTH_LONG).show();

            //process the data further
        }
    }

    @Override
    public void onClick(View view) {
        if (view == buttonSubmit) {
            submitForm();
        }
    }

}
