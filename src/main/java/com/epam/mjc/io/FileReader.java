package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;


public class FileReader {
    public static void main(String[] args) {
        File filePath  = new File("src/main/resources/Profile.txt");
        Profile newProfile = (new FileReader()).getDataFromFile(filePath);
        System.out.println(newProfile.toString());
    }

    public Profile getDataFromFile(File file) {
        String name;
        Integer age;
        String email;
        Long phone;
        try (BufferedReader bf = new BufferedReader(new java.io.FileReader(file))) {
            ArrayList <String> lines = new ArrayList<>();
            while (bf.ready()) {
                lines.add(bf.readLine());
            }
            Map <String, String> profileData = lines.stream()
                    .map(s -> s.split(": "))
                    .collect(Collectors.toMap(e -> e[0], e -> e[1]));
            name = profileData.get("Name");
            age = Integer.parseInt (profileData.get("Age"));
            email = profileData.get("Email");
            phone = Long.parseLong (profileData.get("Phone"));
            return new Profile(name, age, email, phone);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Profile();
    }
}
