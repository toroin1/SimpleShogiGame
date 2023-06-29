package ShogiPakckaje;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PlayersTurnPane  {
	JFrame palyerTurn;
	PlayersTurnPane (int player){
		palyerTurn = new JFrame();
		JOptionPane.showMessageDialog(palyerTurn, winningMassage(player) + "' turn has ended.");
	}
	
	private String winningMassage(int player) {
		switch(player) {
			case 1:
				return "Player 1";
			case -1:
				return "Player 2";
			default:
				return "";
		}
	}

}
