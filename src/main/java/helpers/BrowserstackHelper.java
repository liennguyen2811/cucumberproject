package helpers;

import models.BrowserstackConfig;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;

public final class BrowserstackHelper {

    public static String getBsUrl(String bsSessionId) {

        String bsUrl = "";
        BrowserstackConfig bc = new BrowserstackConfig();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String uri = String.format("%s/sessions/%s.json", bc.restUri, bsSessionId);
        HttpGet httpGet = new HttpGet(uri);

        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String body = EntityUtils.toString(entity);
            JSONObject result = (JSONObject) JSONValue.parse(body);
            JSONObject automated_session = (JSONObject) result.get("automation_session");
            bsUrl = (String) automated_session.get("browser_url");
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bsUrl;
    }

    public static void failBsStatus(String bsSessionId) {
        BrowserstackConfig bc = new BrowserstackConfig();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String uri = String.format("%s/sessions/%s.json", bc.restUri, bsSessionId);
        HttpPut httpPut = new HttpPut(uri);
        httpPut.addHeader("Content-Type", "application/json");
        HttpEntity body = new StringEntity("{\"status\":\"error\"}", "UTF-8");
        httpPut.setEntity(body);
        try {
            httpClient.execute(httpPut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
