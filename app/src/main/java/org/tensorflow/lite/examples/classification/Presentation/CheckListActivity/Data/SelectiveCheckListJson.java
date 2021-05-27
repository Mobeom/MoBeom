package org.tensorflow.lite.examples.classification.Presentation.CheckListActivity.Data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SelectiveCheckListJson implements Serializable{
    @SerializedName("isInfected")
    public boolean isInfected;

    @SerializedName("isVaccined")
    public boolean isVaccined;

    @SerializedName("firstInjection")
    public String firstInjection;

    @SerializedName("secondInjection")
    public String secondInjection;
}
