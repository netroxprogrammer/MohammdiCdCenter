package mohammadicenter.com.mohammadicdcenter.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import mohammadicenter.com.mohammadicdcenter.R;
import mohammadicenter.com.mohammadicdcenter.activities.Utils.Constants;

public class VisitWebsite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_website);
        WebView  web  = (WebView)findViewById(R.id.MyWebsite);
        web.setWebViewClient(new MyBrowser());


        web.getSettings().setLoadsImagesAutomatically(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        web.loadUrl(Constants.WEBSITE_URL);
            }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
