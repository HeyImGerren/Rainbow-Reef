package gameComponents;

import java.io.IOException;

public class Squid extends DestructibleObject {

  public Squid( int x, int y, String rLocation, int tileW, int tileH, int pointValue ) throws IOException {
    super( x, y, rLocation, tileW, tileH, pointValue, true );
  }
}
