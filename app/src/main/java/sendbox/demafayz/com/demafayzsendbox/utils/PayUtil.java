package sendbox.demafayz.com.demafayzsendbox.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.yandex.money.api.methods.params.P2pTransferParams;
import com.yandex.money.api.methods.params.PaymentParams;
import com.yandex.money.api.methods.params.PhoneParams;

import java.math.BigDecimal;

import ru.yandex.money.android.PaymentActivity;

/**
 * Created by demafayz on 01.09.16.
 */
public class PayUtil {

    private static final String CLIENT_ID = "1234567890ABCDEF1234567890ABCDEF1234567890ABCDEF1234567890ABCDEF1";
    private static final String HOST = "https://demomoney.yandex.ru";
    private static final int REQUEST_CODE = 1;

    private Context context;
    private Fragment fragment;
    private AppCompatActivity activity;

    public PayUtil(Fragment fragment) {
        this.fragment = fragment;
        this.context = fragment.getContext();
    }

    public PayUtil(AppCompatActivity activity) {
        this.activity = activity;
        this.context = activity;
    }


    public void pay() {
        PaymentParams params = new P2pTransferParams.Builder("1234567812345678")
                .setAmount(new BigDecimal(1)).create();
        Intent intent = PaymentActivity.getBuilder(context)
                .setPaymentParams(params)
                .setClientId(CLIENT_ID)
                .setHost(HOST)
                .build();
        if (fragment != null) {
            fragment.startActivityForResult(intent, REQUEST_CODE);
        } else if (activity != null) {
            activity.startActivityForResult(intent, REQUEST_CODE);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
