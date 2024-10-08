package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Utils.PlayerColor;
import Utils.PlayerObject;
import model.Player;

class PlayerRollNumber {
	
	//Test ID = 17
	@Test
	void testRollNumber() {
		Player p1 = new Player("player1", PlayerColor.Black, PlayerObject.BlondeWoman);
		p1.setRollNumber(5);
		assertTrue(Player.getRollNumber() == 1);
	}


}
