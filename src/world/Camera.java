/* SWEN20003 Object Oriented Software Development
 * game.RPG Game Engine
 * Author: <Your name> <Your login>
 */

package world;

import org.newdawn.slick.SlickException;

/** Represents the camera that controls our viewpoint.
 */
public final class Camera
{
    /* The camera's position (top left of the screen) relative to the map, in pixels. */
    private double x, y;
    /* Width & height of the screen, in pixels */
    private double width, height;

    protected Camera(double playerX, double playerY, double width, double height)
    throws SlickException
    {
        this.x = playerX - width/2;
        this.y = playerY - height/2;
        this.width = width;
        this.height = height;
    }



    /* Made public to interact with World class to render map */
    public double getX()
    throws SlickException
    {
        return this.x;
    }

    public double getY()
    throws SlickException
    {
        return this.y;
    }

    protected double getWidth()
    throws SlickException
    {
        return this.width;
    }

    protected double getHeight()
    throws SlickException
    {
        return this.height;
    }

    protected void update(double playerX, double playerY, int delta)
    throws SlickException
    {
        this.follow(playerX, playerY, delta);
    }

    private void follow(double targetX, double targetY, int delta)
    throws SlickException
    {
        this.x = targetX - this.width/2;
        this.y = targetY - this.height/2;
    }
    
}