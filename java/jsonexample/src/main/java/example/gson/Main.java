package example.gson;

import isft151.example.Category;
import isft151.example.JsonProvider;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {

    public static void main(String[] args) {
    
        String sourceJson = JsonProvider.getExampleString();
        List<Category> categories = new ArrayList<Category>();        
        
        JsonParser parser = new JsonParser();
        JsonObject element = (JsonObject)parser.parse(sourceJson);
        JsonElement responseWrapper = element.get("Categorias");    
        
        categories = new Gson()
                        .fromJson(responseWrapper, Category.getTypeForList());
        
        for (Category c : categories){
            System.out.println(c);
        }
    }
    

}
