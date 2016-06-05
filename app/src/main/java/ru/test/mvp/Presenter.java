package ru.test.mvp;

/**
 * Created by dima on 04.06.16.
 */
public class Presenter implements IPresenter, ModelChangeListener {

    private final IModel mModel;
    private final IView mView;

    public Presenter(IModel model, IView view) {
        this.mModel = model;
        this.mView = view;
    }

    @Override
    public void onCreate() {
        mModel.setListener(this);
        updateView();
    }

    private void updateView() {
        updateCount();
        if (mModel.isInProgress()) {
            onStartRequest();
        }
    }

    private void updateCount() {
        mView.showSumm(String.valueOf(mModel.getSumm()));
    }

    @Override
    public void increment() {
        mModel.increment();
    }

    @Override
    public void start() {
        mModel.startLongRequest();
    }

    @Override
    public void onCountChanged() {
        updateCount();
    }

    @Override
    public void onStartRequest() {
        mView.disableStartButton();
        mView.showProgress();
    }

    @Override
    public void onStopRequest() {
        mView.hideProgress();
        mView.enableStartButton();
    }
}
