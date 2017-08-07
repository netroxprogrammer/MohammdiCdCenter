package mohammadicenter.com.mohammadicdcenter.activities.Applications;

import android.content.Context;
import android.content.SharedPreferences;

import mohammadicenter.com.mohammadicdcenter.activities.Utils.Constants;

/**
 * Created by mac on 6/18/2017.
 */

public class SharePrefInit {
    SharedPreferences mpref;
    Context mcontent;
    public SharePrefInit(Context context){
        this.mcontent  =context;
        mpref = mcontent.getSharedPreferences(Constants.SHARE_PREF_NAME,Context.MODE_PRIVATE);
    }

    /**
     * Save News in Database
     * @param news
     */

    public void saveNews(String news){
        SharedPreferences.Editor edit  =  mpref.edit();
        edit.putString(Constants.SHARE_PREF_NEW_KEY,news);
        edit.commit();
    }
    public String readNews(){

        String values = mpref.getString(Constants.SHARE_PREF_NEW_KEY,null);
        return values;
    }

}
