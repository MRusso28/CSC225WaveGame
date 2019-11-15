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
    private int sizeX = 16;
    private int sizeY = 16;

    private int ticksToExplode;
    private int explosionSize;

    private boolean isGrowing = true;

    public PlayerBomb (double x, double y, ID id, Handler handler, int _explosionSize) {
        super(x, y, id);
        this.handler = handler;
        explosionSize = _explosionSize;
    }

    public void tick() {
        ticksToExplode--;
        if (ticksToExplode <= 0) {
            explode();
        }

        if (this.y >= Game.HEIGHT)
            handler.removeObject(this);

    }

    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.drawRect((int) x, (int) y, this.sizeX, this.sizeY);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) this.x, (int) this.y, this.sizeX, this.sizeY);
    }

    public void explode() {
        if (isGrowing) {
            sizeX += (int) (explosionSize / 25);
            x -= (int) (explosionSize / 50);
            sizeY += (int) (explosionSize / 25);
            y -= (int) (explosionSize / 50);
        } else {
            sizeX -= (int) (explosionSize / 25);
            x += (int) (explosionSize / 50);
            sizeY -= (int) (explosionSize / 25);
            y += (int) (explosionSize / 50);
        }

        if (this.sizeX >= explosionSize) {
            isGrowing = false;
        }

        if (this.sizeX <= 0) {
            handler.removeObject(this);
        }
    }

}
