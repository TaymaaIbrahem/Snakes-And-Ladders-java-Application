package model;

import Utils.PlayerColor;
import Interfaces.TileMessage;
import Utils.Direction;

public class SurpriseTile extends Tile implements TileMessage{

private Direction jumpingSide ;
	

	
	public SurpriseTile(int tileNumber, PlayerColor tileColor) {
	super(tileNumber, tileColor);
}


	public SurpriseTile(int tileNumber, PlayerColor tileColor,Direction jumpingSide) {
		super(tileNumber, tileColor);
		// TODO Auto-generated constructor stub
		
		this.jumpingSide = jumpingSide;
	}


	public Direction getJumpingSide() {
		return jumpingSide;
	}


	public void setJumpingSide(Direction jumpingSide) {
		this.jumpingSide = jumpingSide;
	}


	@Override
	public String toString() {
		return super.toString()+"SurpriseTile [jumpingSide=" + jumpingSide + "]";
	}


	@Override
	public String setTileMessage(String message) {	
		return "You will be moved 10 steps " +message+" !";
	}


	@Override
	public Boolean checkTileType(Tile tile) {
		// TODO Auto-generated method stub
		return tile instanceof SurpriseTile;
	}
	

	
	
}
