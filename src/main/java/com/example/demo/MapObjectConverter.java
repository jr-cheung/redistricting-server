package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.util.HashMap;
import java.util.Map;

@Converter
public class MapObjectConverter implements AttributeConverter<Map<String,Object>, String>{
    @Override
    public String convertToDatabaseColumn(Map<String,Object> jsonData) {
        String json;
        try{
            json = jsonData.toString();
        }
        catch (NullPointerException ex)
        {
            json = "";
        }
        return json;
    }

    @Override
    public Map<String,Object> convertToEntityAttribute(String jsonDataAsJson) {
        Map<String,Object> result = null;
        try {
            result = new ObjectMapper().readValue(jsonDataAsJson, HashMap.class);
        } catch (JsonProcessingException ex) {
            result = null;
        }
        return result;

    }
}

