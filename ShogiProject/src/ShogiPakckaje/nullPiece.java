package ShogiPakckaje;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
//placeholder for emptyspots in the gameboard
//in reality movement is done by either:
//removing a real piece and adding the moved real piece and putting a fictitious piece in place of the moved piece
//adding the real piece and putting a fictitious piece where the real one was
//its fu**ing jank
public class nullPiece extends Piece {
	
	public nullPiece(){  this.player = 0;}
	
	public ArrayList<Integer[]> calculatePosiblePositions(Piece[][] gameboard){
		return null;
	}
	public boolean isKingPiece() {return false;}
	
	
	public BufferedImage getimage() throws IOException {
		return ImageIO.read(new File("360_F_503228811_52rvm1eaIpXxVQ9TaTNV1USeU4rZDR6y.jpg"));
	}

	@Override
	public ArrayList<Integer[]> promotionRules(Piece[][] gameboard) {
		// TODO Auto-generated method stub
		return  null;
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

	@Override
	public boolean isSafe(Piece[][] gameboard) {
		// TODO Auto-generated method stub
		return false;
	}
}
