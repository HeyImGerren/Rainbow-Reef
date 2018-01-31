package gameComponents;

import java.awt.*;
import java.io.IOException;

public class Pop extends GameObject {
  private Point safePoint;

  double velocityX;
  double velocityY;
  double playRateX;
  double playRateY;

  double accY;

  public Pop( int x, int y, String rLocation ) throws IOException {
    super( x, y, rLocation, 35, 35, true, false );
    safePoint = new Point( this.x, this.y );
    hitbox = new Rectangle( this.x, this.y, this.getWidth(), this.getHeight() );
    velocityX = 0;
    velocityY = 0;
    accY = .3;
    playRateY = 4;
    playRateX = 3;
  }

  public void updateSafePoint( int x, int y ) {
    this.safePoint = new Point( x, y );
  }

  public Point getSafePoint( ) {
    return this.safePoint;
  }

  public void updateP( ) {
    if ( this.currentFrame == 44 ) {
      this.setFrame( 0 );
    } else {
      this.currentFrame++;
    }
    velocityY += accY;
    setX( this.getX() + ( int ) velocityX );
    setY( this.getY() + ( int ) velocityY );
    updateHitbox( this.getX(), this.getY() );
    updateSafePoint( this.getX(), this.getY() );
  }

  @Override
  public void collisionCheck( Pop player ) {
  }

  @Override
  public void collisionCheck( Katch someKatch ) {
    if ( this.getHitbox().intersects( someKatch.getLeftHitbox() ) ) {
      this.setVelocityX( ( int ) playRateX * -1 );
      this.setVelocityY( ( int ) playRateY * -1 );
      if ( playRateY < 15 ) {
        playRateY += .5;
      }
    }
    if ( this.getHitbox().intersects( someKatch.getCenterHitbox() ) ) {
      this.setVelocityX( 0 );
      this.setVelocityY( ( int ) playRateY * -1 );
      if ( playRateY < 15 ) {
        playRateY += .5;
      }

    }
    if ( this.getHitbox().intersects( someKatch.getRightHitbox() ) ) {
      this.setVelocityX( ( int ) playRateX );

      this.setVelocityY( ( int ) playRateY * -1 );
      if ( playRateY < 15 ) {
        playRateY += .5;
      }
    }

  }

  @Override
  public void collisionCheck( IndestructibleObject indestructibleObject ) {
  }

  @Override
  public void collisionCheck( DestructibleObject destructible ) {
    this.setVelocityY( getVelocityY() * -1 );
  }

  @Override
  public void collisionCheck( TopBound topBlock ) {
    this.setVelocityY( 4 );
  }

  @Override
  public void collisionCheck( RightBound rightBlock ) { this.setVelocityX( -5 ); }

  @Override
  public void collisionCheck( LeftBound leftBlock ) {
    this.setVelocityX( 5 );
  }

  @Override
  public void collisionCheck( SolidColumnBlock columnBlock ) {
    if ( x - 35 <= columnBlock.getX() - 5 && x + 35 >= columnBlock.getX() + 5 ) {
      velocityY *= -1;
    }
    if ( x - 35 <= columnBlock.getX() ) {
      x -= 5;
      velocityX *= -1;
    }
    if ( x + 35 >= columnBlock.getX() ) {
      x += 5;
      velocityX *= -1;
    }
  }

  public void setVelocityY( int vel ) {
    this.velocityY = vel;
  }

  public void setVelocityX( int vel ) {
    this.velocityX = vel;
  }

  public int getVelocityX( ) {
    return ( int ) this.velocityX;
  }

  public int getVelocityY( ) {
    return ( int ) this.velocityY;
  }
}