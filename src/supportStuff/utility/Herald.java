// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
//"Home"-VCS:   git@git.HAW-Hamburg.de:shf/Px/LabExercise/ZZZ_SupportStuff[.git]
package supportStuff.utility;


import java.io.Serializable;


/**
 * Herald prints message on screen.
 * 
 * @version {@value #encodedVersion}
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          Px@Hamburg-UAS.eu 
 */
public class Herald implements Serializable {
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
     * 
     */
    public static void proclaimComingDeathOfExecutingThread(){
        proclaimExecutingThreadInformation( "is going down" );
    }//method()
    //
    /**
     * 
     * @param information ...
     */
    public static void proclaimExecutingThreadInformation( final String information ){
        final Thread executingThread = Thread.currentThread();                  // thread that executes this very code
        final StringBuilder sb = new StringBuilder();                           // (thread) local -> hence StringBuffer is NOT required
        sb.append(
            String.format(
                "%d:%s %s\n",
                executingThread.getId(),
                executingThread.getName(),
                information
            )
        );
        proclaimMessage( sb );
    }//method()
    
    
    
    /**
     * ...
     * 
     * @param message ...
     */
    public static void proclaimMessage( final String message ){
        System.err.flush();
        System.out.flush();
        System.out.print( message );
        System.out.flush();
    }//method()
    //
    /**
     * ...
     * 
     * @param message ...
     */
    public static void proclaimMessage( final StringBuilder message ){
        proclaimMessage( message.toString() );
    }//method()
    //
    /**
     * ...
     * 
     * @param message ...
     */
    public static void proclaimMessage( final StringBuffer message ){
        proclaimMessage( message.toString() );
    }//method()
    
    
    /**
     * ...
     * 
     * @param message ...
     */
    public static void proclaimError( final String message ){
        System.out.flush();
        System.err.flush();
        System.err.print( message );
        System.err.flush();
    }//method()
    //
    /**
     * ...
     * 
     * @param message ...
     */
    public static void proclaimError( final StringBuilder message ){
        proclaimError( message.toString() );
    }//method()
    //
    /**
     * ...
     * 
     * @param message ...
     */
    public static void proclaimError( final StringBuffer message ){
        proclaimError( message.toString() );
    }//method()
    
}//class
