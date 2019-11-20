package mainGame;

import java.awt.*;
import java.util.ArrayList;

/**
 * The main Heads Up Display of the game
 *
 * @author Brandon Loehle 5/30/16
 */

public class HUD {
    public double health = 100;
    private double healthMax = 100;
    private double greenValue = 255;
    private double energy = 50;
    private double energyMax = 50;
    private int score = 00000000000;
    private int level = 0;
    private boolean regen = false;
    private int timer = 10;
    private int energyTimer = 60;
    private int healthBarWidth = 400;
    private double healthBarModifier = 2.5;
    private boolean doubleHealth = false;
    private String ability = "";
    private int abilityUses = 0;
    private Color scoreColor = Color.white;
    private Color freezeColor = new Color(0, 255, 255, 25);
    private Color regenColor = new Color(120, 255, 120);
    private int extraLives = 0;
    private double randnumber;
    private String highScoreString = "";
    private double costMultipier = 1.25;
    private double cost = 500;
    private double activeCost = 3000;
    private int numFreeze = 0;
    private int numRegen = 0;
    private int numHealth = 0;
    private int numSpeed = 0;
    private int numShrink = 0;
    private int numArmor = 0;
    private int numClear = 0;
    private int passiveMax = 3;
    private double regenValue = 0;
    private double energyRegenValue = 1;
    private ArrayList<String> leaderboard;
    private ArrayList<String> savedLeaderboard;

    public int getPassiveMax() {
        return passiveMax;
    }

    public void setPassiveMax(int passiveMax) {
        this.passiveMax = passiveMax;
    }

    public int getNumClear() {
        return numClear;
    }

    public void setNumClear() {
        this.numClear += 1;
    }

    public double getRegenValue() {
        return regenValue;
    }

    public void setRegenValue() {
        this.regenValue += .25;
    }

    public double getEnergyRegenValue() {
        return energyRegenValue;
    }

    public void setEnergyRegenValue(double newValue) {
        this.energyRegenValue = newValue;
    }

    public int getNumFreeze() {
        return numFreeze;
    }

    public void setNumFreeze() {
        this.numFreeze += 1;
    }

    public int getNumRegen() {
        return numRegen;
    }

    public void setNumRegen() {
        this.numRegen += 1;
    }

    public int getNumHealth() {
        return numHealth;
    }

    public void setNumHealth() {
        this.numHealth += 1;
    }

    public int getNumSpeed() {
        return numSpeed;
    }

    public void setNumSpeed() {
        this.numSpeed += 1;
    }

    public int getNumShrink() {
        return numShrink;
    }

    public void setNumShrink() {
        this.numShrink += 1;
    }

    public int getNumArmor() {
        return numArmor;
    }

    public void setNumArmor() {
        this.numArmor += 1;
    }

    public double getCostMultipier() {
        return costMultipier;
    }

    public void setCostMultipier(double costMultipier) {
        this.costMultipier = costMultipier;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getActiveCost() {
        return activeCost;
    }

    public void setActiveCost(double a) {
        this.activeCost = a;
    }

    public int getEnergyTimer() {
        return energyTimer;
    }

    public void setEnergyTimer(int energyTimer) {
        this.energyTimer = energyTimer;
    }

    public void tick() {
        //health = Game.clamp(health, 0, health);
        //health = Game.clamp(health, 0, healthMax);
        greenValue = Game.clamp(greenValue, 0, 255);
        greenValue = health * healthBarModifier;

        energy = Game.clamp(energy, 0, energy);
        energy = Game.clamp(energy, 0, energyMax);

        //each tick generate a random # and if that random number equals a specidied #, draw a coin

        if (regen) {// regenerates health if that ability has been unlocked
            timer--;
            if (timer == 0) {
                health += this.getRegenValue();
                timer = 10;
            }
            health = Game.clamp(health, 0, healthMax);
        }

        //Regen energy
        energyTimer--;
        if (energyTimer == 0) {
            energy += this.energyRegenValue;
            energyTimer = 10;
        }
        energy = Game.clamp(energy, 0, energyMax);

    }

    public void reset() {
        health = 100;
        greenValue = 255;
        energy = 50;
        healthBarModifier = 2;
    }

    public void render(Graphics g) {


        Font font = new Font("Amoebic", 1, 30);
        g.setColor(Color.GRAY);
        g.fillRect(15, 15, healthBarWidth, 0);
        g.setColor(new Color(75, (int) greenValue, 0, 230));
        g.fillRect(15, 15, (int) health * 4, 64);
        if (regen && health < healthMax)
            g.setColor(regenColor);
        else
            g.setColor(scoreColor);
        g.drawRect(15, 15, healthBarWidth, 64);
        if (Handler.getFreeze()) {
            g.setColor(Color.GRAY);
            g.fillRect(1560, 20, 300, 30);
            g.setColor(Color.CYAN);
            g.fillRect(1560, 20, Handler.getTimer(), 30);
            g.setColor(scoreColor);
            g.drawRect(1560, 20, 300, 30);
            g.setColor(freezeColor);
            g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        }


        g.setColor(new Color(255, 255, 255, 200));
        g.fillRect(15, 80, (int) energy * 4, 16);

        g.setFont(font);
        g.setColor(scoreColor);
        g.drawString("Score: " + score, 15, 150);
        g.drawString("Level: " + level, 15, 185);
        g.drawString("Extra Lives: " + extraLives, 15, 220);

        if (this.highScoreString != null) {
            g.drawString("High Score:", 15, 950);
            if (this.getLeaderboard().size() == 0) {
                g.drawString("0", 15, 1000);
            } else {
                g.drawString(this.getHighScore(), 15, 1000);
            }
        }

        if (ability.equals("freezeTime")) {
            g.drawString("Time Freezes: " + abilityUses, Game.WIDTH - 300, 64);
        } else if (ability.equals("clearScreen")) {
            g.drawString("Screen Clears: " + abilityUses, Game.WIDTH - 300, 64);
        } else if (ability.equals("levelSkip")) {
            g.drawString("Level Skips: " + abilityUses, Game.WIDTH - 300, 64);
        }
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public int getAbilityUses() {
        return this.abilityUses;
    }

    public void setAbilityUses(int abilityUses) {
        this.abilityUses += abilityUses;
    }

    public void updateScoreColor(Color color) {
        this.scoreColor = color;
    }

    public void resetScore() {
        this.score = 00000000000;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setRegen() {
        regen = true;
    }

    public void resetRegen() {
        regen = false;
    }

    public int getExtraLives() {
        return this.extraLives;
    }

    public void setExtraLives(int lives) {
        this.extraLives = lives;
    }

    public void healthIncrease() {
        doubleHealth = true;
        healthMax = healthMax + 50;
        this.health = healthMax;
        healthBarModifier = (250 / healthMax);
        healthBarWidth = 4 * (int) healthMax;
    }

    public void resetHealth() {
        doubleHealth = false;
        healthMax = 100;
        this.health = healthMax;
        healthBarModifier = 2.5;
        healthBarWidth = 400;
    }

    public void restoreHealth() {
        this.health = healthMax;
    }

    public String getHighScore() {
        return highScoreString;
    }

    public void setHighScore(String data) {

        String topLeaderboard = leaderboard.get(0);

        this.highScoreString = topLeaderboard;

    }

    public void setLeaderboard() {
        leaderboard = new ArrayList<String>();
    }

    public void addLeaderboard(String data) {
        leaderboard.add(data);
    }

    public ArrayList<String> getLeaderboard() {
        return leaderboard;
    }

    public void saveLeaderboard() {
        savedLeaderboard = leaderboard;
    }

    public ArrayList<String> loadLeaderboard() {
        return savedLeaderboard;
    }

}
