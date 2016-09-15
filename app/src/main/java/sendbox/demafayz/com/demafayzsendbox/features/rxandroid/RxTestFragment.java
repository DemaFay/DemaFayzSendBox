package sendbox.demafayz.com.demafayzsendbox.features.rxandroid;

import rx.Observable;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func0;
import sendbox.demafayz.com.demafayzsendbox.R;
import sendbox.demafayz.com.demafayzsendbox.utils.AppUtil;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

/**
 * Created by demafayz on 15.09.16.
 */
public class RxTestFragment extends Fragment implements View.OnClickListener {

    private ViewHolder vh;
    private Looper backgroundLooper;
    private int startCount = 0;

    private class ViewHolder {
        public TextView tvResult;
        public Button btnRxStart;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_rx_test, container, false);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        populateViewHolder(view);
        populateRxBackground();
    }

    private void populateRxBackground() {
        BackgroundThread backgroundThread = new BackgroundThread();
        backgroundThread.start();
        backgroundLooper = backgroundThread.getLooper();
    }

    private void populateViewHolder(View view) {
        vh = new ViewHolder();
        vh.btnRxStart = (Button) view.findViewById(R.id.btnRxStart);
        vh.btnRxStart.setOnClickListener(this);

        vh.tvResult = (TextView) view.findViewById(R.id.tvResult);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnRxStart:
                startRx();
                break;
        }
    }

    private void startRx() {
        Observable observable = sampleObservable();
        observable.subscribeOn(AndroidSchedulers.from(backgroundLooper))
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                AppUtil.dLog(RxTestFragment.class, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                AppUtil.dLog(RxTestFragment.class, "onError: " + e.toString());
            }

            @Override
            public void onNext(String o) {
                AppUtil.dLog(RxTestFragment.class, "onNext: " + o);
                vh.tvResult.setText(o);
            }
        });
    }

    private Observable<String> sampleObservable() {
        Observable observable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(5));
                    startCount++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw OnErrorThrowable.from(e);
                }
                return Observable.just("RxStart count: " + String.valueOf(startCount));
            }
        });
        return observable;
    }

    static class BackgroundThread extends HandlerThread {
        BackgroundThread() {
            super("SchedulerSample-BackgroundThread", THREAD_PRIORITY_BACKGROUND);
        }
    }

    public static RxTestFragment newInstance() {
        RxTestFragment fragment = new RxTestFragment();
        return fragment;
    }
}
