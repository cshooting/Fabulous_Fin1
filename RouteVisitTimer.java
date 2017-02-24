package fabulous.a511.sports.com.fabulous_fin;

/**
 * Created by shooting on 2017/2/1.
 */

import android.content.Context;
import android.os.SystemClock;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.util.Formatter;
import java.util.IllegalFormatException;
import java.util.Locale;


@RemoteViews.RemoteView
public class RouteVisitTimer extends TextView {
    private static final String TAG = "Chronometer";


    public interface OnChronometerTickListener {

        void onChronometerTick(RouteVisitTimer routeVisitTimer);

    }

    private long mBase;
    private long mNow; // the currently displayed time
    private boolean mVisible;
    private boolean mStarted;
    private boolean mRunning;
    private boolean mLogged;
    private String mFormat;
    private Formatter mFormatter;
    private Locale mFormatterLocale;
    private Object[] mFormatterArgs = new Object[1];
    private StringBuilder mFormatBuilder;
    private OnChronometerTickListener mOnChronometerTickListener;
    private StringBuilder mRecycle = new StringBuilder(8);
    private boolean mCountDown;

    public RouteVisitTimer(Context context) {
        this(context, null, 0);
    }

    public RouteVisitTimer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RouteVisitTimer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mBase = System.currentTimeMillis();
        updateText(mBase);
    }

    public void setCountDown(boolean countDown) {
        mCountDown = countDown;
        updateText(SystemClock.elapsedRealtime());
    }

    public boolean isCountDown() {
        return mCountDown;
    }

    public void setBase(long base) {
        mBase = base;
        dispatchChronometerTick();
        updateText(System.currentTimeMillis());
    }

    public long getBase() {
        return mBase;
    }

    public void setFormat(String format) {
        mFormat = format;
        if (format != null && mFormatBuilder == null) {
            mFormatBuilder = new StringBuilder(format.length() * 2);
        }
    }

    public String getFormat() {
        return mFormat;
    }

    public void setOnChronometerTickListener(OnChronometerTickListener listener) {
        mOnChronometerTickListener = listener;
    }

    public OnChronometerTickListener getOnChronometerTickListener() {
        return mOnChronometerTickListener;
    }
//开始计时
    public void start() {
        mStarted = true;
        updateRunning();
    }
//停止计时
    public void stop() {
        mStarted = false;
        updateRunning();
    }
//重置时间
    public void setStarted(boolean started) {
        mStarted = started;
        updateRunning();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mVisible = false;
        updateRunning();
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        mVisible = visibility == VISIBLE;
        updateRunning();
    }

    private synchronized void updateText(long now) {
        mNow = now;
        long seconds = mCountDown ? mBase - now : now - mBase;
        seconds /= 1000;
        if (seconds < 0) {
            seconds = 0;
        }
        String text = DateUtils.formatElapsedTime(mRecycle, seconds);

        if (mFormat != null) {
            Locale loc = Locale.getDefault();
            if (mFormatter == null || !loc.equals(mFormatterLocale)) {
                mFormatterLocale = loc;
                mFormatter = new Formatter(mFormatBuilder, loc);
            }
            mFormatBuilder.setLength(0);
            mFormatterArgs[0] = text;
            try {
                mFormatter.format(mFormat, mFormatterArgs);
                text = mFormatBuilder.toString();
            } catch (IllegalFormatException ex) {
                if (!mLogged) {
                    Log.w(TAG, "Illegal format string: " + mFormat);
                    mLogged = true;
                }
            }
        }
        setText(text);
    }

    private void updateRunning() {
        boolean running = mVisible && mStarted;
        if (running != mRunning) {
            if (running) {
                updateText(System.currentTimeMillis());
                dispatchChronometerTick();
                postDelayed(mTickRunnable, 1000);
            } else {
                removeCallbacks(mTickRunnable);
            }
            mRunning = running;
        }
    }

    private final Runnable mTickRunnable = new Runnable() {
        @Override
        public void run() {
            if (mRunning) {
                updateText(System.currentTimeMillis());
                dispatchChronometerTick();
                postDelayed(mTickRunnable, 1000);
            }
        }
    };

    void dispatchChronometerTick() {
        if (mOnChronometerTickListener != null) {
            mOnChronometerTickListener.onChronometerTick(this);
        }
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return Chronometer.class.getName();
    }
}
