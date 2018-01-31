package gameComponents;

import java.awt.*;
import java.io.IOException;

public class DestructibleObject extends GameObject {
  int pointValue;
  boolean isSplitBlock;
  boolean isLifeBlock;

  public DestructibleObject( int x, int y, String rLocation, int tileW, int tileH, int pointValue, boolean isStrip ) throws IOException {
    super( x, y, rLocation, tileW, tileH, isStrip, false );
    hitbox = new Rectangle( this.x, this.y, this.getWidth(), this.getHeight() );
    this.pointValue = pointValue;
    isSplitBlock = false;
    isLifeBlock = false;
  }

  public boolean isSplitBlock( ) {
    return this.isSplitBlock;
  }

  public boolean isLifeBlock( ) {
    return this.isLifeBlock;
  }

  public void setSplitBlock( ) {
    this.isSplitBlock = true;
  }

  public void setLifeBlock( ) { this.isLifeBlock = true; }

  @Override
  public void collisionCheck( Pop player ) {
    this.isShowing = false;
  }

  @Override
  public void collisionCheck( Katch someKatch ) {}

  @Override
  public void collisionCheck( IndestructibleObject indestructibleObject ) {}

  @Override
  public void collisionCheck( DestructibleObject destructibleObject ) {}

  @Override
  public void collisionCheck( TopBound topBlock ) {}

  @Override
  public void collisionCheck( RightBound rightBlock ) {}

  @Override
  public void collisionCheck( LeftBound leftBlock ) {}

  @Override
  public void collisionCheck( SolidColumnBlock columnBlock ) {}

  public int getPointValue( ) {
    return this.pointValue;
  }
}

