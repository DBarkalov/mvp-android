package ru.test.mvp;

/**
 * Created by dima on 04.06.16.
 */
public interface IModel  {
    void setValue(int value);
    void increment();
    void startLongRequest();
    int  getSumm();
    void setListener(ModelChangeListener listener);
    boolean isInProgress();
}
