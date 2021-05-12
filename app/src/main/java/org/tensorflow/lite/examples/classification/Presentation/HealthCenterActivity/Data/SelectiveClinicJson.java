package org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.Data;

import com.google.gson.annotations.Expose;

public class SelectiveClinicJson {

    @Expose
    private String name;

    @Expose
    private String locate;

    @Expose
    private String address;

    @Expose
    private String phone;

    @Expose
    private Double x;

    @Expose
    private Double y;

    public SelectiveClinicJson(final String name, final String locate, final String address, final String phone, final Double x, final Double y){
        this.name = name;
        this.locate = locate;
        this.address = address;
        this.phone = phone;
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }
}
