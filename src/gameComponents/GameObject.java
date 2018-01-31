package gameComponents;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;

public abstract class GameObject {
  protected int x;
  protected int y;
  protected int currentFrame;
  protected boolean isShowing = true;
  protected Rectangle hitbox;
  protected ImageObserver observer;
  Sprite thisSprite;

  public GameObject( int x, int y, String rLocation, int tileW, int tileH, boolean isStrip, boolean isKatch ) throws IOException {
    this.x = x;
    this.y = y;
    this.currentFrame = 0;
    thisSprite = new Sprite( rLocation, tileW, tileH, isStrip );
  }

  public void setX( int x ) {
    this.x = x;
  }

  public int getX( ) {
    return this.x;
  }

  public void setY( int y ) {
    this.y = y;
  }

  public int getY( ) {
    return this.y;
  }

  public int getWidth( ) { return this.thisSprite.getFrame( currentFrame ).getWidth(); }

  public int getHeight( ) { return this.thisSprite.getFrame( currentFrame ).getHeight(); }

  public Rectangle getHitbox( ) { return this.hitbox; }

  public void updateHitbox( int x, int y ) {
    this.hitbox.setLocation( x, y );
  }

  public void repaint( Graphics graphics ) { graphics.drawImage( thisSprite.getFrame( currentFrame ), x, y, observer ); }

  public void setFrame( int frame ) {
    this.currentFrame = frame;
  }

  public boolean isShowing( ) {
    return isShowing;
  }

  public abstract void collisionCheck( Pop somePop );

  public abstract void collisionCheck( Katch someKatch );

  public abstract void collisionCheck( IndestructibleObject indestructibleObject );

  public abstract void collisionCheck( DestructibleObject destructible );

  public abstract void collisionCheck( TopBound topBlock );

  public abstract void collisionCheck( RightBound rightBlock );

  public abstract void collisionCheck( LeftBound leftBlock );

  public abstract void collisionCheck( SolidColumnBlock columnBlock );
}