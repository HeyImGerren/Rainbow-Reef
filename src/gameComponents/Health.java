package gameComponents;

import java.io.IOException;

public class Health extends DestructibleObject {
  public Health( int x, int y, String rLocation, int tileW, int tileH, int pointValue ) throws IOException {
    super( x, y, rLocation, tileW, tileH, pointValue, false );
  }
}