// Author: Montaril
package WWP;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

// Main menu class.
public class Menu implements ActionListener
{
    // Declare colors and font
	Color fg = new Color(0xD8DEE9);
	Color bg = new Color(0x2E3440);
	Color green = new Color(0xA3BE8C);
	Color red = new Color(0xBF616A);
	
	Font sf_mono = new Font("SF Mono", Font.PLAIN, 25);

    // Declare some variables.
    JFrame menu = new JFrame();

    JTextField title = new JTextField();
    JButton buttonPlay = new JButton();
    JButton buttonExit = new JButton();

    public Menu()
    {
        // Background image.
		ImageIcon Img = new ImageIcon("./resources/bg.png");
		JLabel bgImg = new JLabel("", Img, JLabel.CENTER);
		bgImg.setBounds(0, 0, 1280, 720);
        
        // Main menu frame.
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setSize(1280, 750);
        menu.setTitle("Who Wants to be a Programmer?");
        menu.setLocationRelativeTo(null);
		menu.getContentPane().setBackground(bg);
		menu.setLayout(null);
		menu.setResizable(false);

        // Text title.
        title.setBounds(390, 180, 500, 100);
        title.setFont(sf_mono);
        title.setBackground(bg);
        title.setForeground(fg);
        title.setBorder(BorderFactory.createLineBorder(fg, 3));
        title.setHorizontalAlignment(JTextField.CENTER);
        title.setText("Who Wants to be a Programmer?");

        // Play button.
        buttonPlay.setBounds(540, 290, 200, 100);
        buttonPlay.setFont(sf_mono);
        buttonPlay.setBackground(bg);
        buttonPlay.setForeground(fg);
        buttonPlay.setBorder(BorderFactory.createLineBorder(fg, 3));
        buttonPlay.setFocusable(false);
        buttonPlay.addActionListener(this);
        buttonPlay.setText("Play game");

        // Exit button.
        buttonExit.setBounds(540, 400, 200, 100);
        buttonExit.setFont(sf_mono);
        buttonExit.setBackground(bg);
        buttonExit.setForeground(fg);
        buttonExit.setBorder(BorderFactory.createLineBorder(fg, 3));
        buttonExit.setFocusable(false);
        buttonExit.addActionListener(this);
        buttonExit.setText("Exit game");

        menu.add(title);
        menu.add(buttonPlay);
        menu.add(buttonExit);
        menu.add(bgImg);
        menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonPlay)
        {
            Quiz quiz = new Quiz();
            menu.dispose();
        }
        
        if(e.getSource()==buttonExit)
        {
            System.exit(0);
        }
    }
}
