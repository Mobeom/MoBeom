package org.tensorflow.lite.examples.classification.Data.API;

public class CoronaStatus {
    private String date; // 어떤 날짜를 기준으로 가져왔는지.
    private String deathCnt; // 사망자 수
    private String decideCnt; // 누적 확진 수

    public CoronaStatus(String date, String deathCnt, String decideCnt) {
        this.date = date;
        this.deathCnt = deathCnt;
        this.decideCnt = decideCnt;
    }

    public String getDate() {
        return date;
    }

    public String getDeathCnt() {
        return deathCnt;
    }

    public String getDecideCnt() {
        return decideCnt;
    }
}

