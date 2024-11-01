package ru.ilcorp.neuro_test.service.connect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.ilcorp.neuro_test.model.dto.ai.dtoRequestAI;
import ru.ilcorp.neuro_test.model.dto.ai.dtoResponseAI;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ConnectToAIService {

    private final RestTemplate restTemplate;

    @Value("${ai.url.api}")
    private String URL_AI;

    @Value("${ai.repos}")
    private String directory;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public ConnectToAIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Transactional
    public dtoResponseAI modellingStartFromFile(List<dtoRequestAI> requestAI) {
        try {
            // Динамическое создание путей
            String inputFilePath = directory + "/input.json";
            String outputFilePath = directory + "/output.json";
            String scriptPath = directory + "/done_version1_ex5.py";

            // 1. Записываем requestAI в input.json
            writeInputFile(requestAI, inputFilePath);

            // 2. Запускаем Python-скрипт
            runPythonScript(scriptPath);

            // 3. Читаем выходные данные из output.json

            // 4. Возвращаем результат
            return readOutputFile(outputFilePath);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Transactional
    // Метод для записи данных в input.json
    private void writeInputFile(List<dtoRequestAI> requestAI, String inputFilePath) throws IOException {
        objectMapper.writeValue(new File(inputFilePath), requestAI);
    }
    @Transactional
    // Метод для запуска Python-скрипта
    private void runPythonScript(String scriptPath) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("python3", scriptPath);
        processBuilder.start().waitFor();
    }
    @Transactional
    // Метод для чтения данных из output.json и преобразования их в объект dtoResponseAI
    private dtoResponseAI readOutputFile(String outputFilePath) throws IOException {
        return objectMapper.readValue(new File(outputFilePath), dtoResponseAI.class);
    }
    @Transactional
    public dtoResponseAI modelLaunch(List<dtoRequestAI> requestAI) {
        return restTemplate.postForObject(URL_AI, requestAI, dtoResponseAI.class);
    }
}
