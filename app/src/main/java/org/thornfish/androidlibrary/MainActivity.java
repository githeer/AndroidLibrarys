package org.thornfish.androidlibrary;

import android.os.Bundle;

import org.thornfish.androidlibrary.base.CityBaseActivity;

public class MainActivity extends CityBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
