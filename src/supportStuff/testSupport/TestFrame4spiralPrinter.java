// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package supportStuff.testSupport;


import supportStuff.testSupport.testGenerationSupport.SpiralPrinter;
import supportStuff.utility.Version;


/**
 * Task: For information see ReadMe.txt resp. task
 * 
 * @version {@value #encodedVersion}
 * @author  Michael Schäfers ;  Px@Hamburg-UAS.eu
 */
public class TestFrame4spiralPrinter {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00001_000___2021_11_11__02L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    
    
    
    public static void main( final String... unused ){
        final SpiralPrinter sp = new SpiralPrinter();
        sp.print( 255 );
    }//method()
    
}//class
