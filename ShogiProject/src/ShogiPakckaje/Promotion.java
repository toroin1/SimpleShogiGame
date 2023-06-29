package ShogiPakckaje;

import java.util.ArrayList;

public interface Promotion {
	ArrayList<Integer[]> promotionRules(Piece[][] gameboard);
	boolean canBePromoted();
	void promotionImage();
	void promote();
	void unpromote();
}
