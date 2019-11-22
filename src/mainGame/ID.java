package mainGame;

/**
 * Used to easily identify each game entity
 * @author Brandon Loehle
 * 5/30/16
 */
public enum ID {
	Player("player"),
	Trail("trail"),
	BossEye("enemy"),
	EnemyFast("enemy"),
	EnemySmart("enemy"),
	EnemyBoss("enemy"),
	EnemyBossBullet("enemy"),
	EnemyBurst("enemy"),
	EnemyBurstWarning("enemy"),
	EnemySweep("enemy"),
	EnemyShooter("enemy"),
	EnemyShooterBullet("enemy"),
	Firework("misc"),
	FireworkSpark("misc"),
	CircleTrail("trail"),
	PickupHealth("pickup"),
	PickupCoin("pickup"),
	Levels1to10Text("misc"),
	EnemyBasic("enemy"),
	PlayerBomb("ally"),
	PlayerBombExplosion("weapon");

	private final String type;

	ID (String objectType) {
		type = objectType;
	}

	public String getType () { return type; }
}
