package pl.etroya.design.facade;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class FacadeEverydaySample {
    public static void main(String args[]) throws Exception {
        URL url = new URL("http", "adamtrojnar.blogspot.com", 80, "/2014/04/nie-mozesz-odpalic-projektu-z-tomcat-bo.html");
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputLine;

        while((inputLine = in.readLine()) != null){
            System.out.println(inputLine);
        }
    }
}
