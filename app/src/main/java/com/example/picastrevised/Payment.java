package com.example.picastrevised;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

public class Payment extends AppCompatActivity {

    public static final String clientKey = "AQxHSBpNQ4ys-OJ95SZMx6XSd7uFBONXuSqdjfYcTtq94EOOkdsdayCJ_6CoIE0xGykzZ2egBVRqdjaS";
    private EditText amountEdt;
    private TextView paymentTV;
    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == Activity.RESULT_OK){
                    PaymentConfirmation confirm = result.getData().getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                    if(confirm != null){
                        try {
                            String paymentDetails = confirm.toJSONObject().toString(4);
                            JSONObject payObj = new JSONObject(paymentDetails);
                            String payID = payObj.getJSONObject("response").getString("id");
                            String state = payObj.getJSONObject("response").getString("state");
                            paymentTV.setText("Payment " + state + "\n with payment id is " + payID);
                        } catch (JSONException e) {
                            Log.e("Error", "an extremely unlikely failure occurred: ", e);
                        }
                    }
                }else if(result.getResultCode() == Activity.RESULT_CANCELED){
                    Log.i("paymentExample", "The user canceled");
                }else if(result.getResultCode() == PaymentActivity.RESULT_EXTRAS_INVALID){
                    Log.i("paymentExample", "An invalid Payment was made");
                }
            });

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(clientKey);

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_payment);

        amountEdt = findViewById(R.id.idEdtAmount);
        Button makePaymentBtn = findViewById(R.id.idBtnPay);
        paymentTV = findViewById(R.id.idTVStatus);

        makePaymentBtn.setOnClickListener(view -> {
            getPayment();
        });
        Toast.makeText(this, Login.region, Toast.LENGTH_SHORT).show();
    }

    private void getPayment(){
        String amount = amountEdt.getText().toString();

        PayPalPayment payment = null;

        String payRegion = Login.region;

        if(payRegion.equals("United States"))
            payment = new PayPalPayment(new BigDecimal(amount), "USD", "PICasT Artworks",
                    PayPalPayment.PAYMENT_INTENT_SALE);
        else if(payRegion.equals("Singapore"))
            payment = new PayPalPayment(new BigDecimal(amount), "SGD", "PICasT Artworks",
                    PayPalPayment.PAYMENT_INTENT_SALE);
        else if(payRegion.equals("Japan"))
            payment = new PayPalPayment(new BigDecimal(amount), "JPY", "PICasT Artworks",
                    PayPalPayment.PAYMENT_INTENT_SALE);
        else
            payment = new PayPalPayment(new BigDecimal(amount), "PHP", "PICasT Artworks",
                    PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

        launcher.launch(intent);
    }
}