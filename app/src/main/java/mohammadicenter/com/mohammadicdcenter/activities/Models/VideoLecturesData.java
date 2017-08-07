package mohammadicenter.com.mohammadicdcenter.activities.Models;


/**
 * Created by mac on 5/29/2017.
 * mainid,subid,pname,part,pdetail,pshow,img3
 */


public class VideoLecturesData {
    private  int  mainid;
    private  int subid;
    private  String pname;
    private  String part;
    private  String pdetails;
    private  String pshow;
    private  String img3;


    public int getMainid() {
        return mainid;
    }
    public void setMainid(int mainid) {
        this.mainid = mainid;
    }
    public int getSubid() {
        return subid;
    }
    public void setSubid(int subid) {
        this.subid = subid;
    }
    public String getPname() {
        return pname;
    }
    public void setPname(String pname) {
        this.pname = pname;
    }
    public String getPart() {
        return part;
    }
    public void setPart(String part) {
        this.part = part;
    }
    public String getPdetails() {
        return pdetails;
    }

    public void setPdetails(String pdetails) {
        this.pdetails = pdetails;
    }

    public String getPshow() {
        return pshow;
    }

    public void setPshow(String pshow) {
        this.pshow = pshow;
    }

    public String getImg3() {
        return img3;
    }
    public void setImg3(String img3) {
        this.img3 = img3;
    }
}

