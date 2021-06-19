package org.tensorflow.lite.examples.classification.Presentation.CheckListActivity.Data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserInfoJson implements Serializable{      //@copyright for 김규빈
    @SerializedName("isInfected")
    public boolean isInfected;

    @SerializedName("isVaccined")
    public boolean isVaccined;

    public enum vaccine{
        @SerializedName("m")
        m,
        @SerializedName("w")
        w,
        @SerializedName("y")
        y,
        @SerializedName("a")
        a
    };

    @SerializedName("firstInjection")
    public String firstInjection;

    @SerializedName("secondInjection")
    public String secondInjection;

}
