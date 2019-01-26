package ua.demo.test.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.demo.test.domain.Data;
import ua.demo.test.domain.JsonData;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Service
public class DataHttpService {

    @Autowired
    DataService dataService;

    private static final String API1 = "https://lb-api-sandbox.prozorro.gov.ua/api/2.4/contracts/23567e24f52746ef92c470be6059d193/documents";
    private static final String API2 = "https://lb-api-sandbox.prozorro.gov.ua/api/2.4/contracts/4805f381d48948b1b34d6ea2daa029a3/documents";
    private static final String API3 = "https://lb-api-sandbox.prozorro.gov.ua/api/2.4/contracts/47fa8764e1b74f4db58f84c9db460566/documents";

    public void run() {




        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<String> apiList = Arrays.asList(API1, API2, API3);
        try {
            for (String anApiList : apiList) {
                JsonData jsonData = mapper
                        .readValue(new URL(anApiList), JsonData.class);
                List<Data> dataArrayList = Arrays.asList(jsonData.getData());
                dataArrayList.forEach(dataService::save);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("broken link or not to parse");
        }
    }

}
