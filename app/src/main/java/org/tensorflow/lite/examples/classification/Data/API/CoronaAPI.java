package org.tensorflow.lite.examples.classification.Data.API;

import android.util.Log;

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
    public static void getStatus() throws IOException, XmlPullParserException {
        // 인코딩된 Service Key <- 이거 사용
        String serviceKeyEncoding = "tRFjOqedzciXi8AT49yZ0jh0cj6YO4Yg4Ej4wLcM%2BKa2joeVLhqTnfZRxuY0%2FYqUX4%2F5%2FIOUrjFPvZIyWjImeQ%3D%3D";
        // 디코딩된 Service Key
        String serviceKeyDecoding = "tRFjOqedzciXi8AT49yZ0jh0cj6YO4Yg4Ej4wLcM+Ka2joeVLhqTnfZRxuY0/YqUX4/5/IOUrjFPvZIyWjImeQ==";
        String requestUrl = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?serviceKey=" + serviceKeyEncoding + "&pageNo=1&numOfRows=10&startCreateDt=" + 20210404 + "&endCreateDt=" + 20210405;
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
            System.out.println(line);
            sb.append(line);
        }

        InputStream is = url.openStream();
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser xpp = factory.newPullParser();
        xpp.setInput(new InputStreamReader(is, "UTF-8"));
        String tag;
        xpp.next();
        StringBuffer buffer = new StringBuffer();
        int eventType = xpp.getEventType();
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
                        buffer.append("\n");
                    }
                    else if (tag.equals("decideCnt")) { // 일치하는 아이템명 확인
                        buffer.append("누적 확진자 수(decideCnt) : ");
                        xpp.next();
                        buffer.append(xpp.getText()); // 아이템의 값을 가져오는 부분
                        buffer.append("\n");
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
        buffer.append("파싱 종료 단계 \n");
        System.out.println(buffer.toString());

        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }

    public static String xmlParsing(URL url) throws IOException, XmlPullParserException {
        InputStream is = url.openStream();
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser xpp = factory.newPullParser();
        xpp.setInput(new InputStreamReader(is, "UTF-8"));
        String tag;
        xpp.next();
        StringBuffer buffer = new StringBuffer();
        int eventType = xpp.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    buffer.append("파싱 시작 단계\n\n");
                    break;

                case XmlPullParser.START_TAG:
                    tag = xpp.getName();
                    if (tag.equals("item")) ;
                    else if (tag.equals("careCnt")) {
                        buffer.append("치료수(careCnt) : ");
                        xpp.next();
                        buffer.append(xpp.getText());
                        buffer.append("\n");
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
        buffer.append("파싱 종료 단계 \n");
        System.out.println(buffer.toString());
        return buffer.toString();
    }
}
