package payload;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Response {
    private String name;
    private Date currentTime;

    public  Response(){};

    public Response(String name, Date currentTime) {
        this.name = name;
        this.currentTime = currentTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentTime() {
        return new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").format(new Date());
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        return "{"+"\"name\":" + "\""+name+ "\""+","+"\"currentTime\":"+ "\""+getCurrentTime()+ "\""+"}";
    }

    public static void main(String[] args) {
        Response response=new Response("Tirmai",new Date());
        System.out.println(response);
    }
}
