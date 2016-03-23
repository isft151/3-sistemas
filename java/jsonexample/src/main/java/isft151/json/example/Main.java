package isft151.json.example;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) {
        
        String sourceJson = JsonProvider.getExampleString();
        List<Category> categories = new ArrayList<Category>();
        JSONObject jsonObject = new JSONObject(sourceJson);
        
        JSONArray array = jsonObject.getJSONArray("Categorias");

        for (int i = 0; i < array.length(); i++) {
            JSONObject o = array.getJSONObject(i);            
            Category cat = new Category();
            
            cat.setId(o.getInt("IdCategoria"));
            cat.setName(o.getString("DescripcionCategoria"));            
            categories.add(cat);
        }
        
        for (Category c : categories){
            System.out.println(c);
        }    
    
    }

}
