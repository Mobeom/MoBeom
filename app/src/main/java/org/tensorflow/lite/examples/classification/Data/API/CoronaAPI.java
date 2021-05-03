package org.tensorflow.lite.examples.classification.Data.API;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class CoronaAPI {
    public static void getStatus() throws IOException {
//        String urlBuilder = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson" + "?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=서비스키" + /*Service Key*/
//                "&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode("-", "UTF-8") + /*공공데이터포털에서 받은 인증키*/
//                "&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8") + /*페이지번호*/
//                "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8") + /*한 페이지 결과 수*/
//                "&" + URLEncoder.encode("startCreateDt", "UTF-8") + "=" + URLEncoder.encode("20200310", "UTF-8") + /*검색할 생성일 범위의 시작*/
//                "&" + URLEncoder.encode("endCreateDt", "UTF-8") + "=" + URLEncoder.encode("20200315", "UTF-8");/*URL*//*검색할 생성일 범위의 종료*/
        String serviceKeyEncoding = "tRFjOqedzciXi8AT49yZ0jh0cj6YO4Yg4Ej4wLcM%2BKa2joeVLhqTnfZRxuY0%2FYqUX4%2F5%2FIOUrjFPvZIyWjImeQ%3D%3D";
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
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }
}
