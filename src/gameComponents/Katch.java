package gameComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Katch extends GameObject implements KeyListener {
  protected Rectangle leftHitbox;
  protected Rectangle rightHitbox;
  protected Rectangle centerHitbox;
  private final int LEFT_ARROW = 37;
  private final int RIGHT_ARROW = 39;
  boolean movingLeft;
  boolean movingRight;

  public Katch( int x, int y, String rLocation ) throws IOException {
    super( x, y, rLocation, 80, 30, true, true );
    this.leftHitbox = new Rectangle( this.x, this.y, 35, this.getHeight() );
    this.centerHitbox = new Rectangle( this.x + 26, this.y, 26, this.getHeight() );
    this.rightHitbox = new Rectangle( this.x + 52, this.y, 28, this.getHeight() );
    movingLeft = false;
    movingRight = false;
  }

  @Override
  public void keyTyped( KeyEvent e ) {}

  @Override
  public void keyPressed( KeyEvent e ) {
    if ( e.getKeyCode() == LEFT_ARROW ) {
      movingLeft = true;
    } else if ( e.getKeyCode() == RIGHT_ARROW ) {
      movingRight = true;
    }
  }

  @Override
  public void keyReleased( KeyEvent e ) {
    if ( e.getKeyCode() == LEFT_ARROW ) {
      movingLeft = false;
    } else if ( e.getKeyCode() == RIGHT_ARROW ) {
      movingRight = false;
    }
  }


  public void updateP( ) {
    if ( movingLeft ) {
      this.setX( this.getX() - 10 );
      if ( this.currentFrame == 23 ) {
        this.setFrame( 0 );
      } else {
        this.setFrame( currentFrame + 1 );
      }
      if ( this.getX() <= 20 ) {
        this.setX( 20 );
      }
    }
    if ( movingRight ) {
      this.setX( this.getX() + 10 );
      if ( this.currentFrame == 0 ) {
        this.setFrame( 24 );
        this.setFrame( currentFrame - 1 );
      } else {
        this.setFrame( currentFrame - 1 );
      }
      if ( this.getX() >= 540 ) {
        this.setX( 540 );
      }
    }

    this.updateLeftHitbox( this.getX(), this.getY() );
    this.updateCenterHitbox( this.getX() + 40, this.getY() );
    this.updateRightHitbox( this.getX() + 52, this.getY() );
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

  public Rectangle getLeftHitbox( ) {
    return this.leftHitbox;
  }

  public Rectangle getCenterHitbox( ) {
    return this.centerHitbox;
  }

  public Rectangle getRightHitbox( ) {
    return this.rightHitbox;
  }

  public void updateLeftHitbox( int currentX, int currentY ) { this.leftHitbox.setLocation( currentX, currentY ); }

  public void updateCenterHitbox( int currentX, int currentY ) { this.centerHitbox.setLocation( currentX, currentY ); }

  public void updateRightHitbox( int currentX, int currentY ) { this.rightHitbox.setLocation( currentX, currentY ); }
}
