package mohammadicenter.com.mohammadicdcenter.activities.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import mohammadicenter.com.mohammadicdcenter.R;
import mohammadicenter.com.mohammadicdcenter.activities.Utils.Constants;

public class FacebookPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_page);
        WebView web = (WebView) findViewById(R.id.myfbpage);

        web.setWebViewClient(new FacebookPage.MyBrowser());
        web.getSettings().setLoadsImagesAutomatically(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        web.loadUrl(Constants.FACBOOK_URL);
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
