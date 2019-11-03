package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class Helper {

    public static String readAll(File file) {
        try {
            var reader = new BufferedReader(new java.io.FileReader(file));
            var builder = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            return builder.toString();
        } catch (IOException e) {
            return "";
        }
    }
}
