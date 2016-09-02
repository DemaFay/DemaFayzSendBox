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
//        String result = YandexMoneyAPI.instanceId();
//        String result = YandexMoneyAPI.requestExternalPayment("p2p",
//                "1234567890ABCDEF1234567890ABCDEF1234567890ABCDEF1234567890ABCDEF1",
//                "41001101140",
//                100);
//        Object result = YandexMoneyAPI.requestProcessExternalPayment("3931303833373438395f343434316466313864616236613236363063386361663834336137386537643935383639383062635f3330363333323938",
//                "1234567890ABCDEF1234567890ABCDEF1234567890ABCDEF1234567890ABCDEF1",
//                "yandexmoneyapp%3A%2F%2Fsuccess",
//                "yandexmoneyapp%3A%2F%2Ffail");
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