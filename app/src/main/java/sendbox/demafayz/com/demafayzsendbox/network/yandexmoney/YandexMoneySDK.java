package sendbox.demafayz.com.demafayzsendbox.network.yandexmoney;

import android.content.Context;

/**
 * Created by demafayz on 06.09.16.
 */
public class YandexMoneySDK {

    private Context context;
    private OnResult onResult;
    private PayType payType;
    private String instanceID;
    private String requestId;
    private YandexMoneyAPI.Result successResult;

    public YandexMoneySDK(Context context) {
        this.context = context;
    }

    public void pay(PayType payType, String to, double amount, String extAuthSuccessUri, String extAuthFailUri) {
        this.payType = payType;
        instanceID = YandexMoneyAPI.instanceId();
        String patternId = null;
        if (payType.equals(PayType.P2P)) {
            patternId = "p2p";
        }
        requestId = YandexMoneyAPI.requestExternalPayment(patternId, instanceID, to, amount);

        payRun(requestId, instanceID, extAuthSuccessUri, extAuthFailUri);

    }

    private void payRun(String requestId, String instanceID, String extAuthSuccessUri, String extAuthFailUri) {
        successResult = YandexMoneyAPI.requestProcessExternalPayment(requestId, instanceID, extAuthSuccessUri, extAuthFailUri);
        if (successResult.getType().equals(YandexMoneyAPI.Result.Type.SUCCESS)) {
            if (onResult != null) onResult.onSuccess(successResult.getInvoiceId());
        } else if (successResult.getType().equals(YandexMoneyAPI.Result.Type.ERROR)) {
            if (onResult != null) onResult.onError(successResult.getError());
        } else if (successResult.getType().equals(YandexMoneyAPI.Result.Type.RETRY)) {
            long nextRetry = successResult.getNextRetry();
            try {
                Thread.sleep(nextRetry);
                payRun(requestId, instanceID, extAuthSuccessUri, extAuthFailUri);
            } catch (InterruptedException e) {
                e.printStackTrace();
                payRun(requestId, instanceID, extAuthSuccessUri, extAuthFailUri);
            }
        }
    }

    public void setOnResult(OnResult onResult) {
        this.onResult = onResult;
    }

    public interface OnResult {
        public void onSuccess(String invoiceId);
        public void onError(String error);
    }

    public enum PayType {
        P2P, PHONE
    }

    public String getInstanceID() {
        return instanceID;
    }
}
