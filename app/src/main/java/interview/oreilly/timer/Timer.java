package interview.oreilly.timer;

import android.os.Handler;

/**
 * Created by ehorvat on 9/11/17.
 */

public class Timer  {

    private CountDownListener mCountDownListener;

    Handler mHandler = new Handler();

    private boolean mPaused, mFinished;


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mStartTime -= 1;
            if(mStartTime <= 0) {
                mCountDownListener.onFinish();
                finish();
            } else{
                mCountDownListener.onTick(mStartTime);
                countdown();
            }
        }
    };

    private final int interval = 1000; // 1 second

    private int mStartTime;

    public void startCountdown(int startTime) {
        mStartTime = startTime;
        mFinished = false;
        countdown();
    }

    private void countdown() {
        mHandler.postDelayed(runnable, interval);
    }

    // The finish method removes handler callbacks, removes paused state and sets timer to finished
    public void finish() {
        mHandler.removeCallbacks(runnable);
        pause(false);
        mFinished = true;
    }

    public boolean isFinished() {
        return mFinished;
    }

    public void pause(boolean pauseTimer) {
        mHandler.removeCallbacks(runnable);
        mPaused = pauseTimer;
    }

    public boolean isPaused() {
        return mPaused;
    }

    public void setCountDownListener(CountDownListener countDownListener) {
        mCountDownListener = countDownListener;
    }

    public interface CountDownListener {
        void onTick(int newTime);
        void onFinish();
    }
}
