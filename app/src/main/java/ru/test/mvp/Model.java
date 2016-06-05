package ru.test.mvp;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

/**
 * Created by dima on 04.06.16.
 */
public class Model implements IModel {

    public static final int INIT_VALUE = 10;

    private WeakReference<ModelChangeListener> mListener;
    private int count;
    private LoadTask mLoadTask;

    private static Model sModel;

    public static Model newInstance() {
        if (sModel == null) {
            sModel = new Model();
        }
        return sModel;
    }

    private Model() {
        count = INIT_VALUE;
    }

    @Override
    public void setListener(ModelChangeListener mListener) {
        this.mListener = new WeakReference<ModelChangeListener>(mListener);
    }

    @Override
    public boolean isInProgress() {
        return mLoadTask != null;
    }

    @Override
    public void setValue(int value) {
        count = value;
        if (mListener != null && mListener.get() != null) {
            mListener.get().onCountChanged();
        }
    }

    @Override
    public void increment() {
        count++;
        if (mListener != null && mListener.get() != null) {
            mListener.get().onCountChanged();
        }
    }

    @Override
    public int getSumm() {
        return count;
    }

    @Override
    public void startLongRequest() {
        if (mLoadTask == null) {
            mLoadTask = new LoadTask();
            mLoadTask.execute(new Integer(count));
            if (mListener != null && mListener.get() != null) {
                mListener.get().onStartRequest();
            }
        }
    }

    private class LoadTask extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {
            //// FIXME: test
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return params[0] + 1000;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            mLoadTask = null;
            if (mListener != null && mListener.get() != null) {
                mListener.get().onStopRequest();
            }
            setValue(integer.intValue());
        }
    }
}
