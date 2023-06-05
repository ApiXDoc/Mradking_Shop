package com.mradking.mradkingshop.kirana_store.franchige.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shreyaspatil.EasyUpiPayment.EasyUpiPayment;
import com.shreyaspatil.EasyUpiPayment.listener.PaymentStatusListener;
import com.shreyaspatil.EasyUpiPayment.model.TransactionDetails;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class test extends AppCompatActivity implements PaymentStatusListener {

    private static final int UPI_PAYMENT = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        payUsingUPI("8130652073@ibl", "Neetu", "123456", "05.00", "Payment for Purchase");


        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault());
        String transcId = df.format(c);

        makePayment("01.00", "8130652073@ibl", "Neetu", "test", transcId);


    }



    private void payUsingUPI(String upiId, String name, String txnId, String amount, String note) {
        String upiUri = "upi://pay?pa=" + upiId + "&pn=" + name + "&tr=" + txnId + "&am=" + amount + "&cu=INR&tn=" + note;

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(upiUri));

        Intent chooser = Intent.createChooser(intent, "Pay with");
        if (chooser.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(this, "No UPI app found, please install one to continue", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case UPI_PAYMENT:
                if (data != null) {
                    Bundle bundle = data.getExtras();
                    String status = bundle.getString("Status");

                    if (status.equals("SUCCESS")) {
                        Toast.makeText(this, "Payment successful", Toast.LENGTH_SHORT).show();
                    } else if (status.equals("FAILURE")) {
                        Toast.makeText(this, "Payment failed", Toast.LENGTH_SHORT).show();
                    } else if (status.equals("SUBMITTED")) {
                        Toast.makeText(this, "Payment submitted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Payment cancelled or aborted by user", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
    private void makePayment(String amount, String upi, String name, String desc, String transactionId) {
        // on below line we are calling an easy payment method and passing
        // all parameters to it such as upi id,name, description and others.
        final EasyUpiPayment easyUpiPayment = new EasyUpiPayment.Builder()
                .with(this)
                // on below line we are adding upi id.
                .setPayeeVpa(upi)
                // on below line we are setting name to which we are making payment.
                .setPayeeName(name)
                // on below line we are passing transaction id.
                .setTransactionId(transactionId)
                // on below line we are passing transaction ref id.
                .setTransactionRefId(transactionId)
                // on below line we are adding description to payment.
                .setDescription(desc)
                // on below line we are passing amount which is being paid.
                .setAmount(amount)
                // on below line we are calling a build method to build this ui.
                .build();
        // on below line we are calling a start
        // payment method to start a payment.
        easyUpiPayment.startPayment();
        // on below line we are calling a set payment
        // status listener method to call other payment methods.
        easyUpiPayment.setPaymentStatusListener(this);
    }

    @Override
    public void onTransactionCompleted(TransactionDetails transactionDetails) {
        // on below line we are getting details about transaction when completed.
        String transcDetails = transactionDetails.getStatus().toString() + "\n" + "Transaction ID : " + transactionDetails.getTransactionId();
        Toast.makeText(this, "complete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionSuccess() {
        // this method is called when transaction is successful and we are displaying a toast message.
        Toast.makeText(this, "Transaction successfully completed..", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionSubmitted() {
        // this method is called when transaction is done
        // but it may be successful or failure.
        Log.e("TAG", "TRANSACTION SUBMIT");
    }

    @Override
    public void onTransactionFailed() {
        // this method is called when transaction is failure.
        Toast.makeText(this, "Failed to complete transaction", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionCancelled() {
        // this method is called when transaction is cancelled.
        Toast.makeText(this, "Transaction cancelled..", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAppNotFound() {
        // this method is called when the users device is not having any app installed for making payment.
        Toast.makeText(this, "No app found for making transaction..", Toast.LENGTH_SHORT).show();
    }
}

