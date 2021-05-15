package org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.Data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SelectiveClinicJson implements Serializable {

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

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }
}
