package org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.Data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SelectiveClinicJson implements Serializable {      //@copyright for 이동우

    @SerializedName("name")
    public String name;

    @SerializedName("locate")
    public String locate;

    @SerializedName("address")
    public String address;

    @SerializedName("phone")
    public String phone;

    @SerializedName("x")
    public Double x;

    @SerializedName("y")
    public Double y;

    public String getName() {return name;}

    public String getAddress() {return address;}

    public String getPhone() {return phone;}

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }
}
