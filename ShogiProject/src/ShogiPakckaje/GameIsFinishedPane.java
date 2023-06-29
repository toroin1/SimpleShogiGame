package ShogiPakckaje;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameIsFinishedPane {
	JFrame finishedGamePopUp;
	GameIsFinishedPane(int player){
		finishedGamePopUp = new JFrame();
		JOptionPane.showMessageDialog(finishedGamePopUp, " Game ended with "+ winningMassage(player) + "'s victory.");
		endgame();
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
	
	private void endgame() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
}
