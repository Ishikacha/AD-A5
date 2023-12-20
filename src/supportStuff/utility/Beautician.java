// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
//"Home"-VCS:   git@git.HAW-Hamburg.de:shf/Px/LabExercise/ZZZ_SupportStuff[.git]
package supportStuff.utility;


import java.io.Serializable;
import java.time.Clock;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


/**
 * The class {@link Beautician} ...
 * 
 * @version {@value #encodedVersion}
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          Px@Hamburg-UAS.eu 
 */
public class Beautician implements Serializable {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00001_004___2023_08_30__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    /**
     * The method {@link #getDecodedVersion()} delivers the code version as reground/readable String.
     * @return version as decoded/readable String.
     */
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    //
    static final private long serialVersionUID = version.getEncodedVersion();
    
    
    
    
    
    /**
     * ...
     * 
     * @param nanoSeconds ...
     * @return ...
     */
    static public String nanoSecondBasedTimeToString( final long nanoSeconds ){
        if ( nanoSeconds >= 1_000_000_000_000_000_000L ){
            return String.format(
                "%d.%03d.%03d.%03d,%03d.%03d.%03d[s]",
                nanoSeconds  /  1_000_000_000_000_000_000L,
               (nanoSeconds  /      1_000_000_000_000_000L) % 1_000L,
               (nanoSeconds  /          1_000_000_000_000L) % 1_000L,
               (nanoSeconds  /              1_000_000_000L) % 1_000L,
               (nanoSeconds  /                  1_000_000L) % 1_000L,
               (nanoSeconds  /                      1_000L) % 1_000L,
                nanoSeconds  %                      1_000L
            );
        }else if ( nanoSeconds >= 1_000_000_000_000_000L ){
            return String.format(
                "%d.%03d.%03d,%03d.%03d.%03d[s]",
                nanoSeconds  /  1_000_000_000_000_000L,
               (nanoSeconds  /      1_000_000_000_000L) % 1_000L,
               (nanoSeconds  /          1_000_000_000L) % 1_000L,
               (nanoSeconds  /              1_000_000L) % 1_000L,
               (nanoSeconds  /                  1_000L) % 1_000L,
                nanoSeconds  %                  1_000L
            );
        }else if ( nanoSeconds >= 1_000_000_000_000L ){
            return String.format(
                "%d.%03d,%03d.%03d.%03d[s]",
                nanoSeconds  /  1_000_000_000_000L,
               (nanoSeconds  /      1_000_000_000L) % 1_000L,
               (nanoSeconds  /          1_000_000L) % 1_000L,
               (nanoSeconds  /              1_000L) % 1_000L,
                nanoSeconds  %              1_000L
            );
        }else if ( nanoSeconds >= 1_000_000_000L ){
            return String.format(
                "%d,%03d.%03d.%03d[s]",
                nanoSeconds  /  1_000_000_000L,
               (nanoSeconds  /      1_000_000L) % 1_000L,
               (nanoSeconds  /          1_000L) % 1_000L,
                nanoSeconds  %          1_000L
            );
        }else if ( nanoSeconds >= 1_000_000L ){
            return String.format(
                "%d,%03d.%03d[ms]",
                nanoSeconds  /  1_000_000L,
               (nanoSeconds  /      1_000L) % 1_000L,
                nanoSeconds  %      1_000L
            );
        }else if ( nanoSeconds >= 1_000L ){
            return String.format(
                "%d,%03d[us]",
                nanoSeconds  /  1_000L,
                nanoSeconds  %  1_000L
            );
        }else{
            return String.format(
                "%d[ns]",
                nanoSeconds
            );
        }//if
    }//method()
    
    
    /**
     * ...
     * 
     * @return ...
     */
    static public String getPimpedTime(){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone( ZoneId.of( "Europe/Berlin") ).format( Clock.systemUTC().instant() );
    }//method()
    
}//class
