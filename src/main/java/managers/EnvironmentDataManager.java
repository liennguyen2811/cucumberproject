package managers;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class EnvironmentDataManager{
    public HashMap<String, String> environmentUrls;
    public static HashMap<String, String> billtoboxUrls;
    public HashMap<String, HashMap<String, String>> existingUsers;
    public HashMap<String, HashMap<String, String>> existingHub;
    public HashMap<String, HashMap<String, String>> existingDevice;
    public HashMap<String, HashMap<String, String>> androidElements;
    public HashMap<String, HashMap<String, String>> iOSElements;
    public EnvironmentDataManager(String environment) {
        File fileEnv = new File(FileReaderManager.getInstance().getConfigReader().getTestDataResourcePath() + environment + "_environment.json");
        File fileDevice = new File(FileReaderManager.getInstance().getConfigReader().getTestDataResourcePath() + "device_capabilities.json");
        File fileElements = new File(FileReaderManager.getInstance().getConfigReader().getTestDataResourcePath() + "elements.json");
        HashMap<String,Object> env = null;
        try {
            env = new ObjectMapper().readValue(fileEnv, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String,Object> device = null;
        try {
            device = new ObjectMapper().readValue(fileDevice, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String,Object> elements = null;
        try {
            elements = new ObjectMapper().readValue(fileElements, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        environmentUrls = (HashMap<String, String>) env.get("environment_urls");
        existingUsers = (HashMap<String, HashMap<String, String>>) env.get("existing_users");
        existingHub = (HashMap<String, HashMap<String, String>>) env.get("environment_urls");
        existingDevice =  (HashMap<String, HashMap<String, String>>) device.get("device");
        androidElements =  (HashMap<String, HashMap<String, String>>) elements.get("Android");
        iOSElements =  (HashMap<String, HashMap<String, String>>) elements.get("iOS");
        billtoboxUrls = (HashMap<String, String>) env.get("billtobox_urls");
    }
}
