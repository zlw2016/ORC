package com.ngm.ocr.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by zhangliangwei on 2016/8/18.
 */
@Entity
public class Firearms implements Parcelable {

    @Id(autoincrement = true)
    private Long id;
    private String type;
    private String num;
    private String time;
    private String path;

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNum() {
        return this.num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 277965752)
    public Firearms(Long id, String type, String num, String time, String path) {
        this.id = id;
        this.type = type;
        this.num = num;
        this.time = time;
        this.path = path;
    }

    @Generated(hash = 237012325)
    public Firearms() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.type);
        dest.writeString(this.num);
        dest.writeString(this.time);
        dest.writeString(this.path);
    }

    protected Firearms(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.type = in.readString();
        this.num = in.readString();
        this.time = in.readString();
        this.path = in.readString();
    }

    public static final Parcelable.Creator<Firearms> CREATOR = new Parcelable.Creator<Firearms>() {
        @Override
        public Firearms createFromParcel(Parcel source) {
            return new Firearms(source);
        }

        @Override
        public Firearms[] newArray(int size) {
            return new Firearms[size];
        }
    };
}