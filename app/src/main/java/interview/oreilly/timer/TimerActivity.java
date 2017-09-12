package interview.oreilly.timer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TimerActivity extends AppCompatActivity {

    TimerViewController mViewController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViewController(TimerViewController.class, savedInstanceState);
    }

    /*
     * Normally these generic methods would be used in a BaseActivity class.
     * For simplicity I added them here and forewent the BaseActivity approach
     * since I only have one activity.
     */

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
