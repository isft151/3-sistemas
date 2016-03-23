package isft151.example;

import java.lang.reflect.Type;

import com.google.gson.annotations.SerializedName;

import example.gson.ListParameterizedType;

public class Category {
    
    @SerializedName("IdCategoria")
    private int id;
    
    @SerializedName("DescripcionCategoria")
    private String name;
    
    public static Type getTypeForList(){
        return new ListParameterizedType(Category.class);
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "["+id+"]"+" " +name;
    }
}

