package ShogiPakckaje;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GameBoard extends JPanel implements MouseListener,ConnectionWithGameBoard {
	
	private Piece[][] gameboard = new Piece[9][9];
	private Tile[][] tileBoard = new Tile[9][9];
	private  Tile selectedPiece ;
	private int playerturn;
	private int moves;
	private boolean readytoDropPiece;
	private ConnectionWithHand hand1;
	private ConnectionWithHand hand2;
	
	public GameBoard(String initialset,int firstturn) {
		this.setLayout(new GridLayout(9,9));
		setBorder(BorderFactory.createLineBorder(Color.black,4));
		 initialiseSet(initialset);
		 fillBoard();
		 addMouseListener(this);
		 playerturn = firstturn;
		 moves =0;
		 readytoDropPiece=false;
	}

	public void initialiseSet(String initialiseMod) {
		switch(initialiseMod) {
		case"DEFAULT_SET":{
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++) {
					gameboard[i][j] = new nullPiece(); gameboard[i][j].setPosition(i, j); 
				}
			}
//			for(int i=0;i<9;i++) {
//				gameboard[i][2] = new pawnPiece();gameboard[i][2].setPosition(i, 2);gameboard[i][2].setPlayer(-1);
//				gameboard[i][6] = new pawnPiece();gameboard[i][6].setPosition(i, 6);gameboard[i][6].setPlayer(1);
//			}
			gameboard[4][0]=new kingPiece();gameboard[4][0].setPlayer(-1);gameboard[4][0].setPosition(4, 0);
			gameboard[4][8]=new kingPiece();gameboard[4][8].setPlayer(1);gameboard[4][8].setPosition(4, 8);
			
			gameboard[0][0]=new lancePiece();gameboard[0][0].setPlayer(-1);gameboard[0][0].setPosition(0, 0);
			gameboard[8][0]=new lancePiece();gameboard[8][0].setPlayer(-1);gameboard[8][0].setPosition(8, 0);
			gameboard[8][8]=new lancePiece();gameboard[8][8].setPlayer(1);gameboard[8][8].setPosition(8, 8);
			gameboard[0][8]=new lancePiece();gameboard[0][8].setPlayer(1);gameboard[0][8].setPosition(0, 8);
			
			gameboard[1][0]=new knightPiece();gameboard[1][0].setPlayer(-1);gameboard[1][0].setPosition(1, 0);
			gameboard[7][0]=new knightPiece();gameboard[7][0].setPlayer(-1);gameboard[7][0].setPosition(7, 0);
			gameboard[1][8]=new knightPiece();gameboard[1][8].setPlayer(1);gameboard[1][8].setPosition(1, 8);
			gameboard[7][8]=new knightPiece();gameboard[7][8].setPlayer(1);gameboard[7][8].setPosition(7, 8);
			
			gameboard[2][0]=new silverGeneralPiece();gameboard[2][0].setPlayer(-1);gameboard[2][0].setPosition(2, 0);
			gameboard[6][0]=new silverGeneralPiece();gameboard[6][0].setPlayer(-1);gameboard[6][0].setPosition(6, 0);
			gameboard[2][8]=new silverGeneralPiece();gameboard[2][8].setPlayer(1);gameboard[2][8].setPosition(2, 8);
			gameboard[6][8]=new silverGeneralPiece();gameboard[6][8].setPlayer(1);gameboard[6][8].setPosition(6, 8);
			
			gameboard[3][0]=new goldGeneralPiece();gameboard[3][0].setPlayer(-1);gameboard[3][0].setPosition(3, 0);
			gameboard[5][0]=new goldGeneralPiece();gameboard[5][0].setPlayer(-1);gameboard[5][0].setPosition(5, 0);
			gameboard[3][8]=new goldGeneralPiece();gameboard[3][8].setPlayer(1);gameboard[3][8].setPosition(3, 8);
			gameboard[5][8]=new goldGeneralPiece();gameboard[5][8].setPlayer(1);gameboard[5][8].setPosition(5, 8);
			
			gameboard[7][1]=new bishopPiece();gameboard[7][1].setPlayer(-1);gameboard[7][1].setPosition(7, 1);
			gameboard[1][7]=new bishopPiece();gameboard[1][7].setPlayer(1);gameboard[1][7].setPosition(1, 7);
			
			gameboard[1][1]=new rookPiece();gameboard[1][1].setPlayer(-1);gameboard[1][1].setPosition(1, 1);
			gameboard[7][7]=new rookPiece();gameboard[7][7].setPlayer(1);gameboard[7][7].setPosition(7, 7);
			break;
		}
		case"EMPTY_SET":{
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++) {
					gameboard[i][j] = new nullPiece(); gameboard[i][j].setPosition(i, j); 
				}
			}
			break;
		}
		case"HANDICAP_SET":{}
		}
	}
	
	@Override
	public  int getTurn() {
		return playerturn;
	}
	
	public void changeTurn() {
		new PlayersTurnPane(playerturn);
		playerturn *=-1;
		moves = 0;
		reset();
	}
	
	private void fillBoard() {
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
					tileBoard[j][i]  = new Tile(gameboard[j][i]);
					tileBoard[j][i].addMouseListener(this);
					tileBoard[j][i].notSelected(playerturn);
					add(tileBoard[j][i]);
			}
		}
	}
	
	private void reset() {
		boolean gameIsFinished=false;
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
					tileBoard[j][i].notSelected(playerturn);
					if(tileBoard[j][i].getPiece().player==playerturn&&tileBoard[j][i].getPiece().isKingPiece()) {
						gameIsFinished = tileBoard[j][i].getPiece().isKingPiece();
					}
			}
		}
		if(!gameIsFinished) {
			new GameIsFinishedPane(-1*playerturn);
		}
	}
	
	private void moveTo(Piece from, Piece to) {
		
		Tile tomove = tileBoard[from.getPositionX()][from.getPositionY()];
		if(gameboard[from.getPositionX()][from.getPositionY()].canBePromoted()||gameboard[to.getPositionX()][to.getPositionY()].canBePromoted()) {
			tomove.setcanBePromoted(true);
		}
		gameboard[from.getPositionX()][from.getPositionY()] = new nullPiece();
		gameboard[from.getPositionX()][from.getPositionY()].setPosition(from.getPositionX(),from.getPositionY());
		tomove.changePiece(gameboard[from.getPositionX()][from.getPositionY()]);
		tomove = tileBoard[to.getPositionX()][to.getPositionY()];
		gameboard[to.getPositionX()][to.getPositionY()]=from;		
		gameboard[to.getPositionX()][to.getPositionY()].setPosition(to.getPositionX(),to.getPositionY());
		tomove.changePiece(gameboard[to.getPositionX()][to.getPositionY()]);
		if(gameboard[from.getPositionX()][from.getPositionY()].canBePromoted()||gameboard[to.getPositionX()][to.getPositionY()].canBePromoted()) {
			tomove.setcanBePromoted(true);
		}
	}

	private void displaypossiblemoves(Tile selected) {
		ArrayList<Integer[]> moves = selected.getPiece().calculatePosiblePositions(gameboard);
		for(Integer[] i:moves) {
			tileBoard[i[0]][i[1]].canBeSelected();
		}
	}
	
	private void addToHand(Piece add) {
		switch(playerturn) {
		case 1: 
			hand1.addToHand(add);
			break;
		case -1:
			hand2.addToHand(add);
			break;		
		}
	}
	
	private void removeFromHand() {
		switch(playerturn) {
		case 1: 
			hand1.removeFromHand();
			break;
		case -1:
			hand2.removeFromHand();
			break;		
		}
	}
	
	@Override
	public void sethand1(ConnectionWithHand hand1) {
		this.hand1=hand1;
	}
	
	@Override
	public void sethand2(ConnectionWithHand hand2) {
		this.hand2=hand2;
	}
	
	public void dropAdd(Piece from, Piece to) {
		if(moves>0&&to.player!=0) {return;}
		gameboard[to.getPositionX()][to.getPositionY()]=from;		
		gameboard[to.getPositionX()][to.getPositionY()].setPosition(to.getPositionX(),to.getPositionY());
		tileBoard[to.getPositionX()][to.getPositionY()].changePiece(gameboard[to.getPositionX()][to.getPositionY()]);
		removeFromHand();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(!(e.getComponent() instanceof Tile)) {
			return;
		}
		Tile selected = (Tile)e.getComponent();

		if(tileBoard[selected.getPiece().getPositionX()][selected.getPiece().getPositionY()].getPiece().player==playerturn &&moves<1) {
			reset();

			selectedPiece=tileBoard[selected.getPiece().getPositionX()][selected.getPiece().getPositionY()];
			tileBoard[selected.getPiece().getPositionX()][selected.getPiece().getPositionY()].canBeSelected();
			 displaypossiblemoves(tileBoard[selected.getPiece().getPositionX()][selected.getPiece().getPositionY()]);
			 readytoDropPiece=false;

			 return;
		}
		if(!selected.equals(selectedPiece)&&moves<1) {
			if(selected.isSelected&&(!readytoDropPiece)) {

				addToHand(selected.getPiece());
				moveTo(selectedPiece.getPiece(),selected.getPiece());
				moves+=1;

				reset();							 
			}else if(selected.isSelected&&readytoDropPiece) {
				
				dropAdd(selectedPiece.getPiece(),selected.getPiece());
				moves +=1;
				reset();
			}
			else if((!selected.isSelected)) {
				reset();
				}
			readytoDropPiece=false;
			return;
		}
		if((e.getClickCount()==2&&moves<2)) {
			e.consume();
			selected.promote();
			selected.notSelected(playerturn);
			moves+=1;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {

		
	}

	@Override
	public void mouseExited(MouseEvent e) {

		
	}

	@Override
	public void selectFromHand(Piece select) {
		if(moves>0) {return;}
		reset();
		selectedPiece = new Tile(select);
		ArrayList<Integer[]> dropPositions = select.calculatePossibleDropPositions(gameboard);
		for(Integer[] i:dropPositions) {
			tileBoard[i[0]][i[1]].canBeSelected();
		}
		readytoDropPiece=true;
	}
	
}
