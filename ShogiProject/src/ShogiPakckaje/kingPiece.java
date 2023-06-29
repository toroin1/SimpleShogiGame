package ShogiPakckaje;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class kingPiece extends Piece {
	
	private  int[][] internelRules =  {{-1,-1},{0,-1},{1,-1},{-1,0},{1,0},{-1,1},{1,1},{0,1}};
	
	
	
	public ArrayList<Integer[]> calculatePosiblePositions(Piece[][] gameboard){
		ArrayList<Integer[]> list = new ArrayList<Integer[]>();
		for(int[] rules : internelRules) {
			Integer[] posibleMoves = {
					player*rules[0]+x,
					player*rules[1]+y
			};
			if(!(posibleMoves[0]>=0&&posibleMoves[1]>=0)) {continue;}		
			if(!(posibleMoves[0]<9&&posibleMoves[1]<9)) {continue;}
			if(gameboard[posibleMoves[0]][posibleMoves[1]].player==this.player) {continue;}		
			list.add(posibleMoves);		
		}
		return list;
	}
	public boolean isKingPiece() {return true;}
	

	
	public BufferedImage getimage() throws IOException {

		switch(this.player) {
			case 1:				
				return ImageIO.read(new File("Shogi_gyokusho.png"));
			case-1:
				return ImageIO.read(new File("Shogi_gyokusho_enemy.png"));
			default:
				return null;
		}
	}
	@Override
	public ArrayList<Integer[]> promotionRules(Piece[][] gameboard) {
		return null;
		
	}
	@Override
	public boolean canBePromoted() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void promotionImage() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void promote() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void unpromote() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ArrayList<Integer[]> calculatePossibleDropPositions(Piece[][] gameboard) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isPawnPiece() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean isSafe(Piece[][] gameboard) {
		boolean issafe = false;
		int[][] safemoves = {{-1,-1},{1,-1},{-1,0},{1,0},{-1,1},{1,1},{0,1}};
		for(int[] rules : safemoves) {
			Integer[] posibleMoves = {
					player*rules[0]+x,
					player*rules[1]+y
			};
			if(!(posibleMoves[0]>=0&&posibleMoves[1]>=0)) {continue;}		
			if(!(posibleMoves[0]<9&&posibleMoves[1]<9)) {continue;}
			if(gameboard[posibleMoves[0]][posibleMoves[1]].player==0) {
				issafe = true;
			}
		}
		return issafe;
	}
}
