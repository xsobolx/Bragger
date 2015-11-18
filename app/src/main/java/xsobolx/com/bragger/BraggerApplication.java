package xsobolx.com.bragger;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by xsobolx on 13.11.2015.
 */
public class BraggerApplication extends Application {

    public static final String APPLICATION_ID = "rNxMmsuO9OFrQLU7JuLbtsEO8MCqnr8oLLsiMyjv";
    public static final String CLIENT_KEY = "2gCRs8VMGYARX9mfK40FyGjZ5hUhrbA6C1fdtFh9";

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);

        Parse.initialize(this,
                APPLICATION_ID, CLIENT_KEY);

    }
}
