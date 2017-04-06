package com.demo.wondersdaili.mvp.widget;

import android.content.Context;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.demo.wondersdaili.mvp.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * 界面控制
 */
public class EmptyLayout extends FrameLayout {

    public static final int STATUS_HIDE = 1001;
    public static final int STATUS_LOADING = 1;
    public static final int STATUS_NO_NET = 2;
    public static final int STATUS_NO_DATA = 3;
    private Context mContext;
    private OnRetryListener mOnRetryListener;
    private int mEmptyStatus = STATUS_LOADING;
    private String NoDataReason;

    TextView mTvEmptyNoData;

    TextView mTvEmptyNoNet;

    FrameLayout mRlEmptyContainer;

    ProgressBar mEmptyLoading;

    FrameLayout mEmptyLayout;

    public EmptyLayout(Context context) {
        this(context, null);
    }

    public EmptyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        View.inflate(mContext, R.layout.layout_empty_loading, this);
        mEmptyLayout = (FrameLayout) findViewById(R.id.empty_layout);
        mTvEmptyNoNet = (TextView) findViewById(R.id.tv_net_error);
        mRlEmptyContainer = (FrameLayout) findViewById(R.id.fl_empty_container);
        mTvEmptyNoData = (TextView) findViewById(R.id.tv_no_data);
        mEmptyLoading = (ProgressBar) findViewById(R.id.empty_loading);
        mEmptyLayout.setBackgroundColor(getResources().getColor(R.color.color_window_background));
        mTvEmptyNoNet.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRetryListener != null) {
                    mOnRetryListener.onRetry();
                }
            }
        });
        switchEmptyView();
    }

    /**
     * 隐藏视图
     */
    public void hide() {
        mEmptyStatus = STATUS_HIDE;
        switchEmptyView();
    }

    /**
     * 设置状态
     *
     * @param emptyStatus
     */
    public void setEmptyStatus(@EmptyStatus int emptyStatus, String reason) {
        mEmptyStatus = emptyStatus;
        if (reason != null){
            NoDataReason = reason;
        }
        switchEmptyView();
    }

    /**
     * 获取状态
     *
     * @return 状态
     */
    public int getEmptyStatus() {
        return mEmptyStatus;
    }

    /**
     * 设置异常消息
     *
     * @param msg 显示消息
     */
    public void setEmptyMessage(String msg) {
        mTvEmptyNoNet.setText(msg);
    }

    public void hideErrorIcon() {
        mTvEmptyNoNet.setCompoundDrawables(null, null, null, null);
    }


    /**
     * 切换视图
     */
    private void switchEmptyView() {
        switch (mEmptyStatus) {
            case STATUS_LOADING:
                setVisibility(VISIBLE);
                mRlEmptyContainer.setVisibility(GONE);
                mEmptyLoading.setVisibility(VISIBLE);
                break;
            case STATUS_NO_DATA:
                setVisibility(VISIBLE);
                mRlEmptyContainer.setVisibility(VISIBLE);
                mEmptyLoading.setVisibility(GONE);
                mTvEmptyNoNet.setVisibility(GONE);
                mTvEmptyNoData.setVisibility(VISIBLE);
                mTvEmptyNoData.setText(NoDataReason);
                break;
            case STATUS_NO_NET:
                setVisibility(VISIBLE);
                mRlEmptyContainer.setVisibility(VISIBLE);
                mEmptyLoading.setVisibility(GONE);
                mTvEmptyNoData.setVisibility(GONE);
                mTvEmptyNoNet.setVisibility(VISIBLE);
                break;
            case STATUS_HIDE:
                setVisibility(GONE);
                break;
            default:
                break;
        }
    }

    /**
     * 设置重试监听器
     *
     * @param retryListener 监听器
     */
    public void setRetryListener(OnRetryListener retryListener) {
        this.mOnRetryListener = retryListener;
    }


    /**
     * 点击重试监听器
     */
    public interface OnRetryListener {
        void onRetry();
    }


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STATUS_LOADING, STATUS_NO_NET, STATUS_NO_DATA, STATUS_HIDE})
    @interface EmptyStatus {
    }
}