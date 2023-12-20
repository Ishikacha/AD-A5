// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package version;


import java.io.Serializable;

import supportStuff.utility.Version;


/**
 * The single purpose of this class is to hold the &quot;project version&quot;.
 * Since the code/project is an exercise, it's named {@link GivenCodeVersion}.
 * It's not expected or wanted that there are different release branches
 * containing different "Given Code Versions". Otherwise you do everything at your own risk!
 * There has to be only one single release branch (for the "given code")!!!
 * Hence, there is a single/central project version
 * that is stored in this class.
 * 
 * @version {@value #encodedVersion}
 * @author  Michael Schäfers ;  Px@Hamburg-UAS.eu
 */
public class GivenCodeVersion implements Serializable {
    //
    //--VERSION:----------------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv------//#####################################
    //  ========                                        #___~version~___YYYY_MM_DD__dd_     //### HERE:
    final static private long encodedGivenCodeVersion = 2___00001_015___2023_12_20__01L;    //### <<<=== !!! GIVEN CODE VERSION !!!
    //------------------------=======================---#---^^^^^-^^^---^^^^-^^-^^--^^      //#####################################
    //#############################################################################################################################
    final static private long encodedOwnClassVersion  = 2___00002_009___2023_03_08__02L;    // encoded version of class itself
    final static private Version givenCodeVersion = new Version( encodedGivenCodeVersion );
    final static private Version ownClassVersion  = new Version( encodedOwnClassVersion );
    // Obiges (ab VERSION) dient nur der Versionierung.
    
    // serial version unique ID is based on given code version
    private static final long serialVersionUID = givenCodeVersion.getEncodedVersion();
    
    
    
    
    
    /**
     * The method {@link #getEncodedVersion()} delivers the given code / project version.
     * 
     * @return version
     */
    static public long getEncodedVersion(){
        return givenCodeVersion.getEncodedVersion();
    }//method()
    
    /**
     * The method {@link #getDecodedVersion()} delivers the given code / project version as reground/readable String.
     * 
     * @return version as decoded/readable String.
     */
    static public String getDecodedVersion(){
        return givenCodeVersion.getDecodedVersion();
    }//method()
    
    
    /**
     * The method {@link #getEncodedVersionOfClassItself()} delivers the (given code) class version itself.
     * 
     * @return version
     */
    static public long getEncodedVersionOfClassItself(){
        return ownClassVersion.getEncodedVersion();
    }//method()
    
    /**
     * The method {@link #getDecodedVersionOfClassItself()} delivers the (given code) class version itself as reground/readable String.
     * 
     * @return version as decoded/readable String.
     */
    static public String getDecodedVersionOfClassItself(){
        return ownClassVersion.getDecodedVersion();
    }//method()
    
}//class
