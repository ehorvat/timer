package interview.oreilly.timer;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qozix.spatula.BindView;
import com.qozix.spatula.OnClick;

import interview.oreilly.controller.ViewController;

/**
 * Created by ehorvat on 9/11/17.
 */

public class TimerViewController extends ViewController {

    @BindView(R.id.textinputedittext_timer)
    private TextInputEditText mTextInputEditText;
    @BindView(R.id.button_start_timer)
    private Button mButtonStartTimer;
    @BindView(R.id.button_pause_timer)
    private Button mButtonPauseTimer;
    @BindView(R.id.button_reset_timer)
    private Button mButtonResetTimer;
    @BindView(R.id.textview_timer_display)
    private TextView mTextViewTimerDisplay;
    @BindView(R.id.textview_timer_error)
    private TextView mTextViewTimerError;

    Resources resources;

    // Note this is my own implementation, not from Java's Timer class.
    private Timer timer;

    private Timer.CountDownListener mCountDownListener = new Timer.CountDownListener() {

        private int mCurrentTime;

        private Runnable mUpdateTimer = new Runnable() {
            @Override
            public void run() {
                mTextViewTimerDisplay.setText(String.valueOf(mCurrentTime));
            }
        };

        @Override
        public void onTick(int newTime) {
            mCurrentTime = newTime;
            getView().post(mUpdateTimer);
        }

        @Override
        public void onFinish() {
            mTextViewTimerDisplay.setText(R.string.timer_finished);
        }

    };


    @Override
    public View createUi(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.activity_main, null);
    }

    @Override
    public void uiCreated(Bundle savedInstanceState) {
        timer = new Timer();
        timer.setCountDownListener(mCountDownListener);
        resources = getActivity().getResources();
    }

    @Override
    public void uiAttached() {}

    @Override
    public void uiDetached() {}


    @OnClick(R.id.button_start_timer)
    public void startTimer(View v) {
        // Check if timer is paused and not finished
        if(timer.isPaused() && !timer.isFinished()) {
            // Timer is being resumed
            timer.pause(false);
            // Start countdown from where we left off
            timer.startCountdown(Integer.parseInt(mTextViewTimerDisplay.getText().toString()));
        } else {
            // Finish any current running timers
            timer.finish();
            // Hide errors
            mTextViewTimerError.setVisibility(View.GONE);
            try {
                // Parse timer starting integer from edittext
                int startTime = Integer.parseInt(mTextInputEditText.getText().toString());
                // Set display to input value
                mTextViewTimerDisplay.setText(String.valueOf(startTime));
                // Start count down
                timer.startCountdown(startTime);
            } catch(NumberFormatException ex) {
                showError(resources.getString(R.string.error));
            }

        }
    }

    @OnClick(R.id.button_pause_timer)
    public void pauseTimer(View v) {
        timer.pause(true);
    }

    @OnClick(R.id.button_reset_timer)
    public void resetTimer(View v) {
        timer.finish();
        mTextViewTimerDisplay.setText(resources.getString(R.string.zero));
        mTextInputEditText.setText(resources.getString(R.string.empty));
    }

    private void showError(String message) {
        mTextViewTimerError.setText(message);
        mTextViewTimerDisplay.setText(resources.getString(R.string.zero));
        mTextViewTimerError.setVisibility(View.VISIBLE);
    }

}
