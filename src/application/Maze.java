// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package application;


import supportStuff.applicationSupport.Coordinate;
import supportStuff.utility.Version;


/**
 * The Interface {@link Maze} ...
 * For information see ReadMe.txt resp. task
 * 
 * @version {@value #encodedVersion}
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          Px@Hamburg-UAS.eu 
 */
public interface Maze {
    //
    //--VERSION:-----------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                       #___~version~___YYYY_MM_DD__dd_
    final static long encodedVersion = 2___00001_014___2023_11_28__01L;
    //---------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static Version mazeInterfaceVersion = new Version( encodedVersion );
    /**
     * The method {@link #getDecodedVersion()} delivers the code version as reground/readable String.
     * @return version as decoded/readable String.
     */
    static String getDecodedVersion(){ return mazeInterfaceVersion.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung
    
    
    
    
    
    /**
     * The method {@link #getMazeField()} delivers an actual maze field.
     * 
     * @return maze field
     */
    int[][] getMazeField();
    
    
    /**
     * The method {@link #getStart()} delivers start coordinates of the maze field.
     * 
     * @return maze field related START COORDINATEs
     */
    Coordinate getStart();
    
    /**
     * The method {@link #getDestination()} delivers destination coordinates of the maze field.
     * 
     * @return maze field related DESTINATION COORDINATEs
     */
    Coordinate getDestination();
    
    
    /**
     * The method {@link #prettyPrint()} does pretty print on stdout/screen.
     */
    void prettyPrint();
    
}//interface
