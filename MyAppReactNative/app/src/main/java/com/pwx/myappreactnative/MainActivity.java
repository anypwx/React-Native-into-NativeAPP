package com.pwx.myappreactnative;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;

public class MainActivity extends Activity implements DefaultHardwareBackBtnHandler {

    private ReactRootView reactRootView;
    private ReactInstanceManager reactInstanceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reactRootView  = new ReactRootView(this);
        reactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName("index.android.bundle")
                .setJSMainModuleName("index.android")
                .addPackage(new MainReactPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
        reactRootView.startReactApplication(reactInstanceManager,"HelloWorld",null);
        setContentView(reactRootView);

    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (reactInstanceManager != null) {
            reactInstanceManager.onHostPause(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (reactInstanceManager != null) {
            reactInstanceManager.onHostResume(this,this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (reactInstanceManager != null) {
            reactInstanceManager.onHostDestroy(this);
        }
    }

    @Override
    public void onBackPressed() {
        if (reactInstanceManager != null) {
            reactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && reactInstanceManager != null) {
            reactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
