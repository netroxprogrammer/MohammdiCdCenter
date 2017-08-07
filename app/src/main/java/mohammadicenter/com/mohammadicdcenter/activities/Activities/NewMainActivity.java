package mohammadicenter.com.mohammadicdcenter.activities.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mohammadicenter.com.mohammadicdcenter.R;
import mohammadicenter.com.mohammadicdcenter.activities.Applications.SharePrefInit;
import mohammadicenter.com.mohammadicdcenter.activities.Applications.VolleyInitializer;
import mohammadicenter.com.mohammadicdcenter.activities.Models.LiveStreaming;
import mohammadicenter.com.mohammadicdcenter.activities.Models.News;
import mohammadicenter.com.mohammadicdcenter.activities.Utils.Constants;
import mohammadicenter.com.mohammadicdcenter.activities.VisitWebsite;

public class NewMainActivity extends AppCompatActivity {
    JSONObject reader;
    ArrayList<LiveStreaming> list;
    Button videoLectures,goWebsite,contactUs;
    SharePrefInit pref;
    WebView watchLiveStreaming;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
       this.requestWindowFeature(Window.FEATURE_NO_TITLE);
       this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_new_main);
        pref = new SharePrefInit(NewMainActivity.this);
         setContentView(R.layout.activity_new_main);
        list =  new ArrayList<>();
        setXML();
        readPersonsData();
        if(list.size()<=0) {
            readPersonsData();
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public  void  clickonWebView(View v){
        Toast.makeText(this,"shpwoow",Toast.LENGTH_LONG).show();


    }
    public  void setXML(){
        readLatestNews();

                TextView marquee = (TextView)findViewById(R.id.latestNews);
          if(pref.readNews()==null) {
              readLatestNews();
              marquee = (TextView) findViewById(R.id.latestNews);
              String news = new SharePrefInit(NewMainActivity.this).readNews();
              marquee.setText(news);
              marquee.setSelected(true);
        }else{
              String readNews  = pref.readNews();
            Log.v("ReadPref", readNews);
            marquee.setText(Html.fromHtml(readNews));
        }
        marquee.setSelected(true);
        watchLiveStreaming = (WebView) findViewById(R.id.videoView_liveStream);
    }

    public void setVideoLectures(View v){
        Intent intent = new Intent(NewMainActivity.this, DataGridView.class);
        startActivity(intent);
    }
    public void goToMyWebsite(View  v){
        Intent intent = new Intent(NewMainActivity.this, VisitWebsite.class);
        startActivity(intent);
    }
    public void goToContact(View  v){
        Intent intent = new Intent(NewMainActivity.this, ContactUs.class);
        startActivity(intent);
    }
    public void goToFacebook(View  v){
        Intent intent = new Intent(NewMainActivity.this, FacebookPage.class);
        startActivity(intent);
    }

    /**
     * Read Latest Video Streamming
     */
    public void readPersonsData(){

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Live Streaming Loading");
        pDialog.show();

        StringRequest sendResqurst = new StringRequest(Request.Method.GET, Constants.LIVE_STREAMING_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    reader = new JSONObject(response);
                    JSONObject data1 = reader.getJSONObject("result");
                    String value = data1.getString("success");
                    if (value.equalsIgnoreCase("1")) {
                        JSONArray jArray = reader.getJSONArray("data");
                        for (int i = 0; i < jArray.length(); i++) {
                            LiveStreaming live = new LiveStreaming();
                            Log.e("data ", jArray.toString());
                            JSONObject data = jArray.getJSONObject(i);
                            live.setLiveId(Integer.parseInt(data.getString("live_id")));
                            live.setLiveType(data.getString("live_type"));
                            live.setLiveURL(data.getString("live_url"));

                            list.add(live);
                            Log.v("live",live.getLiveURL());
                            Matcher matcher = Pattern.compile("src=\"([^\"]+)\"").matcher(live.getLiveURL().toString());
                            matcher.find();
                            String src = matcher.group(1);
                            // Toast.makeText(WatchVideo.this, "" +"<iframe frameborder=\"2\"  width=\"480\" height=\"270\"  src=http:"+
                            //src+"></iframe>", Toast.LENGTH_SHORT).show();
                            //WebView displayVideo = (WebView)findViewById(R.id.webView);
                            watchLiveStreaming.setWebViewClient(new WebViewClient(){
                                @Override
                                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                    return false;
                                }
                            });
                            String type;
                            if(src.contains("https")){
                                type ="";
                            }
                            else{
                                type="http:";
                            }
                            Log.v("Live2",src);
                            WebSettings webSettings = watchLiveStreaming.getSettings();
                            webSettings.setJavaScriptEnabled(true);
                            watchLiveStreaming.loadData("<body  bgcolor='black'><center><iframe height=\"290\"  scrolling=\"no\" src="+
                                    type+src+"></iframe>", "text/html", "utf-8");

                        }


                    }


                    pDialog.hide();

                }catch(JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error ",error.toString());
                pDialog.hide();
            }
        });

        VolleyInitializer.getmInstance(this).addToRequestQueue(sendResqurst);
    }

    /**
     * Read Latets News
     */
    public void readLatestNews(){

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("please  Wait");
        pDialog.show();

        StringRequest sendResqurst = new StringRequest(Request.Method.GET, Constants.NEW_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    reader = new JSONObject(response);
                    JSONObject data1 = reader.getJSONObject("result");
                    String value = data1.getString("success");
                    if (value.equalsIgnoreCase("1")) {
                        JSONArray jArray = reader.getJSONArray("data");
                        for (int i = 0; i < jArray.length(); i++) {
                            News news = new News();
                            Log.e("data ", jArray.toString());
                            JSONObject data = jArray.getJSONObject(i);
                            news.setNewsId(Integer.parseInt(data.getString("news_id")));
                            news.setNewsName(data.getString("news_name"));

                            Log.v("newsName",news.getNewsName());
                            pref.saveNews(news.getNewsName());
                        }
                    }
                    pDialog.hide();

                }catch(JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error ",error.toString());
                pDialog.hide();
            }
        });

        VolleyInitializer.getmInstance(this).addToRequestQueue(sendResqurst);
    }
    /**
     *     go to website button  click
     * @param v
     */


    public void goTonetroxtechWebistNewAct(View v){

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.netroxtech.com"));
        startActivity(browserIntent);
    }
}
