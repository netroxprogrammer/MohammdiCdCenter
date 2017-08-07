package mohammadicenter.com.mohammadicdcenter.activities.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
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

import mohammadicenter.com.mohammadicdcenter.R;
import mohammadicenter.com.mohammadicdcenter.activities.Applications.SharePrefInit;
import mohammadicenter.com.mohammadicdcenter.activities.Applications.VolleyInitializer;
import mohammadicenter.com.mohammadicdcenter.activities.DataAdapter.VideoAdapter;
import mohammadicenter.com.mohammadicdcenter.activities.Models.VideoLecturesData;
import mohammadicenter.com.mohammadicdcenter.activities.Utils.Constants;

public class VideoListDataGrid extends AppCompatActivity {
    JSONObject reader;
    ArrayList<VideoLecturesData> list;
    GridView listdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list_data_grid);
        list = new ArrayList<>();
        TextView marquee = (TextView) findViewById(R.id.latestNews);
        String news = new SharePrefInit(VideoListDataGrid.this).readNews();
        marquee.setText(news);
        marquee.setSelected(true);
        listdata = (GridView) findViewById(R.id.videoDataList);
        Bundle extra = getIntent().getExtras();

            if (extra != null) {
                readPersonsData(extra.getString(Constants.MAIN_ID_KEY));
            }
            listdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    TextView data = (TextView) v.findViewById(R.id.videoLink_videolistadapter);
                    //Toast.makeText(DataGridView.this, "" + data.getText(), Toast.LENGTH_SHORT).show();
                    Intent view = new Intent(VideoListDataGrid.this, WatchVideo.class);
                    view.putExtra(Constants.URL_KEY, data.getText().toString());
                    startActivity(view);
                }
            });
        }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public void readPersonsData(String  id){

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("please  Wait");
        pDialog.show();

        StringRequest sendResqurst = new StringRequest(Request.Method.GET, Constants.VIDEO_LECTURE_LINK+"?mainid="+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    reader = new JSONObject(response);
                    JSONObject data1 = reader.getJSONObject("result");
                    String value = data1.getString("success");
                    if (value.equalsIgnoreCase("1")) {
                        JSONArray jArray = reader.getJSONArray("data");
                        for (int i = 0; i < jArray.length(); i++) {
                            VideoLecturesData users = new VideoLecturesData();
                            Log.e("data ", jArray.toString());
                            JSONObject data = jArray.getJSONObject(i);

                            users.setMainid(Integer.parseInt(data.getString("mainid")));

                            users.setSubid(Integer.parseInt(data.getString("subid")));
                            users.setPname(data.getString("pname"));
                            users.setPart(data.getString("part"));
                            users.setPdetails(data.getString("pdetail"));
                            users.setPshow(data.getString("pshow"));
                            users.setImg3(data.getString("img3"));
                            list.add(users);
                        }


                        ListAdapter adapter =new VideoAdapter(VideoListDataGrid.this,list);
                        listdata.setAdapter(adapter);
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
}


