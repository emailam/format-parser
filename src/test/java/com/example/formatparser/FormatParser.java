package com.example.formatparser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FormatParser {
    private final ObjectMapper jsonMapper;
    private final ObjectMapper yamlMapper;
    private final XmlMapper xmlMapper;
    private final CsvMapper csvMapper;

    public FormatParser() {
        this.jsonMapper = new ObjectMapper();
        this.yamlMapper = new ObjectMapper(new YAMLFactory());
        this.xmlMapper = new XmlMapper();
        this.csvMapper = new CsvMapper();
    }

    private String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public void parseJSON(String filePath) {
        try {
            String content = readFile(filePath);
            Object result = jsonMapper.readValue(content, Object.class);
            System.out.println("Successfully parsed JSON");
            System.out.println("Content: " + jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
        } catch (Exception e) {
            System.err.println("Error parsing JSON file: " + e.getMessage());
        }
    }

    public void parseYAML(String filePath) {
        try {
            String content = readFile(filePath);
            Object result = yamlMapper.readValue(content, Object.class);
            System.out.println("Successfully parsed YAML");
            System.out.println("Content" + result);
        } catch (Exception e) {
            System.err.println("Error parsing YAML file: " + e.getMessage());
        }
    }

    public void parseXML(String filePath) {
        try {
            String content = readFile(filePath);
            Object result = xmlMapper.readValue(content, Object.class);
            System.out.println("Successfully parsed XML");
            System.out.println("Content" + result);
        } catch (Exception e) {
            System.err.println("Error parsing XML file: " + e.getMessage());
        }
    }

    public void parseCSV(String filePath) {
        try {
            CsvSchema schema = CsvSchema.emptySchema().withHeader();
            List<Map<String, String>> result = new ArrayList<>();
            String content = readFile(filePath);
            csvMapper.readerFor(Map.class)
                    .with(schema)
                    .readValues(content)
                    .forEachRemaining(entry -> result.add((Map<String, String>) entry));

            System.out.println("Successfully parsed CSV");
            System.out.println("Content: " + result);
        } catch (Exception e) {
            System.err.println("Error parsing CSV: " + e.getMessage());
        }
    }


}