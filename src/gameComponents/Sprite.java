package gameComponents;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sprite {
  int tileW;
  int tileH;
  String spriteFile;
  boolean isStrip;

  private BufferedImage[] images;
  BufferedImage image;

  public Sprite( String rLocation, int tileW, int tileH, boolean isStrip ) throws IOException {
    this.spriteFile = rLocation;
    this.tileW = tileW;
    this.tileH = tileH;
    this.isStrip = isStrip;

    this.image = ImageIO.read( getClass().getResource( rLocation ) );

    if ( isStrip ) {
      this.images = new BufferedImage[ image.getWidth() / tileW ];

      for ( int index = 0; index < this.images.length; index++ ) {
        this.images[ index ] = image.getSubimage( index * this.tileW, 0, this.tileW, this.tileH );
      }
    }
  }

  public BufferedImage getFrame( int frame ) {
    if ( isStrip ) {
      return this.images[ frame ];
    } else {
      return image;
    }
  }

  public int frameCount( ) {
    return this.images.length;
  }
}