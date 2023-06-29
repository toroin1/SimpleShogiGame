package ShogiPakckaje;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Piece implements Promotion {
	protected int x;
	protected int y;
	protected int player;
	protected boolean isPromoted;
	
	public Piece() {	
		isPromoted = false;
	}
	
	public int getPositionX() {
		int position=x;
		return position;
	}
	public int getPositionY() {
		int position=y;
		return position;
	}
	
	public void setPosition(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public void setPlayer(int direction) {
		switch (direction) {
			case 1:{player = direction; return;}
			case -1:{player = direction; return;}
		}
	}
	
		public abstract ArrayList<Integer[]> calculatePosiblePositions(Piece[][] gameboard);

		
		public abstract boolean isKingPiece();
		
		public abstract boolean isPawnPiece();
		
		public void changePlayer() {
			player=player*(-1);
		}
		
		public abstract ArrayList<Integer[]> calculatePossibleDropPositions(Piece[][] gameboard);
		
		public abstract BufferedImage getimage() throws IOException;
		
		public abstract boolean isSafe(Piece[][] gameboard);
	}


