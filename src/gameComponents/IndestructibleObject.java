package gameComponents;

import java.awt.*;
import java.io.IOException;

public abstract class IndestructibleObject extends GameObject {

  public IndestructibleObject( int x, int y, String rLocation, int tileW, int tileH ) throws IOException {
    super( x, y, rLocation, tileW, tileH, false, false );
    hitbox = new Rectangle( this.x, this.y, this.getWidth(), this.getHeight() );
  }

  @Override
  public void collisionCheck( Pop player ) {}

  @Override
  public void collisionCheck( Katch someKatch ) {}

  @Override
  public void collisionCheck( IndestructibleObject indestructibleObject ) {}

  @Override
  public void collisionCheck( DestructibleObject destructible ) {}
}
