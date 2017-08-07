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
import mohammadicenter.com.mohammadicdcenter.activities.DataAdapter.PersonAdapters;
import mohammadicenter.com.mohammadicdcenter.activities.Models.Persons;
import mohammadicenter.com.mohammadicdcenter.activities.Utils.Constants;


public class DataGridView extends AppCompatActivity {
    JSONObject reader;
    ArrayList<Persons> list;
    GridView listdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_grid_view);
        list = new ArrayList<>();
        TextView marquee = (TextView) findViewById(R.id.latestNews);
        String news = new SharePrefInit(DataGridView.this).readNews();

        marquee.setText(news);
        marquee.setSelected(true);
        listdata = (GridView) findViewById(R.id.mydata);

            readPersonsData();

            listdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    TextView data = (TextView) v.findViewById(R.id.videoLecture_personId);
                    //Toast.makeText(DataGridView.this, "" + data.getText(), Toast.LENGTH_SHORT).show();
                    Intent view = new Intent(DataGridView.this, VideoListDataGrid.class);
                    view.putExtra(Constants.MAIN_ID_KEY, data.getText().toString());
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


                        ListAdapter adapter =new PersonAdapters(DataGridView.this,list);
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
    @Override
    public void onStop()
    {
        super.onStop();

    }
}
