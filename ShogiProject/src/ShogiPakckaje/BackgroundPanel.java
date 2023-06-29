package ShogiPakckaje;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class BackgroundPanel extends JPanel implements MouseListener {

	private Image background;
	private GameBoard gameboardscreen;
	private PlayerHand playerhand1;
	private PlayerHand playerhand2;
	private int playerturn=1;
	
	public BackgroundPanel() {
		gameboardscreen = new GameBoard("DEFAULT_SET",playerturn);	
		playerhand2 = new PlayerHand(-1);
		playerhand1 = new PlayerHand(1);
		TitledBorder enemytitle = BorderFactory.createTitledBorder("Player 2 Hand");
		playerhand2.setBorder(enemytitle);
		TitledBorder myhandtitle = BorderFactory.createTitledBorder("Player 1 Hand");
		playerhand1.setBorder(myhandtitle);
		gameboardscreen.sethand1(playerhand1);
		gameboardscreen.sethand2(playerhand2);
		playerhand1.setBoard(gameboardscreen);
		playerhand2.setBoard(gameboardscreen);
		this.add(playerhand2);
		this.add(gameboardscreen);
		AddEndTurnButton();
		this.add(playerhand1);
		
	}


	
	public void AddEndTurnButton() {
		JButton endturnButton = new JButton("End Turn");
		endturnButton.setPreferredSize(new Dimension(90,30));
		this.add(endturnButton);
		endturnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameboardscreen.changeTurn();
				playerturn*=-1;
				playerhand1.reset(playerturn);
				playerhand2.reset(playerturn);
				
			}
		});		
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	


}
