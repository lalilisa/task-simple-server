package util;

import java.util.LinkedHashMap;
import java.util.Map;

public class JsonObject {

    public  static Map<String,String> parser(String json){
        Map<String,String> map=new LinkedHashMap<>();
        String subString=json.substring(1,json.length()-1).trim();
        String [] splitAttribute=subString.split("\\,+");
        for(String ele:splitAttribute){
            String []splitEle=ele.split("\"\\:\"");
            String key=splitEle[0].replaceAll("\"","");
            String value=splitEle[1].replaceAll("\"","");
            map.put(key,value);
        }
        System.out.println(map);
        return  map;
    }


}
