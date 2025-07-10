package com.example.formatparser;

import com.jcraft.jsch.*;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    private static void demoSFTP() {
        try {
            JSch jsch = new JSch();

            System.out.println("Connecting to SFTP test server...");
            Session session = jsch.getSession("demo", "test.rebex.net", 22);
            session.setPassword("password");

            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;

            System.out.println("Files on SFTP server:");

            // List files
            int cnt = 0;
            sftpChannel.ls("/").forEach(entry -> {
                ChannelSftp.LsEntry file = (ChannelSftp.LsEntry) entry;
                System.out.println("- " + file.getFilename());
            });

            // Download a file
            sftpChannel.get("readme.txt", "sftp_readme.txt");
            System.out.println("Downloaded: readme.txt");

            sftpChannel.disconnect();
            session.disconnect();
            System.out.println("SFTP operations completed");
        } catch (Exception e) {
            System.out.println("SFTP Demo: " + e.getMessage());
        }
    }

    private static void demoFTP() {
        try {
            FTPClient ftpClient = new FTPClient();

            System.out.println("Connecting to FTP test server...");
            ftpClient.connect("test.rebex.net", 21);
            ftpClient.login("demo", "password");

            // List files
            System.out.println("Files on the server:");
            String[] files = ftpClient.listNames();
            int cnt = 0;
            for (String file : files) {
                System.out.println(++cnt + "- " + file);
            }

            // Download a file
            File downloadedFile = new File("ftp_readme.txt");
            FileOutputStream fos = new FileOutputStream(downloadedFile);
            ftpClient.retrieveFile("readme.txt", fos);
            fos.close();
            System.out.println("Downloaded: readme.txt");

            ftpClient.logout();
            ftpClient.disconnect();
            System.out.println("FTP operations completed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

        // Part 1: Parse each file type
        parser.parseJSON("sample.json");
        System.out.println();
        parser.parseCSV("sample.csv");
        System.out.println();
        parser.parseYAML("sample.yaml");
        System.out.println();
        parser.parseXML("sample.xml");
        System.out.println();

        // Part 2: FTP DEMO
        demoFTP();
        System.out.println();

        // Part 3: SFTP DEMO
        demoSFTP();
    }
}