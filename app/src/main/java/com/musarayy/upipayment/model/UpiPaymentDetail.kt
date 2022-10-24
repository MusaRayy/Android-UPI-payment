package com.musarayy.upipayment.model

import java.io.Serializable

data class UpiPaymentDetail(
    var vpa: String,
    var name: String,
    var payeeMerchantCode: String,
    var txnRefId: String,
    var description: String,
    var amount: String,
    var txnId: String = "",
    var currency: String = "INR"
) : Serializable {

    companion object {
        val ARG_BUNDLE = UpiPaymentDetail::class.java.name + "arg_bundle"
    }
}