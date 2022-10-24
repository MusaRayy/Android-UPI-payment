package com.musarayy.upipayment.controller

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.musarayy.upipayment.model.UpiPaymentDetail
import com.musarayy.upipayment.model.TransactionDetails
import com.wangsun.upi.payment.sample.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        id_pay_using_upi_app.setOnClickListener {
            startUpiPayment()
        }
    }


    private fun startUpiPayment(){
        val payment = UpiPaymentDetail(
            vpa="your VPA",
            name = "Ur name",
            payeeMerchantCode = "",
            txnRefId = "",
            description = "description",
            amount = "1.00")


        UpiPaymentActivity(this)
            .setPaymentDetail(payment)
            .setUpiApps(UpiPaymentActivity.UPI_APPS)
            .setCallBackListener(object : UpiPaymentActivity.OnUpiPaymentListener{
                override fun onSubmitted(data: TransactionDetails) {
                    Toast.makeText(this@MainActivity,"transaction pending: $data",Toast.LENGTH_LONG).show()
                }
                override fun onSuccess(data: TransactionDetails) {
                    Toast.makeText(this@MainActivity,"transaction success: $data",Toast.LENGTH_LONG).show()
                }
                override fun onError(message: String) {
                    Toast.makeText(this@MainActivity,"transaction failed: $message",Toast.LENGTH_LONG).show()
                }
            }).pay()


        val existingApps = UpiPaymentActivity.getExistingUpiApps(this)
        Log.i(this.localClassName, "existing app: $existingApps" )
    }
}
