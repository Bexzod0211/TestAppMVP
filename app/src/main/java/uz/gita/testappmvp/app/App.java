package uz.gita.testappmvp.app;

import android.app.Application;

import uz.gita.testappmvp.db.MySharedPreferences;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MySharedPreferences.init(this);
    }
}
