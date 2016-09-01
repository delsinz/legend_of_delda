/*
 * Author: Delsin Zhang
 * Created on 08/27/2016.
 */

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;

public abstract class Character {
    Image model, rightImg, leftImg;
    double x, y; // Character's position relative to the map, in pixels.
    double speed;

    public abstract double getX();
    public abstract double getY();
    public abstract Image getModel();

    public abstract void move(double displaceX, double displaceY, int delta);
    public abstract int frictionX(int dirX, World world);
    public abstract int frictionY(int dirY, World world);
    public abstract double nextX(int dirX, World world);
    public abstract double nextY(int dirY, World world);
}
