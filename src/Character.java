/*
 * Author: Delsin Zhang
 * Created on 08/27/2016.
 */

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;

public class Character {
    Image model, rightImg, leftImg;
    /* Character position relative to the map, in pixels */
    double x, y;
    double speed;

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }


}
