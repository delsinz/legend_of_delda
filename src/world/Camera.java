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
    /* Width & height of map, in pixels */
    private double mapWidth, mapHeight;

    protected Camera(double playerX, double playerY, double width, double height)
    throws SlickException
    {
        this.x = playerX - width/2;
        this.y = playerY - height/2;
        this.width = width;
        this.height = height;
    }

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

    protected void setMapWidth(double mapWidth)
    throws SlickException
    {
        this.mapWidth = mapWidth;
    }

    protected void setMapHeight(double mapHeight)
    throws SlickException
    {
        this.mapHeight = mapHeight;
    }

    protected void update(double playerX, double playerY, int delta)
    throws SlickException
    {
        this.follow(playerX, playerY, delta);
    }

    private void follow(double targetX, double targetY, int delta)
    throws SlickException
    {
        if(targetX >= this.width/2 && targetX <= this.mapWidth - this.width/2) { // To prevent camera from moving out of map
            this.x = targetX - this.width/2;
        }
        if(targetY >= this.height/2 && targetY <= this.mapHeight - this.height/2) {
            this.y = targetY - this.height/2;
        }
    }
}