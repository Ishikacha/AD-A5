// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package application;


import java.util.List;

import supportStuff.applicationSupport.Coordinate;
import supportStuff.utility.Version;


/**
 * Task: For information see ReadMe.txt resp. task
 * 
 * @version {@value #encodedVersion}
 * @author  Michael Schäfers ;  P2@Hamburg-UAS.eu 
 */
public interface PathFinder_I {
    //
    //--VERSION:-----------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                       #___~version~___YYYY_MM_DD__dd_
    final static long encodedVersion = 2___00001_009___2021_12_11__01L;
    //---------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static Version pathFinderInterfaceVersion = new Version( encodedVersion );
    static String getDecodedVersion(){ return pathFinderInterfaceVersion.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung
    
    
    
    
    
    /*
     * constructor()
     * =============
    PathFinder( final Maze maze )
    */
    
    
    
    /**
     * ...
     * 
     * @return shortest path from start to destination
     */
    List<Coordinate> getShortestPath();
    
}//interface
