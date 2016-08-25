package com.etroya;


import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * User: trojnaradam@gmail.com
 * Date: 15.01.15
 * Time: 17:59
 * Wykorzystując niedeterministyczny automat skończony (NFA) opracuj program którybędzie akceptował wszystkie ciągi,
 * w których nastąpi dwukrotne powtórzenie znaku. NFA wykorzystuje następujący alfabet :∑= {0,1,2,3,4,5,6,7,8,9}
 */

public class zad2ciagi extends JFrame implements ActionListener {
  public static void main(String[] args) {
    new zad2ciagi();
  }

  String automat[][] = {
      {"A", "0", "A,0"}, {"A", "1", "A,1"}, {"A", "2", "A,2"},
      {"A", "3", "A,3"}, {"A", "4", "A,4"}, {"A", "5", "A,5"},
      {"A", "6", "A,6"}, {"A", "7", "A,7"}, {"A", "8", "A,8"},
      {"A", "9", "A,9"},

      {"0", "0", "B"}, {"0", "1", "A"}, {"0", "2", "A"},
      {"0", "3", "A"}, {"0", "4", "A"}, {"0", "5", "A"},
      {"0", "6", "A"}, {"0", "7", "A"}, {"0", "8", "A"},
      {"0", "9", "A"},

      {"1", "0", "A"}, {"1", "1", "B"}, {"1", "2", "A"},
      {"1", "3", "A"}, {"1", "4", "A"}, {"1", "5", "A"},
      {"1", "6", "A"}, {"1", "7", "A"}, {"1", "8", "A"},
      {"1", "9", "A"},

      {"2", "0", "A"}, {"2", "1", "A"}, {"2", "2", "B"},
      {"2", "3", "A"}, {"2", "4", "A"}, {"2", "5", "A"},
      {"2", "6", "A"}, {"2", "7", "A"}, {"2", "8", "A"},
      {"2", "9", "A"},

      {"3", "0", "A"}, {"3", "1", "A"}, {"3", "2", "A"},
      {"3", "3", "B"}, {"3", "4", "A"}, {"3", "5", "A"},
      {"3", "6", "A"}, {"3", "7", "A"}, {"3", "8", "A"},
      {"3", "9", "A"},

      {"4", "0", "A"}, {"4", "1", "A"}, {"4", "2", "A"},
      {"4", "3", "A"}, {"4", "4", "B"}, {"4", "5", "A"},
      {"4", "6", "A"}, {"4", "7", "A"}, {"4", "8", "A"},
      {"4", "9", "A"},

      {"5", "0", "A"}, {"5", "1", "A"}, {"5", "2", "A"},
      {"5", "3", "A"}, {"5", "4", "A"}, {"5", "5", "B"},
      {"5", "6", "A"}, {"5", "7", "A"}, {"5", "8", "A"},
      {"5", "9", "A"},

      {"6", "0", "A"}, {"6", "1", "A"}, {"6", "2", "A"},
      {"6", "3", "A"}, {"6", "4", "A"}, {"6", "5", "A"},
      {"6", "6", "B"}, {"6", "7", "A"}, {"6", "8", "A"},
      {"6", "9", "A"},

      {"7", "0", "A"}, {"7", "1", "A"}, {"7", "2", "A"},
      {"7", "3", "A"}, {"7", "4", "A"}, {"7", "5", "A"},
      {"7", "6", "A"}, {"7", "7", "B"}, {"7", "8", "A"},
      {"7", "9", "A"},

      {"8", "0", "A"}, {"8", "1", "A"}, {"8", "2", "A"},
      {"8", "3", "A"}, {"8", "4", "A"}, {"8", "5", "A"},
      {"8", "6", "A"}, {"8", "7", "A"}, {"8", "8", "B"},
      {"8", "9", "A"},

      {"9", "0", "A"}, {"9", "1", "A"}, {"9", "2", "A"},
      {"9", "3", "A"}, {"9", "4", "A"}, {"9", "5", "A"},
      {"9", "6", "A"}, {"9", "7", "A"}, {"9", "8", "A"},
      {"9", "9", "B"},

      {"B", "0", "B"}, {"B", "1", "B"}, {"B", "2", "B"},
      {"B", "3", "B"}, {"B", "4", "B"}, {"B", "5", "B"},
      {"B", "6", "B"}, {"B", "7", "B"}, {"B", "8", "B"},
      {"B", "9", "B"}
  };

  String plik;
  String dane;
  String ciagi[];

  HighlightPainter podswietlenie;

  JButton przyciski[];
  JLabel nazwaSciezki;
  JTextField sciezka;
  JLabel nazwaZawartosciPliku;
  JTextArea zawartoscPliku;
  JLabel nazwaListyStanow;
  JTextArea listaStanow;
  JPanel panel1, panel2, panel3, panel4, panel5;
  JScrollPane przewijanie1, przewijanie2;

  zad2ciagi() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("zad2: Ciągi");
    setResizable(false);
    podswietlenie = new DefaultHighlighter.DefaultHighlightPainter(Color.green);
    przyciski = new JButton[2];
    przyciski[0] = new JButton("Wczytaj plik");
    przyciski[1] = new JButton("Przeszukaj plik");

    for (int i = 0; i < przyciski.length; i++)
      przyciski[i].addActionListener(this);

    nazwaSciezki = new JLabel("Ścieżka do pliku");
    sciezka = new JTextField("/home/epredator/string.txt", 24);
    sciezka.setSelectionStart(sciezka.getText().length());

    nazwaZawartosciPliku = new JLabel("Zawarość pliku");
    zawartoscPliku = new JTextArea(7, 25);
    zawartoscPliku.setEditable(false);

    nazwaListyStanow = new JLabel("Lista stanów");
    listaStanow = new JTextArea(16, 25);
    listaStanow.setEditable(false);

    panel1 = new JPanel();
    panel1.setLayout(new GridLayout(0, 1));
    panel1.setPreferredSize(new Dimension(290, 90));

    panel2 = new JPanel();
    panel2.setLayout(new FlowLayout(FlowLayout.LEADING));
    panel2.setPreferredSize(new Dimension(290, 160));

    panel3 = new JPanel();
    panel3.setLayout(new GridLayout(0, 1));
    panel3.setPreferredSize(new Dimension(290, 30));

    panel4 = new JPanel();
    panel4.setLayout(new FlowLayout());
    panel4.setPreferredSize(new Dimension(300, 320));

    panel5 = new JPanel();
    panel5.setLayout(new FlowLayout(FlowLayout.LEADING));
    panel5.setPreferredSize(new Dimension(300, 320));

    panel1.add(nazwaSciezki);
    panel1.add(sciezka);
    panel1.add(przyciski[0]);
    panel2.add(nazwaZawartosciPliku);
    przewijanie1 = new JScrollPane(zawartoscPliku, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    panel2.add(przewijanie1);
    panel3.add(przyciski[1]);
    panel4.add(panel1);
    panel4.add(panel2);
    panel4.add(panel3);
    panel5.add(nazwaListyStanow);
    przewijanie2 = new JScrollPane(listaStanow, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    panel5.add(przewijanie2);

    getContentPane().setLayout(new FlowLayout());
    getContentPane().setPreferredSize(new Dimension(620, 340));
    getContentPane().add(panel4);
    getContentPane().add(panel5);

    pack();
    setVisible(true);
  }

  private void pobierzDane() {

    plik = sciezka.getText();
    dane = "";

    File file = new File(plik);
    Scanner in;
    try {
      in = new Scanner(file);
      while (in.hasNextLine())
        dane += in.nextLine();
      in.close();
      zawartoscPliku.setText(dane);
    } catch (FileNotFoundException e) {
      zawartoscPliku.setText("");
    }
  }

  private void sprawdzCiagi() {

    ciagi = dane.split("#");
    int poczatekCiagu = 0;

    for (int n = 1; n < ciagi.length; n++) {
      ArrayList<String> stanyAutomatu = new ArrayList<String>();
      stanyAutomatu.add("A");

      System.out.println("Ciag: " + ciagi[n]);
      listaStanow.append("Ciag: " + ciagi[n] + "\n");

      for (int i = 0; i < ciagi[n].length(); i++) {
        ArrayList<String> nextAutoStan = new ArrayList<String>();
        for (int j = 0; j < automat.length; j++)
          if (stanyAutomatu.contains(automat[j][0]) && Character.toString(ciagi[n].charAt(i)).equals(automat[j][1])) {
            String stanyNastepne[] = automat[j][2].split(",");
            for (int k = 0; k < stanyNastepne.length; k++)
              if (!nextAutoStan.contains(stanyNastepne[k]))
                nextAutoStan.add(stanyNastepne[k]);
          }
        System.out.print(stanyAutomatu + " --- " + Character.toString(ciagi[n].charAt(i)) + " --> ");
        listaStanow.append(stanyAutomatu + " --- " + Character.toString(ciagi[n].charAt(i)) + " --> ");

        stanyAutomatu = nextAutoStan;
        System.out.println(stanyAutomatu);
        listaStanow.append(stanyAutomatu + "\n");
      }
      if (stanyAutomatu.contains("B")) {
        try {
          zawartoscPliku.getHighlighter().addHighlight(poczatekCiagu + n, poczatekCiagu + ciagi[n].length() + n, podswietlenie);
        } catch (BadLocationException e1) {
        }
      }
      poczatekCiagu += ciagi[n].length();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    listaStanow.setText("");

    if (e.getActionCommand().equals("Wczytaj plik"))
      pobierzDane();
    else if (e.getActionCommand().equals("Przeszukaj plik")) {
      if (!dane.isEmpty())
        sprawdzCiagi();
    }
  }


}