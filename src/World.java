/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Your name> <Your login>
 */

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.lang.Math;

/**
 * Represents the entire game world.
 * (Designed to be instantiated just once for the whole game).
 */
public final class World {
    private TiledMap map;
    private Player player = new Player();

    /**
     * Create a new World object.
     */
    public World()
    throws SlickException
    {
        map = new TiledMap("assets/map.tmx", "assets/");
    }

    /**
     * Update the game state for a frame
     */
    public void update(int dirX, int dirY, int delta)
    throws SlickException
    {
        player.update(dirX, dirY, this, delta);
    }

    /**
     * Render the entire screen, so it reflects the current game state.
     *
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(Graphics g)
    throws SlickException
    {
        renderMap(g);
        player.render(g);
        displayDebugInfo(g);
    }

    private void renderMap(Graphics g)
    throws SlickException
    {
        double camX = player.getCamera().getX();
        double camY = player.getCamera().getY();

        /* Starting position for rendering, relative to map, in tiles */
        int tileX = (int) Math.floor((camX + 1) / map.getTileWidth());
        int tileY = (int) Math.floor((camY + 1) / map.getTileHeight());
        /* Starting position for rendering, relative to screen, in pixels */
        double pixelX = camX - toTileX(camX) * map.getTileWidth();
        double pixelY = camY - toTileY(camY) * map.getTileHeight();
        /* Size of the rendered section, in tiles */
        int renderWidth = (int) Math.floor(player.getCamera().getWidth() / map.getTileWidth()) + 2;
        int renderHeight = (int) Math.floor(player.getCamera().getHeight() / map.getTileHeight()) + 2;
        map.render((int) -pixelX, (int) -pixelY, tileX, tileY, renderWidth, renderHeight);
    }

    /**
     * Takes x pos in pixels, returns x pos in tiles
     */
    private int toTileX(double pixelX)
    throws SlickException
    {
        return (int) Math.floor((pixelX + 1) / map.getTileWidth());
    }

    /**
     * Takes y pos in pixels, returns y pos in tiles
     */
    private int toTileY(double pixelY)
    throws SlickException
    {
        return (int) Math.floor((pixelY + 1) / map.getTileHeight());
    }

    /**
     * Get friction at (x, playerY)
     * i.e. whether (x, playerY) blocks player
     */
    public int xFrictionAt(double x)
    throws SlickException
    {
        if (x < 0 || x > map.getWidth() * map.getTileWidth() - 2) {
            return 1;
        }

        if (map.getTileProperty(map.getTileId(toTileX(x), toTileY(player.getY()), 0), "block", "0").equals("1")) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Get friction at (playerX, y)
     * i.e. whether (playerX, y) blocks player
     */
    public int yFrictionAt(double y)
    throws SlickException
    {
        if (y < 0 || y > map.getHeight() * map.getTileHeight() - 2) { // Don't know why, but -1 has issues.
            return 1;
        }

        if (map.getTileProperty(map.getTileId(toTileX(player.getX()), toTileY(y), 0), "block", "0").equals("1")) {
            return 1;
        } else {
            return 0;
        }
    }

    private void displayDebugInfo(Graphics g)
    throws SlickException
    {
        g.drawString("blocking: " + map.getTileProperty(map.getTileId(toTileX(player.getX()), toTileY(player.getY()), 0), "block", "0"), 50, 50);
        g.drawString("x: " + player.getX() + ", t: " + player.getY(), 50, 65);
        g.drawString("tileX: " + toTileX(player.getX()) + ", tileY: " + toTileY(player.getY()), 50, 80);
        player.getModel().draw(50, 95);

    }
}
/*
* TODO: Character abstract class
* TODO: add Character class abstract
* TODO: Volume for character?
* TODO: pathfinding
* TODO: point and click navigation
* */
