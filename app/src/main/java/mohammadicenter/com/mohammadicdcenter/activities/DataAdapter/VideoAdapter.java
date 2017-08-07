package mohammadicenter.com.mohammadicdcenter.activities.DataAdapter;

import android.content.Context;
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
import mohammadicenter.com.mohammadicdcenter.activities.Models.VideoLecturesData;
import mohammadicenter.com.mohammadicdcenter.activities.Utils.Constants;

/**
 * Created by mac on 5/29/2017.
 */

public class VideoAdapter extends BaseAdapter {
    private ImageLoader mImageLoader;
    private Context context;
    private LayoutInflater mlayoutInfulator;
    List<VideoLecturesData> videoLecturesData;

    public VideoAdapter(Context context, List<VideoLecturesData> videoLecturesData) {
        this.context = context;
        this.videoLecturesData = videoLecturesData;
        mlayoutInfulator = LayoutInflater.from(context);
        mImageLoader = VolleyInitializer.getmInstance(context).getImageLoader();
    }
    @Override
    public int getCount() {
        return this.videoLecturesData.size();
    }
    @Override
    public Object getItem(int i) {
        return videoLecturesData.get(i);
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        if (view == null) {
            view = mlayoutInfulator.inflate(R.layout.videolistadapter, null);
            viewHolder.personImage = (NetworkImageView) view.findViewById(R.id.personImage_adapterList);
           viewHolder.videoLink = (TextView) view.findViewById(R.id.videoLink_videolistadapter);
            viewHolder.personId = (TextView) view.findViewById(R.id.videoId_videolistadapter);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        VideoLecturesData pList = videoLecturesData.get(i);
        // Log.e("users ", Html.fromHtml(pList.getCategory()).toString());
        String  url  = Constants.VIDEO_IMAGE_URL + pList.getImg3().trim();

        viewHolder.personImage.setImageUrl(url.replaceAll("\\s","%20"), mImageLoader);
        // viewHolder.personName.setText(Html.fromHtml(pList.getCategory().toString()));
        Log.v("CheckURL",url.replaceAll("\\s","%") );
        viewHolder.personId.setText(String.valueOf(pList.getMainid()));
        viewHolder.videoLink.setText(String.valueOf(pList.getPdetails()));
        return view;
    }

class ViewHolder
{
    NetworkImageView personImage;
    TextView personId,videoLink;
}
}

