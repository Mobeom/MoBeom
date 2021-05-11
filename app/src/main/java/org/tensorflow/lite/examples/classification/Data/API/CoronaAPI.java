package org.tensorflow.lite.examples.classification.Data.API;

import android.content.Context;
import android.util.Log;

import org.tensorflow.lite.examples.classification.R;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CoronaAPI {
    public static CoronaStatus getStatus(String startDate, String endDate, Context context) throws IOException, XmlPullParserException {
        // 인코딩된 Service Key <- 이거 사용
        String serviceKeyEncoded = context.getString(R.string.corona_service_key_encoded);
        // 디코딩된 Service Key
        String serviceKeyDecoded = context.getString(R.string.corona_service_key_decoded);;
        String requestUrl = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?serviceKey=" + serviceKeyEncoded + "&pageNo=1&numOfRows=10&startCreateDt=" + startDate + "&endCreateDt=" + endDate;
        Log.e("URL", requestUrl);
        URL url = new URL(requestUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());

        return xmlParsing(url);
    }

    public static CoronaStatus xmlParsing(URL url) throws IOException, XmlPullParserException {

        CoronaStatus stat = new CoronaStatus("-1", "-1", "-1");

        InputStream is = url.openStream();
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser xpp = factory.newPullParser();
        xpp.setInput(new InputStreamReader(is, "UTF-8"));
        String tag;
        xpp.next();
        StringBuffer buffer = new StringBuffer();
        int eventType = xpp.getEventType();

        boolean deathTodaychk = false; // 첫째날 데이터 값만 갖고 오기 위해 boolean 값 추가
        boolean decTodayChk = false; // 오늘 값을 확인하는 것인지 체크하기 위함, false면 오늘 값 확인, true면 어제 값 확인
        boolean clearTodayChk = false; // 오늘 값을 확인하는 것인지 체크하기 위함, false면 오늘 값 확인, true면 어제 값 확인
        int todayDec = 0, yesterdayDec = 0;
        int todayClear = 0, yesterdayClear = 0;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    buffer.append("파싱 시작 단계\n\n");
                    break;

                case XmlPullParser.START_TAG:
                    tag = xpp.getName();
                    if (tag.equals("item")) ;
                    else if (tag.equals("deathCnt")) { // 일치하는 아이템명 확인
                        buffer.append("사망자 수(deathCnt) : ");
                        xpp.next();
                        buffer.append(xpp.getText()); // 아이템의 값을 가져오는 부분
                        if (!deathTodaychk) { // 오늘 사망자 수 추가를 위해 boolean 값으로 날짜 파악.
                            stat.setDeathCnt(xpp.getText());
                        }
                        buffer.append("\n");
                        deathTodaychk = true;
                    } else if (tag.equals("decideCnt")) { // 일치하는 아이템명 확인
                        buffer.append("누적 확진자 수(decideCnt) : ");
                        xpp.next();
                        buffer.append(xpp.getText()); // 아이템의 값을 가져오는 부분
                        if (!decTodayChk) { // 오늘 값, API가 오늘 값을 먼저 주기 때문에 이렇게 설정하면 됨.
                            todayDec = Integer.parseInt(xpp.getText());
                        } else { // 어제 값
                            yesterdayDec = Integer.parseInt(xpp.getText());
                        }
                        buffer.append("\n");
                        decTodayChk = true;
                    } else if (tag.equals("clearCnt")) { // 일치하는 아이템명 확인
                        buffer.append("누적 확진자 수(decideCnt) : ");
                        xpp.next();
                        buffer.append(xpp.getText()); // 아이템의 값을 가져오는 부분
                        if (!clearTodayChk) { // 오늘 값, API가 오늘 값을 먼저 주기 때문에 이렇게 설정하면 됨.
                            todayClear = Integer.parseInt(xpp.getText());
                        } else { // 어제 값
                            yesterdayClear = Integer.parseInt(xpp.getText());
                        }
                        buffer.append("\n");
                        clearTodayChk = true;
                    }
                    break;

                case XmlPullParser.TEXT:
                    break;

                case XmlPullParser.END_TAG:
                    tag = xpp.getName(); // 태그 이름 얻어오기
                    if (tag.equals("item")) buffer.append("\n"); // 첫번째 검색결과종료 후 줄바꿈
                    break;
            }
            eventType = xpp.next();
        }

        int dec = todayDec - yesterdayDec; // 오늘 확진자에서 어제 확진자 수 차감
        int clear = todayClear - yesterdayClear;

        stat.setDecideCnt(Integer.toString(dec)); // 확진자 현황 추가
        stat.setClrCnt(Integer.toString(clear));

        buffer.append("파싱 종료 단계 \n");
        System.out.println(buffer.toString());
        return stat;
    }
}
