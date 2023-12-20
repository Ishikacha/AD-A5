package supportStuff.testSupport.testGenerationSupport;


/**
 * Task: For information see ReadMe.txt resp. task
 * 
 * @version 1__0001000__2021_11_11__001
 * @author   Michael Schaefers ([UTF-8]:"Michael Schäfers");  P2@Hamburg-UAS.eu 
 */
public class SpiralPrinter {
    
    public void print( final int width ){
        theChar = char1st;
        int before = 0;
        int after = 0;
        for( int w=width; w>=1; w-=2 ){
            System.out.print( "\"" );
            for( int i=0; i<before; i++ ){
                System.out.print( theChar );
                toggleTheChar();
            }//for
            before++;
            //
            for( int i=0; i<w; i++ ){
                System.out.print( theChar );
            }//for
            //
            for( int i=0; i<after; i++ ){
                toggleTheChar();
                System.out.print( theChar );
            }//for
            after++;
            System.out.println( "\"," );
        }//for
        //
        after-=2;
        for( int w=1; w<width; w+=2 ){
            System.out.print( "\"" );
            for( int i=0; i<before; i++ ){
                System.out.print( theChar );
                toggleTheChar();
            }//for
            before--;
            //
            for( int i=0; i<w; i++ ){
                System.out.print( theChar );
            }//for
            //
            for( int i=0; i<after; i++ ){
                toggleTheChar();
                System.out.print( theChar );
            }//for
            after--;
            System.out.println( "\"," );
        }//for
        //
        after-=2;
        
    }//method()
    
    private void toggleTheChar(){
        switch( theChar ){
            case char1st: theChar = char2nd; break;
            case char2nd: theChar = char1st; break;
            default: theChar = '?';
        }//switch
    }//method()
    
    private char theChar = '?';
    
    final private char char1st = ' ';
    final private char char2nd = '#';
    
}//class


/*
12345678901
-----------+
           |  _________11__________
 xxxxxxxxx |  1-________9________-1
 x       x |  1-1-______7______-1-1
 x xxxxx x |  1-1-1-____5____-1-1-1
 x x   x x |  1-1-1-1-__3__-1-1-1-1
 x x xDx x |  1-1-1-1-1-1-1-1-1-1-1
 x x xxx x |  1-1-1-1-1-3____-1-1-1
 x x     x |  1-1-1-1-__5______-1-1
 x xxxxxxx |  1-1-1-____7________-1
Sx         |  1-1-______9__________
-----------+
*/
