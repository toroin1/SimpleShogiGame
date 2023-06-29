package ShogiPakckaje;

public interface ConnectionWithGameBoard {
	public void selectFromHand(Piece select);
	public int getTurn();
	public void sethand1(ConnectionWithHand hand1);
	public void sethand2(ConnectionWithHand hand2);
}
