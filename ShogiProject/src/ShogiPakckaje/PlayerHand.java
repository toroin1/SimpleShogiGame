package ShogiPakckaje;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PlayerHand extends JPanel implements MouseListener,ConnectionWithHand {
	private ArrayList<Piece> piecehand;
	private ArrayList<Tile> displayedhand;
	private int player;
	private Piece selectedPiece;
	private ConnectionWithGameBoard gameboard;
	private Piece capturedPiece;
	
	public PlayerHand(int player) {
		this.player = player;
		addMouseListener(this);
		piecehand = new ArrayList<Piece>();
		displayedhand = new ArrayList<Tile>();
		setPreferredSize(new Dimension(400,970));
	}

	public int getPlayer() {
		return player;
	}
	
	public void setBoard(ConnectionWithGameBoard gameboard) {
		this.gameboard=gameboard;
	}
	
	private void capturePiece() {
		if(capturedPiece.player == 0) {return;}
		capturedPiece.unpromote();
		capturedPiece.setPlayer(player);
		piecehand.add(capturedPiece);
		displayedhand.add(new Tile(capturedPiece));
		displayedhand.get(displayedhand.size()-1).addMouseListener(this);
		displaynew();
	}
	private void displaynew() {
		displayedhand.get(displayedhand.size()-1).notSelected(player);
		this.add(displayedhand.get(displayedhand.size()-1));
	}
	
	public Piece clickedPiece() {
		return selectedPiece;
	}
	
	public void select(Tile selected) {
		for(Tile sel : displayedhand) {
			sel.notSelected(player);
		}
		selected.canBeSelected();
		selectedPiece=selected.getPiece();
	}
	
	public void reset(int playerturn) {
		if(playerturn !=player) {
			for(Tile sel : displayedhand) {
				sel.notSelected(player);
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(!(e.getComponent() instanceof Tile)) {
			return;
		}
		Tile selected = (Tile)e.getComponent();
		
		for(Tile sel : displayedhand) {
			sel.notSelected(player);
		}
		if(player == gameboard.getTurn()) {
			selected.canBeSelected();
			selectedPiece=selected.getPiece();
			gameboard.selectFromHand(selectedPiece);
		}
		
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

	@Override
	public void addToHand(Piece addPiece) {
		capturedPiece = addPiece;
		capturePiece();
		
	}

	@Override
	public void removeFromHand() {
		for (int i = 0; i < displayedhand.size(); i++) {
		      if(displayedhand.get(i).isSelected) {
		    	  piecehand.remove(i);
		    	  this.remove(displayedhand.get(i));
		    	  displayedhand.remove(i);
		      }
		    }
		this.repaint();
	}

}
