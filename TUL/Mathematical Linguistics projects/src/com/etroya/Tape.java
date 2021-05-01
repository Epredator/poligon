package com.etroya;

import java.util.Stack;

/**
 * User: trojnaradam@gmail.com
 * Date: 24.01.15
 * Time: 14:28
 */
public class Tape {
  private Stack<String> s1 = new Stack<String>();   // left part of tape
  private Stack<String> s2 = new Stack<String>();   // right part of tape
  private String current;                           // current symbol
  private final static String BLANK = "#";

  // allocate, initialize, and return a Tape whose contents are init
  public Tape() { this(BLANK); }
  public Tape(String init) {
    if (init == null || init.equals("")) init = BLANK;
    for (int i = init.length() - 1; i >= 0; i--) {
      current = init.substring(i, i+1);
      s2.push(current);
    }
    current = s2.pop();
  }

  // move tape head left one position
  public void left() {
    s2.push(current);
    if (s1.isEmpty()) s1.push(BLANK);  // increase size of tape if necessary
    current = s1.pop();
  }

  // move tape head right one position
  public void right() {
    s1.push(current);
    if (s2.isEmpty()) s2.push(BLANK);  // increase size of tape if necessary
    current = s2.pop();
  }

  // set and get the symbol stored in the active cell
  public String read()             { return current;    }
  public void write(String symbol) { current = symbol;  }


  // return a reversed version of the string s
  public static String reverse(String s) {
    int N = s.length();
    if (N <= 1) return s;
    String left = s.substring(0, N/2);
    String right = s.substring(N/2, N);
    return reverse(right) + reverse(left);
  }

  // show representation of tape
  public String toString() {
    return BLANK + BLANK + reverse(s1.toString()) + "|" + current + "|" + s2 + BLANK + BLANK;
  }


  public static void main(String[] args) {
    new Tape();
  }
}

