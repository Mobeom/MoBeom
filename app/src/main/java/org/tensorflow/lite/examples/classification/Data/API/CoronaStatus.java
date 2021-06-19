package org.tensorflow.lite.examples.classification.Data.API;

public class CoronaStatus {              // @Copyright for 이원중
    private String deathCnt; // 사망자 수
    private String decideCnt; // 누적 확진 수
    private String clrCnt;

    public CoronaStatus(String deathCnt, String decideCnt, String clrCnt) {              // @Copyright for 이원중
        this.deathCnt = deathCnt;
        this.decideCnt = decideCnt;
        this.clrCnt = clrCnt;
    }

    public String getDeathCnt() {
        return deathCnt;
    }              // @Copyright for 이원중

    public String getDecideCnt() {
        return decideCnt;
    }              // @Copyright for 이원중

    public String getClrCnt() {
        return clrCnt;
    }              // @Copyright for 이원중

    public void setDeathCnt(String deathCnt) {
        this.deathCnt = deathCnt;
    }              // @Copyright for 이원중

    public void setDecideCnt(String decideCnt) {
        this.decideCnt = decideCnt;
    }              // @Copyright for 이원중

    public void setClrCnt(String clrCnt) {
        this.clrCnt = clrCnt;
    }              // @Copyright for 이원중
}

