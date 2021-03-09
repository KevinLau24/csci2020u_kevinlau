package sample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FileLoader {

    private String filename;
    public int tornado = 0;
    public int flashFlood = 0;
    public int thunderstorm = 0;
    public int marine = 0;
    public static HashMap<String, Integer> data = new HashMap<String, Integer>();

    public FileLoader (String filename){
        this.filename = filename;
        this.data = data;
    }

    public void loadFile(){
        String line = "";

        try{
            BufferedReader br = new BufferedReader(new FileReader(this.filename));
            while((line = br.readLine()) != null){
                String[] col = line.split(",");
                System.out.println(col[5]);
                if(data.containsKey(col[5])) {
                    int previous = data.get(col[5]);
                    data.put(col[5], previous + 1);
                }else{
                    data.put(col[5], 1);
                }
            }
            System.out.println(data);
            //System.out.println("Flood: " + flashFlood + " Marine: " + marine + " Thunderstorm: " + thunderstorm + " Tornado: " + tornado);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static HashMap getData(){
        return data;
    }
}
