//This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package version;


import java.io.Serializable;
import supportStuff.utility.Version;


/**
* Wenn Sie wollen, dann können Sie diese Klasse nutzen um Ihre eigene Lösung zu
* versionieren.
* Im Objekt "version" wird die Version (endgültig) abgelegt;
* Die Hilfsvariable {@link #internalEncodedVersion} dient nur internen Zwecken.
* (Konkret um "diese Version "auch in/mit JavaDoc anzeigen zu können.
* Der Zugriff auf die Version soll über die public Getter
*     {@link #getVersionNumber()}  und
*     {@link #getDecodedVersion()}
* erfolgen.
* <br />
* Die Codierung muss - sofern nicht ohnehin schon selbsterklärend, wie in
* {@link Version} dokumentiert - erfolgen.
* 
* @version {@value #encodedVersion}
* Michael Schäfers ;  Px@Hamburg-UAS.eu
*/
public class StudentSolutionVersion implements Serializable {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00000_000___2016_05_06__00L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    //
    final static private long serialVersionUID = version.getEncodedVersion();
    
    
    
    
    
    /**
     * The method {@link #getVersionNumber()} delivers the (endocded) project version.
     * 
     * @return encoded project version resp. version of your solution. 
     */
    static public long getEncodedVersion(){
        return version.getEncodedVersion();
    }//method()
    
    /**
     * The method {@link #getDecodedVersion()} delivers the given project version
     * as reground/readable String.
     * 
     * @return project version resp. version of your solution as decoded/readable String.
     */
    static public String getDecodedVersion(){
        return version.getDecodedVersion();
    }//method()
    
}//class
