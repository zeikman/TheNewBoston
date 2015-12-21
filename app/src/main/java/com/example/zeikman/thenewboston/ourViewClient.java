package com.example.zeikman.thenewboston;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by zeikman on 12/21/15.
 */
public class ourViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView v, String url) {
        v.loadUrl(url);
        return true;
    }
}
