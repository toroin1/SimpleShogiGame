package ShogiPakckaje;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class goldGeneralPiece extends Piece {
	
	private  int[][] internelRules =  {{-1,-1},{0,-1},{1,-1},{-1,0},{1,0},{0,1}};
	
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
	public boolean isKingPiece() {return false;}
	
	
	public BufferedImage getimage() throws IOException {
		switch(this.player) {
		case 1:
			return ImageIO.read(new File("Shogi_kinsho.png"));
		case-1:
			return ImageIO.read(new File("Shogi_kinsho_enemy.png"));
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
		ArrayList<Integer[]> list = new ArrayList<Integer[]>();
		for(int i =0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(gameboard[j][i].player!=0) {continue;}
				Integer[] posibleMove = {j,i};
				list.add(posibleMove);
			}
		}
		return list;
	}
	@Override
	public boolean isPawnPiece() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isSafe(Piece[][] gameboard) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
