/*
 * Author: Delsin Zhang
 * Created on 08/26/2016.
 */

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;

public class Player
{
    private static final double PLAYER_START_X = 756;
    private static final double PLAYER_START_Y = 684;
    private Image rightImg = new Image("assets/units/bloodborn.png");
    private Image leftImg = rightImg.getFlippedCopy(true, false);
    private Image model;
    private double x, y; // Player position relative to the map, in pixels.
    private double speed;
    private Camera camera;

    public Player()
    throws SlickException
    {
        this.model = rightImg;
        this.x = PLAYER_START_X;
        this.y = PLAYER_START_Y;
        this.speed = 1;
        camera = new Camera(this.x, this.y, RPG.SCREEN_WIDTH, RPG.SCREEN_HEIGHT);
    }

    public double getX()
    throws SlickException
    {
        return x;
    }

    public double getY()
    throws SlickException
    {
        return y;
    }

    public Image getModel()
    throws SlickException
    {
        return model;
    }

    public Camera getCamera()
    throws SlickException
    {
        return this.camera;
    }

    public void update(int dirX, int dirY, World world, int delta)
    throws SlickException
    {
        /* Update player's (x, y) pos */
        this.move(nextX(dirX, world), nextY(dirY, world), delta);
        /* Camera follows player */
        this.camera.update(this.x, this.y, delta);
    }

    public void render(Graphics g)
    throws SlickException
    {
        this.model.drawCentered((float)(x - camera.getX()), (float)(y - camera.getY()));
    }

    /** Move player by displacement (displaceX, displaceY) */
    private void move(double displaceX, double displaceY, int delta)
    throws SlickException
    {
        /* Update facing direction */
        if(displaceX > 0) {
            model = rightImg;
        } else if(displaceX < 0) {
            model = leftImg;
        }
        /* Move player model */
        this.x += displaceX;
        this.y += displaceY;
    }

    /** Get friction in player's x direction
     * i.e. check whether player can move in x direction */
    private int frictionX(int dirX, World world)
    throws SlickException {
        double velocityX = dirX * this.speed;
        double playerNextX = this.x + velocityX;
        return world.xFrictionAt(playerNextX);
    }

    /** Get friction in player's y direction
     * i.e. check whether player can move in y direction */
    private int frictionY(int dirY, World world)
    throws SlickException {
        double velocityY = dirY * this.speed;
        double playerNextY = this.y + velocityY;
        return world.yFrictionAt(playerNextY);
    }

    /** Computes player's displacement in x direction */
    private double nextX(int dirX, World world)
    throws SlickException {
        return (Math.abs(dirX) - frictionX(dirX, world)) * dirX * speed;
    }

    /** Computes player's displacement in y direction */
    private double nextY(int dirY, World world)
    throws SlickException {
        return (Math.abs(dirY) - frictionY(dirY, world)) * dirY * speed;
    }

}
