
package mohammadicenter.com.mohammadicdcenter.activities.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mohammadicenter.com.mohammadicdcenter.R;

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

    }

    public void  callFirst(View v){

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+923006133713"));
        startActivity(intent);

    }
    public void  callSecond(View v){

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+923006171415"));
        startActivity(intent);

    }
}
