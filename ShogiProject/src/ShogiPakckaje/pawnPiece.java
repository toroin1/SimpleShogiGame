package ShogiPakckaje;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class pawnPiece extends Piece {
	
	private BufferedImage ally;
	private BufferedImage enemy;
	private  int[][] internelRules =  {{0,-1}};
	private int[][] promotionInternelRules = {{-1,-1},{0,-1},{1,-1},{-1,0},{1,0},{0,1}};

	
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
				ally = ImageIO.read(new File("Shogi_fuhyo.png"));
				enemy = ImageIO.read(new File("Shogi_fuhyo_enemy.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				ally = ImageIO.read(new File("Shogi_tokin.png"));
				enemy = ImageIO.read(new File("Shogi_tokin_enemy.png"));
			} catch (IOException e) {

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
		switch(this.player) {
		case 1:
			for(int i = 0;i<9;i++) {
				ArrayList<Integer[]> column = new ArrayList<Integer[]>();
				for(int j = 8;j>0;j--) {
					if(gameboard[i][j].player==1&&gameboard[i][j].isPawnPiece()) {
						if(!gameboard[i][j].isPromoted) {
							column.clear();
							break;
						}else {
							continue;
						}
					}
					if(gameboard[i][j].player!=0) {
						continue;
					}
					if(gameboard[i][j-1].player==-1&&gameboard[i][j-1].isKingPiece()) {
						if(!(gameboard[i][j-1].isSafe(gameboard))) {
							continue;
						}
					}
					Integer[] possiblemove = {i,j};
					column.add(possiblemove);
				}
				list.addAll(column);
			}
			return list;
		case-1:
			for(int i = 0;i<9;i++) {
				ArrayList<Integer[]> column = new ArrayList<Integer[]>();
				for(int j = 0;j<8;j++) {
					if(gameboard[i][j].player==-1&&gameboard[i][j].isPawnPiece()) {
						if(!gameboard[i][j].isPromoted) {
							column.clear();
							break;
						}else {
							continue;
						}		
					}
					if(gameboard[i][j].player!=0) {
						continue;
					}
					if(gameboard[i][j+1].player==1&&gameboard[i][j+1].isKingPiece()) {
						if(!(gameboard[i][j+1].isSafe(gameboard))) {
							continue;
						}						
					}
					Integer[] possiblemove = {i,j};
					column.add(possiblemove);
				}
				list.addAll(column);
			}
			return list;
		default:
			return null;
		}
	}
	@Override
	public boolean isPawnPiece() {
		return true;
	}
	@Override
	public boolean isSafe(Piece[][] gameboard) {
		// TODO Auto-generated method stub
		return false;
	}
}
