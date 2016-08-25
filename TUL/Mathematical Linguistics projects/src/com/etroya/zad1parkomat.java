package com.etroya;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: trojnaradam@gmail.com
 * Date: 14.01.15
 * Time: 18:54
 * Wykorzystując automa skończony (FA) zaproponuj program będący symulatorem parkometru
 */
public class zad1parkomat extends JFrame implements ActionListener {

  public static void main(String[] args) {
    new zad1parkomat();
  }

  private JPanel[] panel;
  private String listaStanowParkomatu = "0 pln";
  private JButton[] przyciskiPLN;
  private JLabel podpisStanu;
  private JTextField stan;
  private JLabel podpisOperacji;
  private JTextArea operacje;
  private JLabel etykietaListyStanow;
  private JTextArea listaStanow;
  private int stanParkomatu = 0;
  private int automat[][] = {
      {0, 1, 1}, {0, 2, 2}, {0, 5, 5},
      {1, 1, 2}, {1, 2, 3}, {1, 5, 6},
      {2, 1, 3}, {2, 2, 4}, {2, 5, 7},
      {3, 1, 4}, {3, 2, 5}, {3, 5, -1},
      {4, 1, 5}, {4, 2, 6}, {4, 5, -1},
      {5, 1, 6}, {5, 2, 7}, {5, 5, -1},
      {6, 1, 7}, {6, 2, -1}, {6, 5, -1},
      {7, 1, -1}, {7, 2, -1}, {7, 5, -1}
  };

  zad1parkomat() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
    setLocation(x, y);
    setTitle("Zadanie 1: Automat stanów -parkometr");
    setResizable(false);

    przyciskiPLN = new JButton[5];
    przyciskiPLN[0] = new JButton("wrzuć 1 pln");
    przyciskiPLN[1] = new JButton("wrzuć 2 pln");
    przyciskiPLN[2] = new JButton("wrzuć 5 pln");
    przyciskiPLN[3] = new JButton("Odbierz bilet");
    przyciskiPLN[3].setEnabled(false);
    przyciskiPLN[4] = new JButton("Restart");
    przyciskiPLN[4].setEnabled(false);

    for (int i = 0; i < przyciskiPLN.length; i++)
      przyciskiPLN[i].addActionListener(this);

    podpisStanu = new JLabel("Stan");
    stan = new JTextField("0 pln", 5);
    stan.setEditable(false);
    stan.setBackground(Color.yellow);
    podpisOperacji = new JLabel("Operacje");
    operacje = new JTextArea(8, 20);
    operacje.setEditable(false);

    etykietaListyStanow = new JLabel("Lista stanów");

    listaStanow = new JTextArea(2, 26);
    listaStanow.setPreferredSize(new Dimension(360, 20));
    listaStanow.setEditable(false);

    panel = new JPanel[2];
    for (int i = 0; i < panel.length; i++) {
      panel[i] = new JPanel();
    }
    panel[0].setLayout(new GridLayout(1, 5));
    panel[1].setLayout(new FlowLayout(FlowLayout.LEADING));

    for (int i = 0; i < przyciskiPLN.length; i++)
      panel[0].add(przyciskiPLN[i]);

    panel[1].add(podpisStanu);
    panel[1].add(stan);
    panel[1].add(podpisOperacji);
    panel[1].add(operacje);
    panel[1].add(etykietaListyStanow);
    panel[1].add(listaStanow);

    getContentPane().setLayout(new FlowLayout(FlowLayout.LEADING));
    getContentPane().setPreferredSize(new Dimension(875, 150));
    panel[1].setBackground(Color.lightGray);

    for (int i = 0; i < panel.length; i++)
      getContentPane().add(panel[i]);

    pack();
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    int nominal = 0;
    if (e.getActionCommand().equals("wrzuć 1 pln"))
      nominal = 1;
    else if (e.getActionCommand().equals("wrzuć 2 pln"))
      nominal = 2;
    else if (e.getActionCommand().equals("wrzuć 5 pln"))
      nominal = 5;
    else {
      stanParkomatu = 0;
      operacje.setText("");
      listaStanowParkomatu = "0 pln";
      listaStanow.setText("");
    }

    for (int i = 0; i < automat.length; i++)
      if (automat[i][0] == stanParkomatu && automat[i][1] == nominal) {
        stanParkomatu = automat[i][2];
        if (stanParkomatu == -1) {
          listaStanowParkomatu += " | nieakcept.";
          operacje.append(automat[i][0] + "\t" + automat[i][1] + "\tnieakcept.\n");
        } else {
          listaStanowParkomatu += " | " + stanParkomatu + " pln ";
          operacje.append(automat[i][0] + "\t" + automat[i][1] + "\t" + automat[i][2] + "\n");
        }
        break;
      }

    if (stanParkomatu == -1) {
      stan.setText("nieakceptowany");
      for (int i = 0; i < przyciskiPLN.length; i++)
        if (przyciskiPLN[i].getText().equals("Restart"))
          przyciskiPLN[i].setEnabled(true);
        else
          przyciskiPLN[i].setEnabled(false);
      listaStanow.setText(listaStanowParkomatu);
    } else if (stanParkomatu == 7) {
      stan.setText(stanParkomatu + " pln");
      for (int i = 0; i < przyciskiPLN.length; i++)
        if (przyciskiPLN[i].getText().equals("Odbierz bilet"))
          przyciskiPLN[i].setEnabled(true);
        else
          przyciskiPLN[i].setEnabled(false);
      listaStanow.setText(listaStanowParkomatu);
    } else {
      stan.setText(stanParkomatu + " pln");
      for (int i = 0; i < przyciskiPLN.length; i++)
        if (!przyciskiPLN[i].getText().equals("Restart") && !przyciskiPLN[i].getText().equals("Odbierz bilet"))
          przyciskiPLN[i].setEnabled(true);
        else
          przyciskiPLN[i].setEnabled(false);
    }
  }
}
