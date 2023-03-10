package util;

import java.util.LinkedHashMap;
import java.util.Map;

public class JsonObject {
    public  static Map<String,String> parser(String json){
        Map<String,String> map=new LinkedHashMap<>();
        System.out.println(json);
        String subString=json.substring(1,json.length()-1).trim();
        String [] splitAttribute=subString.split("\\s+\\,+\\s+");
        for(String ele:splitAttribute){
            String []splitEle=ele.split("([\\s+\"]\\:[\\s+\"])");
            String key=splitEle[0].trim().replaceAll("\"","");
            String value=splitEle[1].trim().replaceAll("\"","");
            map.put(key,value);
        }
        return  map;
    }

}
