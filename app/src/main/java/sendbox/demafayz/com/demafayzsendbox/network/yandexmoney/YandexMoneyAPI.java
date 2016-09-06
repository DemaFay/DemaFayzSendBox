package sendbox.demafayz.com.demafayzsendbox.network.yandexmoney;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import sendbox.demafayz.com.demafayzsendbox.utils.AppUtil;

/**
 * Created by demafayz on 02.09.16.
 */
public class YandexMoneyAPI {

    private static final String HOST = "https://money.yandex.ru";
    private static final String INSTANCE_ID_URL = HOST + "/api/instance-id";
    private static final String CLIENT_ID = "1234567890ABCDEF1234567890ABCDEF1234567890ABCDEF1234567890ABCDEF1";
    private static final String INSTANCE_ID_OUTPUT = "client_id=" + CLIENT_ID;
    private static final String REQUEST_EXTERNAL_PAYMENT_URL = HOST + "/api/request-external-payment";
    private static final String PROCESS_EXTERNAL_PAYMENT_URL = HOST + "/api/process-external-payment";

    public static Result requestProcessExternalPayment(String requestId, String instanceId, String extAuthSuccessUri, String extAuthFailUri) {
        String output = "request_id=" + requestId +
                "&instance_id=" + instanceId +
                "&ext_auth_success_ur=" + extAuthSuccessUri +
                "&ext_auth_fail_uri=" + extAuthFailUri;
        InputStream is = null;
        OutputStream os = null;
        HttpURLConnection conn = null;
        String success = null;
        Result successResult = null;
        try {
            URL url = new URL(PROCESS_EXTERNAL_PAYMENT_URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(output.length()));
            os = new BufferedOutputStream(conn.getOutputStream());
            OutputStreamWriter osw = new OutputStreamWriter(os);
            writeRequestExternalPaymentStream(osw, output);
            int code = conn.getResponseCode();
            if (code != HttpURLConnection.HTTP_OK) {
                AppUtil.dLog(YandexMoneyAPI.class, "Request error: " + code);
            } else {
                is = new BufferedInputStream(conn.getInputStream());
                success = readStream(is);
                successResult = getProcessExternalPaymentResult(success);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return successResult;
    }

    private static Result getProcessExternalPaymentResult(String success) throws JSONException {
        JSONObject object = new JSONObject(success);
        Result result = new Result();
        String status = object.getString("status");
        if (status.equals("success")) {
            String invoicId = object.getString("invoice_id");
            result.setType(Result.Type.SUCCESS);
            result.setInvoiceId(invoicId);
            AppUtil.dLog(YandexMoneyAPI.class, "result: " + invoicId);
        } else if (status.equals("in_progress")) {
            long nextRetry = Integer.valueOf(object.getString("next_retry"));
            result.setType(Result.Type.RETRY);
            result.setNextRetry(nextRetry);
            AppUtil.dLog(YandexMoneyAPI.class, "result: " + nextRetry);
        } else if (status.equals("refused")) {
            String error = object.getString("error");
            result.setType(Result.Type.ERROR);
            result.setError(error);
            AppUtil.dLog(YandexMoneyAPI.class, "result: " + error);
        }
        return result;
    }

    public static String requestExternalPayment(String patternId, String instanceId, String to, double amount) {
        String output = "pattern_id=" + patternId +
                "&instance_id=" + instanceId +
                "&to=" + to +
                "&amount_due=" + amount;
        InputStream is = null;
        OutputStream os = null;
        HttpURLConnection conn = null;
        String requestId = null;
        try {
            URL url = new URL(REQUEST_EXTERNAL_PAYMENT_URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(output.length()));
            os = new BufferedOutputStream(conn.getOutputStream());
            OutputStreamWriter osw = new OutputStreamWriter(os);
            writeRequestExternalPaymentStream(osw, output);
            int code = conn.getResponseCode();
            if (code != HttpURLConnection.HTTP_OK) {
                AppUtil.dLog(YandexMoneyAPI.class, "Request error: " + code);
            } else {
                is = new BufferedInputStream(conn.getInputStream());
                requestId = readStream(is);
                requestId = getRequestExternalPaymentResult(requestId);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return requestId;
    }

    private static String getRequestExternalPaymentResult(String result) throws JSONException {
        String requestId = null;
        JSONObject object = new JSONObject(result);
        String status = object.getString("status");
        if (status.equals("success")) {
            requestId = object.getString("request_id");
        }
        return requestId;
    }

    private static void writeRequestExternalPaymentStream(OutputStreamWriter osw, String output) throws IOException {
        osw.write(output);
        osw.flush();
    }

    public static String instanceId () {
        InputStream is = null;
        OutputStream os = null;
        HttpURLConnection conn = null;
        String result = null;
        try {
            URL url = new URL(INSTANCE_ID_URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(INSTANCE_ID_OUTPUT.length()));
            os = new BufferedOutputStream(conn.getOutputStream());
            writeInstanceIdStream(os);
            int code = conn.getResponseCode();
            if (code != HttpURLConnection.HTTP_OK) {
                AppUtil.dLog(YandexMoneyAPI.class, "Request error: " + code);
                result = null;
            } else {
                is = new BufferedInputStream(conn.getInputStream());
                result = readStream(is);
                result = getResult(result);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static String getResult(String result) throws JSONException {
        String instanceId = null;
        JSONObject jsonObject = new JSONObject(result);
        String status = jsonObject.getString("status");
        if (status.equals("success")) {
            instanceId = jsonObject.getString("instance_id");
        }
        return instanceId;
    }

    private static String readStream(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuffer result = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    private static void writeInstanceIdStream(OutputStream os) throws IOException {
        os.write(INSTANCE_ID_OUTPUT.getBytes());
        os.flush();
    }

    public static class Result {

        private Type type;
        private String invoiceId;
        private String error;
        private long nextRetry;

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        public String getInvoiceId() {
            return invoiceId;
        }

        public void setInvoiceId(String invoiceId) {
            this.invoiceId = invoiceId;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public long getNextRetry() {
            return nextRetry;
        }

        public void setNextRetry(long nextRetry) {
            this.nextRetry = nextRetry;
        }

        public enum Type {
            SUCCESS, ERROR, RETRY
        }
    }
}
