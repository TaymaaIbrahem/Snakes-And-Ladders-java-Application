package test;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import Utils.PlayerColor;
import Utils.PlayerObject;
import controller.PlayerController;
import model.Player;

public class PlayerMoveMethod {

	@Test
	public void test() {
		PlayerController pl = PlayerController.getInstance();
		int[] current = {0,2};int[] target = {1,2};
		Player player = new Player("tayma",PlayerColor.Black,PlayerObject.girlWithGreenShirt,3, current);
		pl.movePlayer(10, target[0],target[1], player);
		
		assertEquals("result", 10, player.getCurrentTileNumber());
	}

}
