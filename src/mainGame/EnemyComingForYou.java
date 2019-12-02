
	package mainGame;

	import java.awt.Color;
	import java.awt.Graphics;
	import java.awt.Rectangle;

	/**
	 * A type of enemy in the game
	 * 
	 * @author Jenna Saleh 11/19/19
	 *
	 */

	public class EnemyComingForYou extends GameObject {

		private Handler handler;

		public EnemyComingForYou(double x, double y, double velX, double velY, ID id, Handler handler) {
			super(x, y, id);
			this.handler = handler;
			this.velX = velX;
			this.velY = velY;
		}

		public void tick() {
			this.x += velX;
			this.y += velY;

			
			if (this.x <= 0 || this.x >= Game.WIDTH - 50)
				velX *= -1;

			handler.addObject(new Trail(x, y, ID.Trail, new Color(214, 145, 17), 50, 50, 0.050, this.handler));

		}

		public void render(Graphics g) {
			g.setColor(new Color(214, 145, 17));
			g.fillRect((int) x, (int) y, 50, 50);

		}

		@Override
		public Rectangle getBounds() {
			return new Rectangle((int) this.x, (int) this.y, 50, 50);
		}

	}


