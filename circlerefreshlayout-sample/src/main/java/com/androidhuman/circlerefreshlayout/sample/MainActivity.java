package com.androidhuman.circlerefreshlayout.sample;

import com.androidhuman.circlerefreshlayout.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnCircleRefreshIndicator;

    Button btnArrowRefreshIndicator;

    Button btnPullFromBottom;

    Button btnWithWebViewTop;

    Button btnWithWebViewBottom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCircleRefreshIndicator = (Button) findViewById(
                R.id.btn_activity_main_circle_refresh_indicator);

        btnArrowRefreshIndicator = (Button) findViewById(
                R.id.btn_activity_main_arrow_refresh_indicator);

        btnPullFromBottom = (Button) findViewById(
                R.id.btn_activity_main_pull_from_bottom);

        btnWithWebViewTop = (Button) findViewById(
                R.id.btn_activity_main_webview_top);

        btnWithWebViewBottom = (Button) findViewById(
                R.id.btn_activity_main_webview_bottom);

        btnCircleRefreshIndicator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RefreshLayoutActivity.class)
                        .putExtra(RefreshLayoutActivity.KEY_REFRESH_DRAWABLE_STYLE,
                                SwipeRefreshLayout.CIRCLE)
                        .putExtra(RefreshLayoutActivity.KEY_TITLE, "Circle Refresh indicator"));
            }
        });

        btnArrowRefreshIndicator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RefreshLayoutActivity.class)
                        .putExtra(RefreshLayoutActivity.KEY_REFRESH_DRAWABLE_STYLE,
                                SwipeRefreshLayout.ARROW)
                        .putExtra(RefreshLayoutActivity.KEY_TITLE, "Arrow Refresh indicator"));
            }
        });

        btnPullFromBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RefreshLayoutActivity.class)
                        .putExtra(RefreshLayoutActivity.KEY_PULL_POSITION, Gravity.BOTTOM)
                        .putExtra(RefreshLayoutActivity.KEY_TITLE, "Pull from bottom"));
            }
        });

        btnWithWebViewTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WebViewActivity.class)
                        .putExtra(WebViewActivity.KEY_PULL_POSITION, Gravity.TOP));
            }
        });

        btnWithWebViewBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WebViewActivity.class)
                        .putExtra(WebViewActivity.KEY_PULL_POSITION, Gravity.BOTTOM));
            }
        });
    }
}
