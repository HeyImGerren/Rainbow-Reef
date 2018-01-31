package gameComponents;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Level {

  public Level( ) {}

  public static char[][] parseLevelFile( String resourceLocation ) {
    Path path = FileSystems.getDefault().getPath( resourceLocation );
    char[][] levelMap = new char[0][0];

    try {
      List<String> levelRows = Files.readAllLines( path );
      levelMap = new char[levelRows.size()][levelRows.get( 0 ).length()];

      for ( int i = 0; i < levelRows.size(); i++ ) {
        String row = levelRows.get( i );

        for ( int j = 0; j < row.length(); j++ ) {
          String currentCharacter = String.valueOf( row.charAt( j ) );
          char value;

          if ( currentCharacter.matches( "." ) ) {
            value = currentCharacter.toCharArray()[0];
          } else {
            value = 0;
          }
          levelMap[ i ][ j ] = value;
        }
      }
    } catch ( IOException e ) {
      e.printStackTrace();
    }

    return levelMap;
  }
}