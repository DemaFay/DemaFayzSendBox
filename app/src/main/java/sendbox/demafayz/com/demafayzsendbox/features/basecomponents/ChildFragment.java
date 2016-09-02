package sendbox.demafayz.com.demafayzsendbox.features.basecomponents;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by demafayz on 01.09.16.
 */
public abstract class ChildFragment extends BaseFragment {

    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
    }
}
