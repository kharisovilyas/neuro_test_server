package ru.ilcorp.neuro_test.service.connect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ilcorp.neuro_test.model.dto.ai.dtoRequestAI;
import ru.ilcorp.neuro_test.model.dto.ai.dtoResponseAI;

import java.util.List;

@Service
public class ConnectToAIService {

    private final RestTemplate restTemplate;
    @Value("${ai.url.api}")
    private String URL_AI;

    @Autowired
    public ConnectToAIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public dtoResponseAI modelLaunch(List<dtoRequestAI> requestAI) {
        return restTemplate.postForObject(URL_AI, requestAI, dtoResponseAI.class);
    }
}