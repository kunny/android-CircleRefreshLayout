package com.androidhuman.circlerefreshlayout.sample;

import com.androidhuman.circlerefreshlayout.SwipeRefreshLayout;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity
        implements SwipeRefreshLayout.OnRefreshListener {

    public static final String KEY_PULL_POSITION = "pull_position";

    SwipeRefreshLayout srlRefresh;

    WebView wvWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        srlRefresh = (SwipeRefreshLayout) findViewById(R.id.srl_activity_webview_refresh);
        srlRefresh.setOnRefreshListener(this);

        @SwipeRefreshLayout.PullPosition
        int pullPosition = getIntent().getIntExtra(KEY_PULL_POSITION, Gravity.TOP);
        srlRefresh.setPullPosition(pullPosition);

        wvWebView = (WebView) findViewById(R.id.wv_activity_webview);
        wvWebView.getSettings().setJavaScriptEnabled(true);
        wvWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                setTitle("Loading...");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                String pageTitle = view.getTitle();
                if (!TextUtils.isEmpty(pageTitle)) {
                    setTitle(pageTitle);
                }
                if (srlRefresh.isRefreshing()) {
                    srlRefresh.setRefreshing(false);
                }
            }
        });
        wvWebView.loadUrl("http://d.android.com");
    }

    @Override
    public void onBackPressed() {
        if (wvWebView.canGoBack()) {
            wvWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onRefresh() {
        wvWebView.reload();
    }
}
