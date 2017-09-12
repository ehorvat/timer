package interview.oreilly.timer;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class TimerActivity extends AppCompatActivity {

    TimerViewController mViewController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViewController(TimerViewController.class, savedInstanceState);
    }

    public <T extends TimerViewController> void setViewController(Class<T> clazz, Bundle savedInstanceState) {
        T viewController = createViewController(clazz, savedInstanceState);
        if (viewController == null) {
            throw new NullPointerException("ViewController must not be null");
        }
        setViewController(viewController);
    }

    public <T extends TimerViewController> void setViewController(T viewController) {
        mViewController = viewController;
        View view = viewController.getView();
        setContentView(view);
    }

    public <T extends TimerViewController> T createViewController(Class<T> clazz, Bundle savedInstanceState) {
        try {
            return new TimerViewController.Factory(this).create(clazz, savedInstanceState);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}