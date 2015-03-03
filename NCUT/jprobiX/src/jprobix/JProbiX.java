/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jprobix;


import jprobix.ui.UI;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author epredator
 */
public class JProbiX {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
               new UI().setVisible(true);
              
                
            }
        });
    }

    
}





