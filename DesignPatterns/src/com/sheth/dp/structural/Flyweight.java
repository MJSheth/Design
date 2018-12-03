package com.sheth.dp.structural;

import java.util.HashMap;
import java.util.Random;

/**
 * 
 * It is used when we want to limit the number of objects (similar- part of the
 * internal state is common(intrinsic state) whereas other part (extrinsic
 * state) can vary) created to improve the performance.
 * 
 * Flyweight objects are Immutable
 * 
 * Flyweight: Provides an interface through which Flyweights can receive and act
 * on extrinsic state
 * 
 * ConcreteFlyweight: Implements Flyweight interface and stores intrinsic
 * values. ConcreteFlyweight object must be sharable. It must be able to
 * maintain state(final intrinsic attributes Task and PlayerType) that is
 * intrinsic to it and manipulate state(weapon) that is extrinsic to it
 * 
 * FlyweightFactory: It creates and manages Flyweight objects. It also ensures
 * sharing of Flyweight objects
 * 
 * Client: It maintains references to Flyweights in addition to computing and
 * maintaining extrinsic state
 */

// Client
public class Flyweight {
	public static void main(String args[]) {

		// Total 10 players sharing two types of Player objects with different
		// weapons
		for (int i = 0; i < 10; i++) {
			Player p = PlayerFactory.getPlayer(getRandPlayerType());
			p.assignWeapon(getRandWeapon());
			p.mission();
		}
	}

	public static PlayerType getRandPlayerType() {
		Random r = new Random();
		int randInt = r.nextInt(PlayerType.values().length);
		return PlayerType.values()[randInt];
	}

	public static Weapon getRandWeapon() {
		Random r = new Random();
		int randInt = r.nextInt(Weapon.values().length);
		return Weapon.values()[randInt];
	}
}

// Flyweight Interface
interface Player {
	public void assignWeapon(Weapon weapon);

	public void mission();
}

enum Weapon {
	AK_47, MAVERICK, GUT_KNIFE, DESERT_EAGLE
}

enum PlayerType {
	TERRORIST, COUNTER_TERRORIST
}

enum Task {
	PLANT_A_BOMB, DEFUSE_A_BOMB
}

// ConcreteFlyweight
class Terrorist implements Player {
	// Intrinsic Attributes
	private final Task task;
	private final PlayerType playerType;
	// Extrinsic Attribute
	private Weapon weapon;

	public Terrorist() {
		playerType = PlayerType.TERRORIST;
		task = Task.PLANT_A_BOMB;
	}

	@Override
	public void assignWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	@Override
	public void mission() {
		System.out.println(playerType + " with weapon :" + weapon + " | Task is:" + task);
	}

}

// ConcreteFlyweight
class CounterTerrorist implements Player {
	// Intrinsic Attribute
	private final Task task;
	private final PlayerType playerType;
	// Extrinsic Attribute
	private Weapon weapon;

	public CounterTerrorist() {
		playerType = PlayerType.COUNTER_TERRORIST;
		task = Task.DEFUSE_A_BOMB;
	}

	@Override
	public void assignWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	@Override
	public void mission() {
		System.out.println(playerType + " with weapon :" + weapon + " | Task is:" + task);
	}
}

// FlyweightFactory (Create objects based on type)
class PlayerFactory {
	private static HashMap<PlayerType, Player> playersMap = new HashMap<PlayerType, Player>();

	public static Player getPlayer(PlayerType playerType) {
		Player player = null;
		if (playersMap.containsKey(playerType)) {
			player = playersMap.get(playerType);
		} else {
			switch (playerType) {
			case TERRORIST:
				player = new Terrorist();
				break;
			case COUNTER_TERRORIST:
				player = new CounterTerrorist();
				break;
			}
			playersMap.put(playerType, player);
		}
		return player;
	}
}
