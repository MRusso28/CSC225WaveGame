package mainGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.json.JSONException;
import org.json.JSONObject;

public class Leaderboard {
	
	private Game game;
	private Handler handler;
	private HUD hud;
	private int timer;
	private Color retryColor;
	private String text;
	private int counter;

	public Leaderboard(Game game, Handler handler, HUD hud) throws MalformedURLException {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.retryColor = Color.white;
	}

	public void tick() {
		handler.clearPlayer();
	}

	public void render(Graphics g) {
		Font font = new Font("Amoebic", 1, 100);
		Font font2 = new Font("Amoebic", 1, 40);
		g.setFont(font);
		text = "Leaderboard:";
		g.drawString(text, Game.WIDTH / 2 - getTextWidth(font, text) / 2, Game.HEIGHT / 2 - 150);
		g.setFont(font2);
		
		ArrayList<String> leaderboard = hud.getLeaderboard();
		
		Collections.sort(leaderboard, new NumericalStringComparator().reversed());
		
		for (int i = 0; i < leaderboard.size(); i++){
			String newEntry = leaderboard.get(i);
			g.drawString(newEntry,Game.WIDTH / 2 - getTextWidth(font2,newEntry)/2, Game.HEIGHT/2 + (50*i));
		}
	}
	
	public class NumericalStringComparator implements Comparator<String> {
	    @Override
	    public int compare (String s1, String s2) {
	        int i1 = Integer.parseInt(s1.split(" ")[0]);
	        int i2 = Integer.parseInt(s2.split(" ")[0]);
	        int cmp = Integer.compare(i1, i2);
	        if (cmp != 0) {
	            return cmp;
	        }
	        return s1.compareTo(s2);
	    }
	}
	
	/**
	 * Function for getting the pixel width of text
	 * 
	 * @param font
	 *            the Font of the test
	 * @param text
	 *            the String of text
	 * @return width in pixels of text
	 */
	
	public int getTextWidth(Font font, String text) {
		AffineTransform at = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(at, true, true);
		int textWidth = (int) (font.getStringBounds(text, frc).getWidth());
		return textWidth;
	}

}
