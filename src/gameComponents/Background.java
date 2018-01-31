package gameComponents;

import java.io.IOException;

public class Background extends GameObject {

  public Background( int x, int y, String rLocation, int tileW, int tileH ) throws IOException {
    super( x, y, rLocation, tileW, tileH, false, false );
  }

  @Override
  public void collisionCheck( Pop somePop ) {}

  @Override
  public void collisionCheck( Katch someKatch ) {}

  @Override
  public void collisionCheck( IndestructibleObject indestructibleObject ) {}

  @Override
  public void collisionCheck( DestructibleObject destructible ) {}

  @Override
  public void collisionCheck( TopBound topBlock ) {}

  @Override
  public void collisionCheck( RightBound rightBlock ) {}

  @Override
  public void collisionCheck( LeftBound leftBlock ) {}

  @Override
  public void collisionCheck( SolidColumnBlock columnBlock ) {}
}

