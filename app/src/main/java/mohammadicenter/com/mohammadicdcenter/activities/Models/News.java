package mohammadicenter.com.mohammadicdcenter.activities.Models;

/**
 * Created by mac on 6/18/2017.
 */

public class News {
    private int newsId;
    private String newsName=null;

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }
}
