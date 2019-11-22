package mainGame;

import java.awt.*;
import java.util.Random;

/**
 * The bullets that the first boss shoots
 *
 * @author Brandon Loehle 5/30/16
 */

public class PlayerBomb extends GameObject {

    private Handler handler;
    private int sizeX = 32;
    private int sizeY = 32;

    public PlayerBomb (double x, double y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() {
        collision();
        if (this.y >= Game.HEIGHT)
            handler.removeObject(this);
    }

    public void render(Graphics g) {
        g.setColor(new Color(11,100,237));
        g.fillRect((int) x, (int) y, this.sizeX, this.sizeY);
        g.setColor(Color.white);
        g.drawRect((int) x, (int) y, this.sizeX, this.sizeY);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) this.x, (int) this.y, this.sizeX, this.sizeY);
    }

    public void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId().getType().equals("enemy")) {
                if (getBounds().intersects(tempObject.getBounds()) && tempInvincible == 0) {
                    handler.removeObject(this);
                    handler.addObject(new PlayerBombExplosion(this.x, this.y, ID.PlayerBombExplosion, handler, 300));
                }
            }
        }
    }

}
