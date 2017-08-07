package mohammadicenter.com.mohammadicdcenter.activities.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mohammadicenter.com.mohammadicdcenter.R;
import mohammadicenter.com.mohammadicdcenter.activities.Applications.SharePrefInit;
import mohammadicenter.com.mohammadicdcenter.activities.Utils.Constants;

public class WatchVideo extends AppCompatActivity {
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_video);
        WebView displayVideo =  (WebView)findViewById(R.id.watch_video);
        TextView marquee = (TextView)findViewById(R.id.latestNews);
        String news = new SharePrefInit(WatchVideo.this).readNews();
        marquee.setText(news);
        marquee.setSelected(true);
        Bundle extra =  getIntent().getExtras();
        if(extra!=null){
            Matcher matcher = Pattern.compile("src=\"([^\"]+)\"").matcher(extra.getString(Constants.URL_KEY));
            matcher.find();
            String src = matcher.group(1);
           // Toast.makeText(WatchVideo.this, "" +"<iframe frameborder=\"2\"  width=\"480\" height=\"270\"  src=http:"+
            //src+"></iframe>", Toast.LENGTH_SHORT).show();
            //WebView displayVideo = (WebView)findViewById(R.id.webView);
            displayVideo.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });
            WebSettings webSettings = displayVideo.getSettings();
            webSettings.setJavaScriptEnabled(true);

            if(src.contains("https")){
                type ="";
            }
            else{
                type="http:";
            }
            displayVideo.loadData("<body  bgcolor='black'><center><iframe frameborder=\"0\" width=\"280\" height=\"290\"  " +
                    "src="+type+src+"></iframe>", "text/html", "utf-8");
        }

        }
    }


