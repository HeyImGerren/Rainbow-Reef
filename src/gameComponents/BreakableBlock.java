package gameComponents;

import java.io.IOException;

public class BreakableBlock extends DestructibleObject {

  public BreakableBlock( int x, int y, String rLocation, int tileW, int tileH, int pointValue ) throws IOException {
    super( x, y, rLocation, tileW, tileH, pointValue, false );
  }
}