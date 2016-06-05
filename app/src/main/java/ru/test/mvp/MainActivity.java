package ru.test.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements IView {

    private TextView mSummTextView;
    private Button mIncrementButton;
    private Button mStartButton;
    private ProgressBar mProgress;
    private IPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSummTextView = (TextView) findViewById(R.id.summ);
        mIncrementButton = (Button) findViewById(R.id.increment);
        mStartButton = (Button) findViewById(R.id.start);
        mProgress = (ProgressBar) findViewById(R.id.progress);

        mPresenter = new Presenter(Model.newInstance(), this);
        mPresenter.onCreate();

        mIncrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.increment();
            }
        });
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.start();
            }
        });
    }

    @Override
    public void showSumm(String text) {
        mSummTextView.setText(text);
    }

    @Override
    public void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void enableStartButton() {
        mStartButton.setEnabled(true);
    }

    @Override
    public void disableStartButton() {
        mStartButton.setEnabled(false);
    }

}
