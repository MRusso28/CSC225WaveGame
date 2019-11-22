package mainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * A type of enemy in the game
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class EnemySweep extends GameObject {

	private Handler handler;

	public EnemySweep(double x, double y, double velX, double velY, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.velX = velX;
		this.velY = velY;
	}

	public void tick() {
		this.x += velX;
		this.y += velY;

		// if (this.y <= 0 || this.y >= Game.HEIGHT - 43) velY *= -1;
		if (this.x <= 0 || this.x >= Game.WIDTH - 16)
			velX *= -1;

		handler.addObject(new Trail(x, y, ID.Trail, Color.cyan, 16, 16, 0.150, this.handler));

		collision();
		if (health <= 0) {
			handler.removeObject(this);
		}
	}

	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId().getType().equals("weapon")) {
				// collision code
				if (getBounds().intersects(tempObject.getBounds()) && tempInvincible == 0) {//hit by player's weapon
					health -= 1;
					tempInvincible = 15;
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect((int) x, (int) y, 16, 16);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, 16, 16);
	}

}
