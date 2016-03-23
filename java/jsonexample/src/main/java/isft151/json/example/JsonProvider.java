package isft151.json.example;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class JsonProvider {
    
    public static String getExampleString(){
        StringBuilder result = new StringBuilder();
        try{          
            FileInputStream fileStream = new FileInputStream("categories.json");          
            DataInputStream inputStream = new DataInputStream(fileStream);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            
            while ((line = buffer.readLine()) != null)   {
                result.append(line);
            }

            fileStream.close();
            inputStream.close();
            buffer.close();
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
         }
        return result.toString();          
    
    }
    
}
