package sendbox.demafayz.com.demafayzsendbox.features.maincomtext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import sendbox.demafayz.com.demafayzsendbox.R;
import sendbox.demafayz.com.demafayzsendbox.features.basecomponents.BaseActivity;
import sendbox.demafayz.com.demafayzsendbox.utils.ContextUtil;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showBaseFragment();
    }

    private void showBaseFragment() {
        ContextUtil.showFragment(this, FeaturesListFragment.newInstance(), false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }
}
