package gameComponents;

import java.io.IOException;

public class BoundaryBlock extends IndestructibleObject {

  public BoundaryBlock( int x, int y, String rLocation, int tileW, int tileH ) throws IOException {
    super( x, y, rLocation, tileW, tileH );
  }

  @Override
  public void collisionCheck( TopBound topBlock ) {}

  @Override
  public void collisionCheck( RightBound rightBlock ) {}

  @Override
  public void collisionCheck( LeftBound leftBlock ) {}

  @Override
  public void collisionCheck( SolidColumnBlock columnBlock ) {}
}

