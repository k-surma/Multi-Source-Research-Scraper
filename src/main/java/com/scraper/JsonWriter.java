//package com.scraper;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
//public class JsonWriter {
//    public static void writeArticlesToJson(List<Article> articles, String filePath) {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//
//        try {
//            mapper.writeValue(new File(filePath), articles);
//            System.out.println("Data written to " + filePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
