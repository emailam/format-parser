package com.example.formatparser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    private static void createSampleFiles() {
        try {
            // Create JSON file
            String json = "{\n  \"name\": \"Khaled\",\n  \"age\": 30,\n  \"city\": \"Cairo\"\n}";
            Files.write(Paths.get("sample.json"), json.getBytes());

            // Create CSV file
            String csv = "name,age,city\nAli,25,Cairo\nOmar,35,Sohag\nWaleed,28,Giza";
            Files.write(Paths.get("sample.csv"), csv.getBytes());

            // Create YAML file
            String yaml = "person:\n  name: Sarah\n  age: 27\n  skills:\n    - Java\n    - Python\n    - SQL";
            Files.write(Paths.get("sample.yaml"), yaml.getBytes());

            // Create XML file
            String xml = "<?xml version=\"1.0\"?>\n<employee>\n  <name>Ahmed Mohamed</name>\n  <department>IT</department>\n  <salary>75000</salary>\n</employee>";
            Files.write(Paths.get("sample.xml"), xml.getBytes());

            System.out.println("Sample files created successfully!\n");

        } catch (IOException e) {
            System.err.println("Error creating sample files: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        FormatParser parser = new FormatParser();

        createSampleFiles();

        // Parse each file type
        parser.parseJSON("sample.json");
        System.out.println();
        parser.parseCSV("sample.csv");
        System.out.println();
        parser.parseYAML("sample.yaml");
        System.out.println();
        parser.parseXML("sample.xml");
    }
}