package dataProviders;

import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.JsonElement;
import gherkin.deps.com.google.gson.JsonObject;
import gherkin.deps.com.google.gson.JsonParser;
import managers.FileReaderManager;
import testDataTypes.Customer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JsonDataReader {
    private final String customerFilePath = FileReaderManager.getInstance().getConfigReader().getTestDataResourcePath() + "Customer.json";
    private final String environmentFilePath = FileReaderManager.getInstance().getConfigReader().getTestDataResourcePath() + "test_environment.json";
    private List<Customer> customerList;

    public JsonDataReader(){
        customerList = getCustomerData();
    }

    private List<Customer> getCustomerData() {
        Gson gson = new Gson();
        BufferedReader bufferReader = null;
        try {
            bufferReader = new BufferedReader(new FileReader(customerFilePath));
            Customer[] customers = gson.fromJson(bufferReader, Customer[].class);
            return Arrays.asList(customers);
        }catch(FileNotFoundException e) {
            throw new RuntimeException("Json file not found at path : " + customerFilePath);
        }finally {
            try { if(bufferReader != null) bufferReader.close();}
            catch (IOException ignore) {}
        }
    }
    public String getEnvironment_urls() {
        BufferedReader bufferReader = null;
        String baseurl;
        try {
            bufferReader = new BufferedReader(new FileReader(environmentFilePath));
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(bufferReader);
            JsonObject jsonObject = element.getAsJsonObject().getAsJsonObject().getAsJsonObject("environment_urls");
            baseurl = jsonObject.get("base_url").getAsString();
        }catch(FileNotFoundException e) {
            throw new RuntimeException("Json file not found at path : " + environmentFilePath);
        }finally {
            try { if(bufferReader != null) bufferReader.close();}
            catch (IOException ignore) {}
        }
        return baseurl;
    }
    public final Customer getCustomerByName(String customerName){
        return customerList.stream().filter(x -> x.firstName.equalsIgnoreCase(customerName)).findAny().get();
    }
}
