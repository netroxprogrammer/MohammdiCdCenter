package mohammadicenter.com.mohammadicdcenter.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mohammadicenter.com.mohammadicdcenter.R;
import mohammadicenter.com.mohammadicdcenter.activities.Applications.VolleyInitializer;
import mohammadicenter.com.mohammadicdcenter.activities.DataAdapter.PersonAdapters;
import mohammadicenter.com.mohammadicdcenter.activities.Models.Persons;
import mohammadicenter.com.mohammadicdcenter.activities.Utils.Constants;

public class VideosLectures extends AppCompatActivity {
    JSONObject reader;
    ArrayList<Persons>  list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_lectures);
        TextView marquee = (TextView)findViewById(R.id.latestNews);
        marquee.setSelected(true);
        list = new ArrayList<>();
           readPersonsData();

       // if(list.size()>0){


    }
    public void readPersonsData(){

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("please  Wait");
        pDialog.show();

        StringRequest sendResqurst = new StringRequest(Request.Method.GET, Constants.VIDEO_LECTURE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    reader = new JSONObject(response);
                    JSONObject data1 = reader.getJSONObject("result");
                    String value = data1.getString("success");
                    if (value.equalsIgnoreCase("1")) {
                        JSONArray jArray = reader.getJSONArray("data");
                        for (int i = 0; i < jArray.length(); i++) {
                            Persons users = new Persons();
                            Log.e("data ", jArray.toString());
                            JSONObject data = jArray.getJSONObject(i);

                            users.setMainid(Integer.parseInt(data.getString("mainid")));

                            users.setCategory(data.getString("category"));

                            users.setImg1(data.getString("img1"));
                        ///    Log.e("users ", users.getImg1());
                            users.setMain_show(data.getString("main_show"));
                            users.setRanking(Integer.parseInt(data.getString("ranking")));
                            list.add(users);
                        }

                        ListView listdata = (ListView)findViewById(R.id.personList);
                        ListAdapter adapter =new  PersonAdapters(VideosLectures.this,list);
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

