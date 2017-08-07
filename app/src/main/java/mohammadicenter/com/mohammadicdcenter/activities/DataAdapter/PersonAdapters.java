package mohammadicenter.com.mohammadicdcenter.activities.DataAdapter;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import mohammadicenter.com.mohammadicdcenter.R;
import mohammadicenter.com.mohammadicdcenter.activities.Applications.VolleyInitializer;
import mohammadicenter.com.mohammadicdcenter.activities.Models.Persons;
import mohammadicenter.com.mohammadicdcenter.activities.Utils.Constants;

/**
 * Created by mac on 5/24/2017.
 */

public class PersonAdapters extends BaseAdapter {
    private ImageLoader mImageLoader;
    private Context context;
    private LayoutInflater mlayoutInfulator;
    List<Persons> personsList;
    public PersonAdapters(Context context, List<Persons> personsList){
        this.context =  context;
        this.personsList = personsList;
        mlayoutInfulator =  LayoutInflater.from(context);
        mImageLoader = VolleyInitializer.getmInstance(context).getImageLoader();

    }

    @Override
    public int getCount() {
        return this.personsList.size();
    }

    @Override
    public Object getItem(int i) {
        return  personsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        if(view==null){
            view = mlayoutInfulator.inflate(R.layout.videolectureslist,null);
            viewHolder.personImage = (NetworkImageView)view.findViewById(R.id.personImage);
            viewHolder.personName  = (TextView)view.findViewById(R.id.videoLecture_personName);
            viewHolder.personId    =   (TextView)view.findViewById(R.id.videoLecture_personId);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Persons pList = personsList.get(i);
        Log.e("users ", Html.fromHtml(pList.getCategory()).toString());
        viewHolder.personImage.setImageUrl(Constants.PERSON_IMAGE_URL+pList.getImg1(),mImageLoader);
        viewHolder.personName.setText(Html.fromHtml(pList.getCategory().toString()));
        viewHolder.personId.setText(String.valueOf(pList.getMainid()));
        return view;
    }
    }

      class ViewHolder
      {
          NetworkImageView personImage;
          TextView personName,personId;

      }