package ShogiPakckaje;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Tile extends JLabel  {
	private Piece tilePiece;
	public boolean isSelected;
	private boolean canbePromoted;
	
	
	public Tile(Piece piece) {
		tilePiece = piece;
		setPreferredSize(new Dimension(109, 109));

		try {
			setIcon(new ImageIcon(tilePiece.getimage()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
		isSelected = false;
		canbePromoted = false;
		
	}

	public void canBeSelected() {
		isSelected = true;
		setBorder(BorderFactory.createLineBorder(Color.red,3));
	}
	
	public void notSelected(int player) {
		isSelected = false;
		setBorder(BorderFactory.createLineBorder(Color.black,1));
		if(this.canbePromoted && tilePiece.player ==player) {
			if(!tilePiece.isPromoted) {
				setBorder(BorderFactory.createLineBorder(Color.blue,3));	
			}
		}
		if(tilePiece.player!=player) {
			canbePromoted=false;
		}
	}
	
//	public void visualcanbePromoted(int playerturn) {
//		if(canbePromoted && tilePiece.player ==playerturn) {
//			setBorder(BorderFactory.createLineBorder(Color.blue,3));	
//		}
//	}
	
	public Piece getPiece() {
		return tilePiece;
	}
	
	public void changePiece(Piece changeInto) {
		tilePiece = changeInto;
		try {
			setIcon(new ImageIcon(tilePiece.getimage()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setcanBePromoted(boolean canbePromoted) {
		this.canbePromoted = canbePromoted;
		
	}
	
	public boolean getcanbePromoted() {
		return canbePromoted;
	}

	public void promote() {
		if(canbePromoted) {
			tilePiece.promote();
			try {
				setIcon(new ImageIcon(tilePiece.getimage()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			canbePromoted = false;
		}
	}
	
}
