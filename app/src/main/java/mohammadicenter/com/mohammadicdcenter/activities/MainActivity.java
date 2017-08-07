package mohammadicenter.com.mohammadicdcenter.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import mohammadicenter.com.mohammadicdcenter.R;
import mohammadicenter.com.mohammadicdcenter.activities.Activities.DataGridView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setXML();
    }
    public void setXML(){

        RelativeLayout  videoLayout =  (RelativeLayout)findViewById(R.id.VideoLecture_Layout);
        videoLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id =  view.getId();
        if(R.id.VideoLecture_Layout== id ){

            Intent intent = new Intent(MainActivity.this,DataGridView.class);
            startActivity(intent);

        }
    }
    public void goToMyWebitse(View  v){
        Intent  intent = new Intent(MainActivity.this,VisitWebsite.class);
        startActivity(intent);

    }
}
