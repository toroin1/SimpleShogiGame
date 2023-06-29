package ShogiPakckaje;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class silverGeneralPiece extends Piece {
	
	private  int[][] internelRules =  {{-1,-1},{0,-1},{1,-1},{-1,1},{1,1}};
	private int[][] promotionInternelRules = {{-1,-1},{0,-1},{1,-1},{-1,0},{1,0},{0,1}};
	private BufferedImage ally;
	private BufferedImage enemy;

	
	public ArrayList<Integer[]> calculatePosiblePositions(Piece[][] gameboard){
		if(isPromoted) {
			return promotionRules(gameboard);
		}
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
		promotionImage();
		switch(this.player) {
		case 1:
			return ally;
		case-1:
			return enemy;
		default:
			return null;
		}
	}
	@Override
	public ArrayList<Integer[]> promotionRules(Piece[][] gameboard) {
		ArrayList<Integer[]> list = new ArrayList<Integer[]>();
		for(int[] rules : promotionInternelRules) {
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
	@Override
	public boolean canBePromoted() {
		// TODO Auto-generated method stub
		if(!isPromoted) {
			switch(player) {
			case 1:
				if(y<=2) {
					return true;}
				break;
			case -1:
				if(y>=6) {
					return true;}
				break;
			}
		}
		return false;
	}
	@Override
	public void promotionImage() {
		if(!isPromoted) {
			try {
				ally = ImageIO.read(new File("Shogi_ginsho.png"));
				enemy = ImageIO.read(new File("Shogi_ginsho_enemy.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				ally = ImageIO.read(new File("Shogi_narigin.png"));
				enemy = ImageIO.read(new File("Shogi_narigin_enemy.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
	}
	@Override
	public void promote() {
		isPromoted = true;	
	}
	@Override
	public void unpromote() {
		isPromoted = false;
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
