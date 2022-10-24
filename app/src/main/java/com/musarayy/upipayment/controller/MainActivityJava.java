package com.musarayy.upipayment.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.musarayy.upipayment.model.UpiPaymentDetail;
import com.musarayy.upipayment.model.TransactionDetails;
import com.wangsun.upi.payment.sample.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainActivityJava extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        findViewById(R.id.id_pay_using_upi_app).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startUpiPayment();
            }
        });
    }


    private void startUpiPayment() {

        ArrayList<String> existingApps = UpiPaymentActivity.getExistingUpiApps(this);

        UpiPaymentDetail payment = new UpiPaymentDetail(
                "your VPA name",
                "ur name",
                "",
                "",
                "description",
                "1.00",
                "",
                "");

        new UpiPaymentActivity(this)
                .setPaymentDetail(payment)
                .setUpiApps(UpiPaymentActivity.getUPI_APPS())
                .setCallBackListener(new UpiPaymentActivity.OnUpiPaymentListener() {
                    @Override
                    public void onSubmitted(@NotNull TransactionDetails data) {
                        Toast.makeText(MainActivityJava.this, "transaction pending: " + data, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(@NotNull String message) {
                        Toast.makeText(MainActivityJava.this, "transaction failed: " + message, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccess(@NotNull TransactionDetails data) {
                        Toast.makeText(MainActivityJava.this, "transaction success: " + data.toString(), Toast.LENGTH_LONG).show();
                    }
                }).pay();
    }
}
