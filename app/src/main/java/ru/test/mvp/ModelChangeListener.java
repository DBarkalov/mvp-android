package ru.test.mvp;

/**
 * Created by dima on 04.06.16.
 */
public interface ModelChangeListener {
    void onCountChanged();
    void onStartRequest();
    void onStopRequest();
}

