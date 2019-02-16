package com.multiplelanguage.Language;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.multiplelanguage.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class App extends Application {

    protected SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        checkLanguage();

    } // function of onCreate

    private void checkLanguage() {

        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);

        if (sharedPreferences.getString("language", "ar").equals("ar")) {

            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath("fonts/ElMessiri-Regular.ttf")
                    .setFontAttrId(R.attr.fontPath)
                    .build()
            );

            Log.e("QP", "font App : ar");
        } else if (sharedPreferences.getString("language", "ar").equals("en")) {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath("fonts/OpenSans-Regular.ttf")
                    .setFontAttrId(R.attr.fontPath)
                    .build()
            );

            Log.e("QP", "font App : " + sharedPreferences.getString("language", ""));
        }
    } // function of checkLanguage


    @Override
    protected void attachBaseContext(Context newBase) {
        sharedPreferences = newBase.getSharedPreferences("user", MODE_PRIVATE);
        super.attachBaseContext(CalligraphyContextWrapper.wrap(new MyContextWrapper(newBase).wrap(sharedPreferences.getString("language", "ar"))));
    }// apply fonts


} // class of App

