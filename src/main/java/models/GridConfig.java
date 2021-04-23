package models;

import java.net.MalformedURLException;
import java.net.URL;

public class GridConfig {
    public URL hub;
    public String host, port;

    public GridConfig() {

        String gridIp = System.getProperty("gridIp");
        String gridPort = System.getProperty("gridPort");

       // this.host = (gridIp != null) ? gridIp : "169.254.48.125";
        this.host = (gridIp != null) ? gridIp : "10.88.170.84";
        this.port = (gridPort != null) ? gridPort : "28702";
        String hub = String.format("http://%s:%s/wd/hub", host, port);

        try {
            this.hub = new URL(hub);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
