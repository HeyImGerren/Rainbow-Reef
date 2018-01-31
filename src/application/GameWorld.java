package application;

import gameComponents.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GameWorld extends JPanel implements Observer, Runnable, KeyListener {
  public static final int WIDTH = 631;
  public static final int HEIGHT = 465;

  public static final String levelPic = "/home/gerren/Desktop/Rainbow Reef/src/resources/RFlevel.txt";
  public static final String boundaryPic = "/resources/Wall.png";
  public static final String bgPic = "/resources/Background1.png";
  public static final String health = "/resources/Block_life.png";
  public static final String BluePic = "/resources/Block6.png";
  public static final String RedPic = "/resources/Block3.png";
  public static final String PurplePic = "/resources/Block1.png";
  public static final String YellowPic = "/resources/Block2.png";
  public static final String GreenPic = "/resources/Block4.png";
  public static final String WhitePic = "/resources/Block7.png";
  public static final String SolidPic = "/resources/Block_solid.png";
  public static final String SplitPic = "/resources/Block_split.png";
  public static final String squid = "/resources/Bigleg_small_strip24.png";
  public static final String katchPic = "/resources/Katch_strip24.png";
  public static final String popPic = "/resources/Pop_strip45.png";
  public static final String winScreen = "/resources/Congratulation.png";
  public static final String loseScreen = "/resources/gameover.png";
  public static final String livesIcon = "/resources/Katch_small.png";

  protected GameClock gameClock;
  protected Dimension dimension;
  protected Pop pop1;
  protected Katch katch;
  protected Background bg;
  protected Background winnerScreen;
  protected Background loserScreen;
  private int playerScore, playerLives, numberOfPopsShowing;
  private BufferedImage livesImage;
  protected char[][] levelArray;

  protected ArrayList<DestructibleObject> destructibleObjectList;
  protected ArrayList<TopBound> ceilingBlockList;
  protected ArrayList<LeftBound> leftBlockList;
  protected ArrayList<RightBound> rightBlockList;
  protected ArrayList<SolidColumnBlock> columnBlockList;
  protected ArrayList<Pop> popList;
  protected ArrayList<Squid> squidList;


  public GameWorld( ) throws IOException {
    playerScore = 0;
    playerLives = 5;
    numberOfPopsShowing = 1;
    this.livesImage = ImageIO.read( getClass().getResource( livesIcon ) );
    this.dimension = new Dimension( WIDTH, HEIGHT );
    this.gameClock = new GameClock();
    destructibleObjectList = new ArrayList<>();
    ceilingBlockList = new ArrayList<>();
    leftBlockList = new ArrayList<>();
    rightBlockList = new ArrayList<>();
    columnBlockList = new ArrayList<>();
    popList = new ArrayList<>();
    squidList = new ArrayList<>();
    this.gameClock.addObserver( this );
    this.levelArray = Level.parseLevelFile( levelPic );
    spawnObjects();
    this.bg = new Background( WIDTH, HEIGHT, bgPic, 640, 480 );
    this.winnerScreen = new Background( WIDTH, HEIGHT, winScreen, 640, 480 );
    this.loserScreen = new Background( 400, 300, loseScreen, 250, 79 );
    this.addKeyListener( katch );
    new Thread(  this.gameClock ).start();
    this.setFocusable( true );
  }

  public void spawnObjects( ) throws IOException {
    for ( int row = 0; row < 23; row++ ) {
      for ( int column = 0; column < 32; column++ ) {
        if ( levelArray[row][column] == 'X' ) {
          if ( column >= 0 && row == 0 ) {
            TopBound ceilingBox = new TopBound( column * 20, row * 20, boundaryPic, 20, 20 );
            ceilingBox.setX( column * 20 );
            ceilingBox.setY( row * 20 );
            this.ceilingBlockList.add( ceilingBox );
          } else if ( column == 0 && row >= 1 ) {
            LeftBound leftBoundaryBox = new LeftBound( column * 20, row * 20, boundaryPic, 20, 20 );
            leftBoundaryBox.setX( column * 20 );
            leftBoundaryBox.setY( row * 20 );
            this.leftBlockList.add( leftBoundaryBox );
          } else {
            RightBound rightBoundaryBox = new RightBound( column * 20, row * 20, boundaryPic, 20, 20 );
            rightBoundaryBox.setX( column * 20 );
            rightBoundaryBox.setY( row * 20 );
            this.rightBlockList.add( rightBoundaryBox );
          }
        }

        if ( levelArray[row][column] == '|' ) {
          SolidColumnBlock bound = new SolidColumnBlock( column * 20, row * 20, SolidPic, 40, 20 );
          bound.setX( column * 20 );
          bound.setY( row * 20 );
          this.columnBlockList.add( bound );
        }

        if ( levelArray[row][column] == 'H' ) {
          Health tempWall = new Health( column * 20, row * 20, health, 40, 20, 80 );
          tempWall.setX( column * 20 );
          tempWall.setY( row * 20 );
          tempWall.setLifeBlock();
          this.destructibleObjectList.add( tempWall );
        }

        if ( levelArray[row][column] == 'B' ) {
          BreakableBlock tempWall = new BreakableBlock( column * 20, row * 20, BluePic, 40, 20, 10 );
          tempWall.setX( column * 20 );
          tempWall.setY( row * 20 );
          this.destructibleObjectList.add( tempWall );
        }

        if ( levelArray[row][column] == 'R' ) {
          BreakableBlock tempWall = new BreakableBlock( column * 20, row * 20, RedPic, 40, 20, 20 );
          tempWall.setX( column * 20 );
          tempWall.setY( row * 20 );
          this.destructibleObjectList.add( tempWall );
        }

        if ( levelArray[row][column] == 'Y' ) {
          BreakableBlock tempWall = new BreakableBlock( column * 20, row * 20, YellowPic, 40, 20, 30 );
          tempWall.setX( column * 20 );
          tempWall.setY( row * 20 );
          this.destructibleObjectList.add( tempWall );
        }

        if ( levelArray[row][column] == 'G' ) {
          BreakableBlock tempWall = new BreakableBlock( column * 20, row * 20, GreenPic, 40, 20, 40 );
          tempWall.setX( column * 20 );
          tempWall.setY( row * 20 );
          this.destructibleObjectList.add( tempWall );
        }

        if ( levelArray[row][column] == 'P' ) {
          BreakableBlock tempWall = new BreakableBlock( column * 20, row * 20, PurplePic, 40, 20, 50 );
          tempWall.setX( column * 20 );
          tempWall.setY( row * 20 );
          this.destructibleObjectList.add( tempWall );
        }

        if ( levelArray[row][column] == 'W' ) {
          BreakableBlock tempWall = new BreakableBlock( column * 20, row * 20, WhitePic, 40, 20, 60 );
          tempWall.setX( column * 20 );
          tempWall.setY( row * 20 );
          this.destructibleObjectList.add( tempWall );
        }

        if ( levelArray[row][column] == 'S' ) {
          SplitBlock tempWall = new SplitBlock( column * 20, row * 20, SplitPic, 40, 20, 70 );
          tempWall.setX( column * 20 );
          tempWall.setY( row * 20 );
          tempWall.setSplitBlock();
          this.destructibleObjectList.add( tempWall );
        }

        if ( levelArray[row][column] == 'A' ) {
          Squid tempObject = new Squid( column * 20, row * 20, squid, 40, 40, 100 );
          tempObject.setX( column * 20 );
          tempObject.setY( row * 20 );
          this.squidList.add( tempObject );
        }
      }
    }

    katch = new Katch( 280, 410, katchPic );
    pop1 = new Pop( 310, 260, popPic );
    popList.add( pop1 );
  }

  public Dimension getDimension( ) {
    return this.dimension;
  }

  @Override
  public void update( Observable o, Object arg ) {
    try {
      checkPopStatus();

      for ( int currentPop = 0; currentPop < popList.size(); currentPop++ ) {
        popList.get( currentPop ).collisionCheck( katch );
        checkDestructibleObjectCollisions( popList.get( currentPop ) );
        checkIndestructibleObjectCollisions( popList.get( currentPop ) );
      }
    } catch ( IOException e ) {
      e.printStackTrace();
    }
    this.repaint();
    updatePopCoordinates();
  }

  @Override
  public void paintComponent( Graphics graphic ) {
    super.paintComponent( graphic );

    if ( playerLives > 0 && !squidList.isEmpty() ) {
      bg.setX( 0 );
      bg.setY( 0 );
      bg.repaint( graphic );

      int positionHolder = 30;
      for ( int numLives = 1; numLives <= playerLives; numLives++ ) {
        graphic.drawImage( this.livesImage, positionHolder, 450, null );
        positionHolder += 30;
      }

      graphic.setColor( Color.black );
      graphic.setFont( new Font( "ariel", Font.BOLD, 15 ) );
      graphic.drawString( "Score: " + getPlayerScore(), 530, 460 );

      for ( SolidColumnBlock columnBlocks : columnBlockList ) {
        columnBlocks.repaint( graphic );
      }

      for ( LeftBound leftBlock : leftBlockList ) {
        leftBlock.repaint( graphic );
      }

      for ( RightBound rightBlock : rightBlockList ) {
        rightBlock.repaint( graphic );
      }

      for ( TopBound topBlock : ceilingBlockList ) {
        topBlock.repaint( graphic );
      }

      for ( int currentWall = 0; currentWall < destructibleObjectList.size(); currentWall++ ) {
        if ( destructibleObjectList.get( currentWall ).isShowing() ) {
          destructibleObjectList.get( currentWall ).repaint( graphic );
        }
      }

      for ( Squid currentSquid : squidList ) {
        if ( currentSquid.isShowing() ) {
          currentSquid.repaint( graphic );
        }
      }

      katch.updateP();
      katch.repaint( graphic );

      for ( Pop popInPlay : popList ) {
        popInPlay.updateP();
        popInPlay.repaint( graphic );
      }
    } else if ( squidList.isEmpty() ) {
      winnerScreen.setX( 0 );
      winnerScreen.setY( 0 );
      winnerScreen.repaint( graphic );
    } else {
      graphic.setColor( Color.black );
      graphic.fillRect( 0, 0, WIDTH, HEIGHT );
      loserScreen.setX( 165 );
      loserScreen.setY( 150 );
      loserScreen.repaint( graphic );
      graphic.setColor( Color.white );
      graphic.setFont( new Font( "ariel", Font.BOLD, 25 ) );
      graphic.drawString( "Final score: " + getPlayerScore(), 190, 300 );
    }

    requestFocus();
  }


  @Override
  public void run( ) {
  }

  @Override
  public void keyTyped( KeyEvent e ) {
  }

  @Override
  public void keyPressed( KeyEvent e ) {
  }

  @Override
  public void keyReleased( KeyEvent e ) {
  }

  public void checkIndestructibleObjectCollisions( Pop currentPop ) {
    for ( SolidColumnBlock columnBlock : columnBlockList ) {
      if ( currentPop.getHitbox().intersects( columnBlock.getHitbox() ) ) {
        currentPop.setX( ( int ) currentPop.getSafePoint().getX() );
        currentPop.setY( ( int ) currentPop.getSafePoint().getY() );
        currentPop.collisionCheck( columnBlock );
      }
    }
    for ( TopBound topBlock : ceilingBlockList ) {
      if ( currentPop.getHitbox().intersects( topBlock.getHitbox() ) ) {
        currentPop.collisionCheck( topBlock );
      }
    }
    for ( LeftBound leftBlock : leftBlockList ) {
      if ( currentPop.getHitbox().intersects( leftBlock.getHitbox() ) ) {
        currentPop.collisionCheck( leftBlock );
      }
    }
    for ( RightBound rightBound : rightBlockList ) {
      if ( currentPop.getHitbox().intersects( rightBound.getHitbox() ) ) {
        currentPop.collisionCheck( rightBound );
      }
    }
  }

  public void checkDestructibleObjectCollisions( Pop currentPop ) throws IOException {
    for ( int currentObject = 0; currentObject < destructibleObjectList.size(); currentObject++ ) {
      if ( currentPop.getHitbox().intersects( destructibleObjectList.get( currentObject ).getHitbox() ) ) {
        currentPop.collisionCheck( destructibleObjectList.get( currentObject ) );
        destructibleObjectList.get( currentObject ).collisionCheck( currentPop );
        updatePlayerScore( destructibleObjectList.get( currentObject ).getPointValue() );

        if ( destructibleObjectList.get( currentObject ).isLifeBlock() ) {
          playerLives++;
        }
        if ( destructibleObjectList.get( currentObject ).isSplitBlock() ) {
          popList.add( new Pop( destructibleObjectList.get( currentObject ).getX(),
              destructibleObjectList.get( currentObject ).getY(), popPic ) );
          numberOfPopsShowing++;
        }
        destructibleObjectList.remove( destructibleObjectList.get( currentObject ) );
      }
    }

    for ( int currentSquid = 0; currentSquid < squidList.size(); currentSquid++ ) {
      if ( currentPop.getHitbox().intersects( squidList.get( currentSquid ).getHitbox() ) ) {
        currentPop.collisionCheck( squidList.get( currentSquid ) );
        squidList.get( currentSquid ).collisionCheck( currentPop );
        updatePlayerScore( squidList.get( currentSquid ).getPointValue() );
        squidList.remove( currentSquid );
      }
    }
  }

  public void updatePlayerScore( int blockPointValue ) {
    this.playerScore += blockPointValue;
  }

  public int getPlayerScore( ) {
    return playerScore;
  }

  public void checkPopStatus( ) throws IOException {
    for ( int currentPop = 0; currentPop < popList.size(); currentPop++ ) {
      if ( popList.get( currentPop ).getY() > HEIGHT ) {
        numberOfPopsShowing--;
        popList.remove( popList.get( currentPop ) );
        if ( numberOfPopsShowing == 0 ) {
          playerLives--;
          if ( playerLives > 0 ) {
            pop1 = new Pop( this.katch.getX() + 20, this.katch.getY() - 100, popPic );
            popList.add( pop1 );
            numberOfPopsShowing++;
          }
        }
      }
    }
  }

  public void updatePopCoordinates( ) {
    for ( Pop pop : popList ) {
      pop.updateSafePoint( pop.getX(), pop.getY() );
      pop.updateHitbox( ( int ) pop.getSafePoint().getX(), ( int ) pop.getSafePoint().getY() );
    }
  }
}
