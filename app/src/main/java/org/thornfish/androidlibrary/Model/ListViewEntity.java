package org.thornfish.androidlibrary.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */

public class ListViewEntity {



    private String respcode;
    private String respmsg;
    private List<RespdataBean> respdata;

    public String getRespcode() {
        return respcode;
    }

    public void setRespcode(String respcode) {
        this.respcode = respcode;
    }

    public String getRespmsg() {
        return respmsg;
    }

    public void setRespmsg(String respmsg) {
        this.respmsg = respmsg;
    }

    public List<RespdataBean> getRespdata() {
        return respdata;
    }

    public void setRespdata(List<RespdataBean> respdata) {
        this.respdata = respdata;
    }

    public static class RespdataBean implements Parcelable {
        /**
         * id : 26
         * gid : 7
         * title : 商品7
         * imgurl :
         * jifen : 70
         * money : 75
         * time : 2017-06-04 15:11
         * status : 0
         * validtime : 2017-09-04 15:11
         */

        private String id;
        private String gid;
        private String title;
        private String imgurl;
        private String jifen;
        private String money;
        private String time;
        private int status;
        private String validtime;
        private Boolean check;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getJifen() {
            return jifen;
        }

        public void setJifen(String jifen) {
            this.jifen = jifen;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getValidtime() {
            return validtime;
        }

        public void setValidtime(String validtime) {
            this.validtime = validtime;
        }

        public Boolean getCheck() {
            return check;
        }

        public void setCheck(Boolean check) {
            this.check = check;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.gid);
            dest.writeString(this.title);
            dest.writeString(this.imgurl);
            dest.writeString(this.jifen);
            dest.writeString(this.money);
            dest.writeString(this.time);
            dest.writeInt(this.status);
            dest.writeString(this.validtime);
            dest.writeValue(this.check);
        }

        public RespdataBean() {
        }

        protected RespdataBean(Parcel in) {
            this.id = in.readString();
            this.gid = in.readString();
            this.title = in.readString();
            this.imgurl = in.readString();
            this.jifen = in.readString();
            this.money = in.readString();
            this.time = in.readString();
            this.status = in.readInt();
            this.validtime = in.readString();
            this.check = (Boolean) in.readValue(Boolean.class.getClassLoader());
        }

        public static final Creator<RespdataBean> CREATOR = new Creator<RespdataBean>() {
            @Override
            public RespdataBean createFromParcel(Parcel source) {
                return new RespdataBean(source);
            }

            @Override
            public RespdataBean[] newArray(int size) {
                return new RespdataBean[size];
            }
        };
    }
}
