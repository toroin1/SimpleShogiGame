package ShogiPakckaje;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class lancePiece extends Piece {
	
	private  int[][] internelRules =  {{0,-1}};
	private int[][] promotionInternelRules = {{-1,-1},{0,-1},{1,-1},{-1,0},{1,0},{0,1}};
	private BufferedImage ally;
	private BufferedImage enemy;
	
	public ArrayList<Integer[]> calculatePosiblePositions(Piece[][] gameboard){
		if(isPromoted) {
			return promotionRules(gameboard);
		}
		ArrayList<Integer[]> list = new ArrayList<Integer[]>();
		for(int[] rules : internelRules) {
			int nextX = x;
			int nextY = y;
			while((nextY>=0&&nextY<=8)&&(nextX>=0&&nextX<=8)) {		
				System.out.println(nextX + ""+nextY);
				nextX = nextX+player*rules[0];
				nextY = nextY+player*rules[1];	
				if(((nextY<0||nextY>8)||(nextX<0||nextX>8))) {break;}			
				if(gameboard[nextX][nextY].player==this.player) {break;}			
				Integer[] possibleMoves = {nextX,nextY};				
				list.add(possibleMoves);	
				if(gameboard[nextX][nextY].player!=0) {break;}
			}
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
				ally = ImageIO.read(new File("Shogi_kyosha.png"));
				enemy = ImageIO.read(new File("Shogi_kyosha_enemy.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				ally = ImageIO.read(new File("Shogi_narikyo.png"));
				enemy = ImageIO.read(new File("Shogi_narikyo_enemy.png"));
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
			for(int i = 8;i>0;i--) {
				for(int j = 0;j<9;j++) {
					if(gameboard[j][i].player!=0) {continue;}
					Integer[] possiblemove = {j,i};
					list.add(possiblemove);
				}
			}
			return list;
		case-1:
			for(int i = 0;i<8;i++) {
				for(int j = 0;j<9;j++) {
					if(gameboard[j][i].player!=0) {continue;}
					Integer[] possiblemove = {j,i};
					list.add(possiblemove);
				}
			}
			return list;
		default:
			return null;
		}
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
