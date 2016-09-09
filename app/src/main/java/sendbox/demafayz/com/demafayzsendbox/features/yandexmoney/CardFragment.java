package sendbox.demafayz.com.demafayzsendbox.features.yandexmoney;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sendbox.demafayz.com.demafayzsendbox.R;
import sendbox.demafayz.com.demafayzsendbox.features.basecomponents.ChildFragment;
import sendbox.demafayz.com.demafayzsendbox.network.yandexmoney.YandexMoneyAPI;
import sendbox.demafayz.com.demafayzsendbox.utils.PayUtil;

/**
 * Created by demafayz on 01.09.16.
 */
public class CardFragment extends ChildFragment {

    private PayUtil payUtil;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_card, container, false);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        payUtil = new PayUtil(this);
        payUtil.pay();
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void doInBackground(Context context) {
        String instanceId = YandexMoneyAPI.instanceId();
        String requestId = YandexMoneyAPI.requestExternalPayment("p2p",
                instanceId,
                YandexMoneyAPI.SELLER_PURSE,
                100);
        YandexMoneyAPI.Result externalPayment = YandexMoneyAPI.requestProcessExternalPayment(requestId,
                instanceId,
                "yandexmoneyapp://oauth/authorize/success",
                "yandexmoneyapp://oauth/authorize/fail");
    }

    @Override
    protected void onPostExecute() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static CardFragment newInstance() {
        CardFragment fragment = new CardFragment();
        return fragment;
    }
}