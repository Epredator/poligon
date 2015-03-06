package com.etroya;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: trojnaradam@gmail.com
 * Date: 24.01.15
 * Time: 15:05
 */
public class kodPocztowy {

  public static void main(String[] args) {
    String text = "30-079";
    new kodPocztowy(text);
  }

  kodPocztowy(String text) {
    Pattern pattern = Pattern.compile("^[0-9]{2}\\-[0-9]{3}$");
    Matcher matcher = pattern.matcher(text);
    if (matcher.find())
      System.out.println(text + " jest poprawnym kodem pocztowy");
      else
      System.out.println(text + " NIE jest poprawnym kodem pocztowy");
  }

}
