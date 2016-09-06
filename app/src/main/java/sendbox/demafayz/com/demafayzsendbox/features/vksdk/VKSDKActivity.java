package sendbox.demafayz.com.demafayzsendbox.features.vksdk;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import sendbox.demafayz.com.demafayzsendbox.R;
import sendbox.demafayz.com.demafayzsendbox.features.basecomponents.BaseActivity;
import sendbox.demafayz.com.demafayzsendbox.utils.ContextUtil;

/**
 * Created by demafayz on 06.09.16.
 */

public class VKSDKActivity extends BaseActivity {

    private VKPhotosFragment vkPhotosFragment;
    private ViewHolder vh;
    private class ViewHolder {
        public FrameLayout flFragmentContainer;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vksdk);
        populateViewHolder();
        showFirstFragment();
    }

    private void showFirstFragment() {
        vkPhotosFragment = VKPhotosFragment.newInstance();
        ContextUtil.showFragment(this, vkPhotosFragment, false);
    }

    private void populateViewHolder() {
        vh = new ViewHolder();
        vh.flFragmentContainer = (FrameLayout) findViewById(R.id.flFragmentContainer);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (vkPhotosFragment != null) vkPhotosFragment.onActivityResult(requestCode, resultCode, data);
    }
}
