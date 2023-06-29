package ShogiPakckaje;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ShogiProjectMain {
	

	public static void main(String[] args) throws IOException {

		JFrame gamescreen = new JFrame();
		gamescreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamescreen.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height-40);
		gamescreen.setResizable(false);
		gamescreen.setTitle("Simple shogi game");
		gamescreen.setIconImage((new ImageIcon("hqdefault.jpg")).getImage());

		BackgroundPanel centerside = new BackgroundPanel();


		gamescreen.add(centerside,BorderLayout.CENTER);

		
		gamescreen.setVisible(true);		
	}


	
}


