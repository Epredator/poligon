package com.etroya;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: trojnaradam@gmail.com
 * Date: 20.01.15
 * Time: 11:53
 */
public class zad3poprawionyParkomat extends JFrame implements ActionListener  {

  JPanel[] panel;
  String listaStanowParkomatu = "0";
  JButton[] przyciskiPLN;
  JLabel podpisStanu;
  JTextField stan;
  JLabel podpisWrzuconych;
  JTextField wrzucone;
  JLabel podpisReszty;
  JTextField reszta;
  JLabel podpisOperacji;
  JTextArea operacje;
  JLabel podpisListyStanow;
  JTextArea listaStanow;
  int zl = 0;
  int resztaZl = 0;
  int stanParkomatu = 0;
  int automat[][] = {
      {0, 1, 1, 1}, {0, 2, 2, 2}, {0, 5, 5, 5},
      {1, 1, 2, 1}, {1, 2, 3, 2}, {1, 5, 6, 5},
      {2, 1, 3, 1}, {2, 2, 4, 2}, {2, 5, 7, 5},
      {3, 1, 4, 1}, {3, 2, 5, 2}, {3, 5, -1, 5},
      {4, 1, 5, 1}, {4, 2, 6, 2}, {4, 5, -2, 5},
      {5, 1, 6, 1}, {5, 2, 7, 2}, {5, 5, -3, 5},
      {6, 1, 7, 1}, {6, 2, -1, 2}, {6, 5, -4, 5},
      {7, 1, -1, 1}, {7, 2, -2, 2}, {7, 5, -5, 5}
  };

  zad3poprawionyParkomat() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
    setLocation(x, y);
    setTitle("Zadanie 3: Automat stanów -parkometr poprawiony");
    setResizable(false);

    przyciskiPLN = new JButton[4];
    przyciskiPLN[0] = new JButton("wrzuć 1 pln");
    przyciskiPLN[1] = new JButton("wrzuć 2 pln");
    przyciskiPLN[2] = new JButton("wrzuć 5 pln");
    przyciskiPLN[3] = new JButton("Odbierz bilet (i resztę)");
    przyciskiPLN[3].setEnabled(false);

    for(int i = 0; i < przyciskiPLN.length; i++)
      przyciskiPLN[i].addActionListener(this);

    podpisStanu = new JLabel("Stan");
    stan = new JTextField("0 pln", 3);
    stan.setEditable(false);

    podpisWrzuconych = new JLabel("Wrzucono");
    wrzucone = new JTextField("0 PLN", 3);
    wrzucone.setEditable(false);

    podpisReszty = new JLabel("Reszta");
    reszta = new JTextField("0 pln", 3);
    reszta.setEditable(false);

    podpisOperacji = new JLabel("Operacje");
    operacje = new JTextArea(8, 26);
    operacje.setEditable(false);
    operacje.setText("Stan\tOdczyt\tStan nast.\tZapis\n");

    podpisListyStanow = new JLabel("Lista stanów");
    listaStanow = new JTextArea(2, 26);
    listaStanow.setEditable(false);

    panel = new JPanel[2];
    for(int i = 0; i < panel.length; i++)
    {
      panel[i] = new JPanel();
//      panel[i].setPreferredSize(new Dimension(300, 250));
    }
    panel[0].setLayout(new GridLayout(1,5));
    panel[1].setLayout(new FlowLayout(FlowLayout.LEADING));

    for(int i = 0; i < przyciskiPLN.length; i++)
      panel[0].add(przyciskiPLN[i]);
    panel[1].add(podpisStanu);
    panel[1].add(stan);
    panel[1].add(podpisWrzuconych);
    panel[1].add(wrzucone);
    panel[1].add(podpisReszty);
    panel[1].add(reszta);
    panel[1].add(podpisOperacji);
    panel[1].add(operacje);
    panel[1].add(podpisListyStanow);
    panel[1].add(listaStanow);

    getContentPane().setLayout(new FlowLayout(FlowLayout.LEADING));
    getContentPane().setPreferredSize(new Dimension(975, 200));
    for(int i = 0; i < panel.length; i++)
      getContentPane().add(panel[i]);

    pack();
    setVisible(true);
  }



  @Override
  public void actionPerformed(ActionEvent e) {

    int nominal = 0;
    if(e.getActionCommand().equals("wrzuć 1 pln"))
      nominal = 1;
    else if(e.getActionCommand().equals("wrzuć 2 pln"))
      nominal = 2;
    else if(e.getActionCommand().equals("wrzuć 5 pln"))
      nominal = 5;
    else
    {
      stanParkomatu = 0;
      zl = 0;
      resztaZl = 0;
      operacje.setText("Stan\tOdczyt\tStan nast.\tZapis\n");
      listaStanowParkomatu = "0";
      listaStanow.setText("");
    }

    for(int i = 0; i < automat.length; i++)
      if(automat[i][0] == stanParkomatu && automat[i][1] == nominal)
      {
        stanParkomatu = automat[i][2];
        zl += automat[i][1];
        if(zl > 7)
          resztaZl = zl - 7;
        listaStanowParkomatu += " | " + stanParkomatu + " ";
        operacje.append(automat[i][0] + "\t" + automat[i][1] + "\t" + automat[i][2] + "\t" + automat[i][3] + "\n");
        break;
      }

    if(stanParkomatu == 7 || stanParkomatu < 0)
    {
      for(int i = 0; i < przyciskiPLN.length; i++)
        if(przyciskiPLN[i].getText().equals("Odbierz bilet (i resztę)"))
          przyciskiPLN[i].setEnabled(true);
        else
          przyciskiPLN[i].setEnabled(false);
      listaStanow.setText(listaStanowParkomatu);
    }
    else
    {
      for(int i = 0; i < przyciskiPLN.length; i++)
        if(!przyciskiPLN[i].getText().equals("Odbierz bilet (i resztę)"))
          przyciskiPLN[i].setEnabled(true);
        else
          przyciskiPLN[i].setEnabled(false);
    }

    stan.setText(stanParkomatu + "");
    wrzucone.setText(zl + " zl");
    reszta.setText(resztaZl + " zl");
  }

  public static void main(String[] args) {
    new zad3poprawionyParkomat();
  }
}
