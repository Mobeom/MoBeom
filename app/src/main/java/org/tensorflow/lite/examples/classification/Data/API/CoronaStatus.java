package org.tensorflow.lite.examples.classification.Data.API;

public class CoronaStatus {
    private String deathCnt; // 사망자 수
    private String decideCnt; // 누적 확진 수
    private String clrCnt;

    public CoronaStatus(String deathCnt, String decideCnt, String clrCnt) {
        this.deathCnt = deathCnt;
        this.decideCnt = decideCnt;
        this.clrCnt = clrCnt;
    }

    public String getDeathCnt() {
        return deathCnt;
    }

    public String getDecideCnt() {
        return decideCnt;
    }

    public String getClrCnt() {
        return clrCnt;
    }

    public void setDeathCnt(String deathCnt) {
        this.deathCnt = deathCnt;
    }

    public void setDecideCnt(String decideCnt) {
        this.decideCnt = decideCnt;
    }

    public void setClrCnt(String clrCnt) {
        this.clrCnt = clrCnt;
    }
}

