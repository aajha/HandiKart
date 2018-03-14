package com.example.handikart;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;

public class Seller_Number_verification extends AppCompatActivity {


    Button mButton;
    Button reverifyButton;
    EditText mEdit;
    String Number;
    TextInputLayout numberWrapper;
    TextInputLayout OTPWrapper;
    EditText otpEdit;
    private String message;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 100;

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_number_verification);

        numberWrapper = (TextInputLayout) findViewById(R.id.layoutRegisterContacttext);
        numberWrapper.setHint("Enter Phone Number");

        OTPWrapper = (TextInputLayout) findViewById(R.id.layoutEnterOTP);
        OTPWrapper.setHint("Enter OTP :");

        //get contact number and check if it is 10 digits, else return error and reload
        mButton = (Button) findViewById(R.id.registercontactbutton);
        mEdit = (EditText) findViewById(R.id.registerContacttext);
        otpEdit = (EditText)findViewById(R.id.enterOTPText);
        reverifyButton = (Button) findViewById(R.id.reverifyButton);

        /*mEdit.setOnTouchListener(new View.OnTouchListener(){
            @SuppressLint("ClickableViewAccessibility")
            public boolean onTouch(View arg0, MotionEvent arg1)
            {
                numberWrapper.setError(null);
                return true;
            }
        });*/
        mEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    hideKeyboard();
                    return true;
                }
                return false;
            }
        });

        otpEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                mButton.setVisibility(View.GONE);
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    hideKeyboard();
                    matchOTP();
                    return true;
                }
                return false;
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyNumber();

            }
        });

        reverifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());

            }
        });

    }


    void matchOTP()
    {
        String message_rcvd = OTPWrapper.getEditText().getText().toString();
        if (message_rcvd.equals(message))
        {
            //go to next page
            Toast.makeText(getApplicationContext(), "OTP matches..Number verified", Toast.LENGTH_LONG).show();
        }
        else
        {
            //otp does not match->bring the REVERIFY button
            Toast.makeText(getApplicationContext(), "OTP does not match..", Toast.LENGTH_LONG).show();
            reverifyButton.setVisibility(View.VISIBLE);
            //do reverification

        }
    }

    void verifyNumber()
    {
        //boolean error=true;
        numberWrapper.setError(null);
        Number=numberWrapper.getEditText().getText().toString();

        View focusView = null;
        //if number field is empty
        if (TextUtils.isEmpty(Number)) {
            numberWrapper.setError(getString(R.string.error_field_required));
            focusView = numberWrapper;
        }
        else
        {
            //if no. is not of length 10
            if (Number.length()!=10)
            {
                numberWrapper.setError("Enter Valid 10-digit number");
                focusView = numberWrapper;
            }
            //no. is valid
            else
            {
                //error=false;
                //Toast.makeText(getApplicationContext(), "Sending OTP and verifying...Please Wait...", Toast.LENGTH_LONG).show();

                numberWrapper.setVisibility(View.GONE);

                //generate OTP

                int OTPlength =3;
                message = generateOTP(OTPlength);


                ///////////////////////////////////

                message = "1234";

                //send OTP

                sendSMSMessage();

                //make enter otp visible
                OTPWrapper.setVisibility(View.VISIBLE);

                //Toast.makeText(getApplicationContext(), "OTP Sent...", Toast.LENGTH_LONG).show();
                //get OTP
                //boolean otp_match=matchOTP(message_rcvd);



            }
        }

    }

    protected void sendSMSMessage() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
        else {
            sendMessage();
        }
    }

    private void sendMessage() {
        SmsManager smsManager = SmsManager.getDefault();
        String num= "+91"+Number;
        smsManager.sendTextMessage(num, null, message, null, null);
        //Toast.makeText(getApplicationContext(), "SMS sent.",Toast.LENGTH_LONG).show();
    }

    private String generateOTP(int otPlength) {
        int lowerlimit= (int) Math.pow(10,otPlength);
        int upperlimit= ((int) Math.pow(10,otPlength+1))-1;
        Random randomNum = new Random();
        int OTP = lowerlimit + randomNum.nextInt(upperlimit-lowerlimit);
        String Otp= Integer.toString(OTP);
        return Otp;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    sendMessage();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS sending failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }
}
