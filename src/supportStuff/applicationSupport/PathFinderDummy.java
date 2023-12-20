package supportStuff.applicationSupport;


import java.util.ArrayList;
import java.util.List;

import application.Maze;
import application.PathFinder_I;


/*
 *------------------------------------------------------------------------------
 * For information see ReadMe.txt                                     2020/12/11
 * 
 * Dies ist nur Dummy-Code als Demo!!!
 * Dieser Code ist NICHT(!) korrekt!
 * Es ist Ihre Aufgabe korrekten Code zu schreiben!
 *------------------------------------------------------------------------------
 */
public class PathFinderDummy implements PathFinder_I {
    
    final int[][] field;
    private final int startX;
    private final int startY;
    private final int destinationX;
    private final int destinationY;
    
    private final List<Coordinate>  shortestPath;
    private boolean shortestPathNotComputedYet;
    
    
    
    public PathFinderDummy( final Maze maze ){
        // adapt maze representation to internal needs
        field = maze.getMazeField();
        startX = maze.getStart().getX();
        startY = maze.getStart().getY();
        destinationX = maze.getDestination().getX();
        destinationY = maze.getDestination().getY();
        // ^^^^--- ACHTUNG!!! ---^^^^
        // Ausgabe/Maze::prettyPrint() gibt das Original-Maze aus und kennt mögliche Kopien nicht!!!
        // Im Beispiel oben ist field eine Referenz auf das Original-Field im Original-Maze - daher unkritisch.
        // Ansonsten müssten die relevanten (neuberechneten) Werte ins Original-Maze zurücktransportiert werden
        // sofern eine sinnvolle Ausgabe gewünscht ist.
        // Für alle, die keine Originale "zerstören" wollen - hier ist es eingefordert,
        // sonst kennt die Ausgabe/Maze::prettyPrint() nicht die auszugebenden Daten.
        // Das Maze hat auch noch für sich selbst ein "2.Original" für die Kontrollen ;-)
        
        
        shortestPath = new ArrayList<Coordinate>();                             // "container" for upcoming shortest path
        shortestPathNotComputedYet = true;                                      // shortest path is NOT computed yet
    }//constructor()
    
    
    
    @Override
    public List<Coordinate> getShortestPath(){
        if( shortestPathNotComputedYet ){
            computeShortestPath();
            shortestPathNotComputedYet = false;
        }//if
        return shortestPath;
    }//method()
    
    private void computeShortestPath(){
        final int dx = destinationX;
        final int dy = destinationY;
        int sx = startX;
        int sy = startY;
        
        shortestPath.add( new Coordinate( sx, sy ));
        field[sx][sy] = 0;
        
        while( sx < dx ){  sx++;  shortestPath.add( new Coordinate( sx, sy ));  field[sx][sy] = 0;  }
        while( sx > dx ){  sx--;  shortestPath.add( new Coordinate( sx, sy ));  field[sx][sy] = 0;  }
        while( sy < dy ){  sy++;  shortestPath.add( new Coordinate( sx, sy ));  field[sx][sy] = 0;  }
        while( sy > dy ){  sy--;  shortestPath.add( new Coordinate( sx, sy ));  field[sx][sy] = 0;  }
    }//method()
    
}//class
