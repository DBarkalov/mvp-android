package ru.test.mvp;

/**
 * Created by dima on 04.06.16.
 */
public interface IView {
    void showSumm(String test);
    void showProgress();
    void hideProgress();
    void enableStartButton();
    void disableStartButton();
}
