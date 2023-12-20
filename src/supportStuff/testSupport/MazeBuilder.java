// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package supportStuff.testSupport;


import application.Maze;
import java.io.Serializable;
import supportStuff.applicationSupport.Coordinate;
import supportStuff.utility.Version;


/**
 * Task: For information see ReadMe.txt resp. task
 * 
 * @version {@value #encodedVersion}
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          P1@Hamburg-UAS.eu  
 */
public class MazeBuilder implements Maze,Cloneable,Serializable {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00001_001___2023_08_31__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    /**
     * The method {@link #getDecodedVersion()} delivers the code version as reground/readable String.
     * @return version as decoded/readable String.
     */
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung
    //
    static final private long serialVersionUID = version.getEncodedVersion();
    
    
    
    
    
    final static private int maxMazeSize = 256;                                 // max size for edge length of maze
    
    
    
    
    final private int[][] theMaze;
    
    final private Coordinate start;
    final private Coordinate destination;
    
    
    
    
    
    
    
    private MazeBuilder( final int[][] theMaze, final Coordinate start, final Coordinate destination ){
        this.theMaze = theMaze;
        this.start = start;
        this.destination = destination;
    }//constructor()
    
    
    
    
    
    
    
    @Override
    public int[][] getMazeField(){
        return theMaze;
    }//method()
    
    @Override
    public Coordinate getStart(){
        return start;
    }//method()
    
    @Override
    public Coordinate getDestination(){
        return destination;
    }//method()
    
    
    @Override
    public String toString(){ throw new UnsupportedOperationException( "SORRY toString() makes no sense for maze - use prettyPrint() instead" ); }
    
    @Override
    public void prettyPrint(){
        // compute required values to do pretty print
        int maxValue = Integer.MIN_VALUE;
        for( final int[] mazeColumn : theMaze ){
            for( final int elem : mazeColumn ){
                if( elem!=Integer.MAX_VALUE && elem>maxValue )  maxValue = elem;
            }//for
        }//for
        if( theMaze.length-1 > maxValue )  maxValue = theMaze.length-1;
        
        final int nod = numberOfDigits( maxValue );
        final int requestedChars = 1+nod;                                       // one additional char for separating blank
        
        
        // print first line with x-coordinates
        System.out.printf( String.format( "%c%dc |", '%',requestedChars ), ' ' );
        for( int x=0; x<theMaze.length; x++ ){
            System.out.printf( String.format( "%c%dd", '%',requestedChars ), x );
        }//for
        System.out.printf( " |\n" );
        //
        // print separating line
        System.out.printf( "%s-+%s+-%s\n",  multiplyChar( requestedChars, '-' ), multiplyChar( 1+requestedChars*theMaze.length, '-' ), multiplyChar( requestedChars, '-' ) );
        //
        // print theMaze line by line with line number ahaead
        for( int y=0; y<theMaze[0].length; y++ ){
            // print line number
            System.out.printf( String.format( "%c%dd |", '%',requestedChars ), y );
            //
            // print line
            for( int x=0; x<theMaze.length; x++ ){
                if( x==start.getX() && y==start.getY() ){
                    System.out.printf( String.format( "%c%dc", '%',requestedChars ), 'S' );
                }else if( x==destination.getX() && y==destination.getY() ){
                    System.out.printf( String.format( "%c%dc", '%',requestedChars ), 'D' );
                }else if( theMaze[x][y] == Integer.MIN_VALUE ){
                    System.out.printf( String.format( "%c%ds", '%',requestedChars ), multiplyChar( nod, '#' ) );
                }else if( theMaze[x][y] == Integer.MAX_VALUE ){
                    System.out.printf( String.format( "%c%ds", '%',requestedChars ), multiplyChar( nod, ' ' ) );
                }else{
                    System.out.printf( String.format( "%c%dd", '%',requestedChars ), theMaze[x][y] );
                }//if
            }//for
            //
            // print line number again
            System.out.printf( String.format( " |%c%dd\n", '%',requestedChars ), y );
        }//for
        //
        // print separating line
        System.out.printf( "%s-+%s+-%s\n",  multiplyChar( requestedChars, '-' ), multiplyChar( 1+requestedChars*theMaze.length, '-' ), multiplyChar( requestedChars, '-' ) );
        //
        // print last line with x-coordinates
        System.out.printf( String.format( "%c%dc |", '%',requestedChars ), ' ' );
        for( int x=0; x<theMaze.length; x++ ){
            System.out.printf( String.format( "%c%dd", '%',requestedChars ), x );
        }//for
        System.out.printf( " |\n" );
    }//method()
    //
    private static int numberOfDigits( long value ){
        int resu=1;
        while( 0 < (value/=10))  resu++;
        return resu;
    }//method()
    //
    private static String multiplyChar( int factor, final char c ){
        StringBuilder sb = new StringBuilder();                                 // there ain't no thread around
        while( --factor>=0 )  sb.append( c );
        return sb.toString();
    }//method()
    
    
    
    @Override
    public Object clone(){
        final int[][] originalMazeField = getMazeField();
        final int xLength = originalMazeField.length;
        final int yLength = originalMazeField[0].length;
        final int[][] resultingMazeField = new int[xLength][yLength];
        for( int x=xLength; --x>=0; ){
            for( int y = yLength; --y>=0; ){
                resultingMazeField[x][y] = originalMazeField[x][y];
            }//for
        }//for
        final Coordinate resultingStart       = (Coordinate)( getStart().clone() );
        final Coordinate resultingDestination = (Coordinate)( getDestination().clone() );
        return new MazeBuilder( resultingMazeField, resultingStart, resultingDestination );
    }//method()    
    
    
    
    
    
    
    
    
    
    
    //##########################################################################
    //###
    //###
    //###
    
    // maze fabric
    static public MazeBuilder createMaze(
        final String[] mazeDefinition,          // the general definition of the maze String array
        final int modificationId                // the way the maze is modified
    ){
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //
        // do checks (if arguments/definitions are valid)
        //
        
        //check modification is valid - 8 modifications are supported
        if( modificationId<0 || 7<modificationId ){
            throw new IllegalArgumentException(
                String.format(
                    "modification id %d is out of [0,..,7]",
                    modificationId
                )//String.format()
            );//throw
        }//if
        //=> modification ID is valid
        
        
        // check if maze definition is valid
        if( null==mazeDefinition || 0>mazeDefinition.length ){
            throw new IllegalArgumentException( "invalid maze definition" );
        }//if
        //=> maze definition is valid (part #1)
        //
        // check for proper array (is it two dimensional?)
        final int yRawLength = mazeDefinition.length;
        final int xRawLength = mazeDefinition[0].length();
        if( 0>xRawLength ){
            throw new IllegalArgumentException( "invalid maze definition" );
        }//if
        for( int i=mazeDefinition.length; --i>1; ){
            if( xRawLength != mazeDefinition[i].length() ){
                throw new IllegalArgumentException( "invalid maze definition" );
            }//if
        }//for
        //=> maze definition is valid (part #2) resp. array is ok resp. maze definition is two dimensional array
        //
        // check array size
        if( maxMazeSize<xRawLength  ||  maxMazeSize<yRawLength ){
            throw new IllegalArgumentException(
                String.format(
                    "invalid maze definition ->   max maze size = %d   but   x=%d * %d=y",
                    maxMazeSize,
                    xRawLength,
                    yRawLength
                )//String.format()
            );//throw
        }//if
        //=> maze definition is valid (part #3) resp. size ok
        
        
        
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //
        // construct maze
        //
        final MazeBuilder rawMaze = generateMaze( mazeDefinition );
        final MazeBuilder theMaze = modifyMaze( rawMaze, modificationId  );
        
        return theMaze;
    }//method()
    
    
    
    // generates raw maze as defined by maze definition
    static private MazeBuilder generateMaze( final String[] mazeDefinition ){
        final int xLength = mazeDefinition[0].length();
        final int yLength = mazeDefinition.length;
        final int[][] resultingMazeField = new int[xLength][yLength];
        Coordinate startProto = null;
        Coordinate destinationProto = null;
        
        for( int y = yLength; --y >= 0; ){
            for( int x = xLength; --x >= 0; ){
                switch( mazeDefinition[y].charAt( x ) ){
                    case '#':
                        resultingMazeField[x][y] = Integer.MIN_VALUE;
                        break;
                    case ' ':
                        resultingMazeField[x][y] = Integer.MAX_VALUE;
                        break;
                    case 'S':
                    case 's':
                        if( null!=startProto ){
                            throw new IllegalArgumentException(
                                String.format(
                                    "invalid maze definition : 2nd start detected ->   1st at x:%d,y:%d   2nd at x:%d,y:%d",
                                    startProto.getX(),
                                    startProto.getY(),
                                    x,
                                    y
                                )//String.format()
                            );//throw
                        }//if
                        startProto = new Coordinate( x, y );
                        resultingMazeField[x][y] = Integer.MAX_VALUE;
                        break;
                    case 'D':
                    case 'd':
                        if( null!=destinationProto ){
                            throw new IllegalArgumentException(
                                String.format(
                                    "invalid maze definition : 2nd destiantion detected ->   1st at x:%d,y%d   2nd at x:%d,y:%d",
                                    destinationProto.getX(),
                                    destinationProto.getY(),
                                    x,
                                    y
                                )//String.format()
                            );//throw
                        }//if
                        destinationProto = new Coordinate( x, y );
                        resultingMazeField[x][y] = Integer.MAX_VALUE;
                        break;
                    default:
                        throw new IllegalArgumentException(
                            String.format(
                                "invalid maze definition : parsing problem  at x:%d,y:%d -> %c",
                                x,
                                y,
                                mazeDefinition[y].charAt( x )
                            )//String.format()
                        );//throw+default
                }//switch
            }//for
        }//for
        
        if( null == startProto )        throw new IllegalArgumentException( "invalid maze definition : no start defined" );
        if( null == destinationProto )  throw new IllegalArgumentException( "invalid maze definition : no destination defined" );
        
        return new MazeBuilder( resultingMazeField, startProto, destinationProto );
    }//method()
    
    
    
    //##########################################################################
    //###
    //### moficiation accoring to rotation, mirroring, ...
    //###
    
    // modifies maze (e.g. rotates or mirrors) as defined by modificationId
    static private MazeBuilder modifyMaze( final MazeBuilder rawMaze, final int modificationId ){
        final Coordinate rawStart = rawMaze.getStart();
        final Coordinate rawDestination = rawMaze.getDestination();
        final int[][] rawMazeField = rawMaze.getMazeField();
        final int xLng = rawMazeField.length;
        final int xMax = xLng-1;
        final int yLng = rawMazeField[0].length;
        final int yMax = yLng-1;
        int[][] resulingMazeField;
        Coordinate start;
        Coordinate destination;
        
        switch ( modificationId ){
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            case 1:
                // a-b      b-a
                // | |  =>  | |   <->
                // d-c      c-d
                resulingMazeField = new int[xLng][yLng];
                for( int x=xLng; --x>=0; ){
                    for( int y = yLng; --y>=0; ){
                        resulingMazeField[xMax-x][y] = rawMazeField[x][y];                            // <->
                    }//for
                }//for
                //
                start       = new Coordinate( xMax-rawStart.getX(),       rawStart.getY() );
                destination = new Coordinate( xMax-rawDestination.getX(), rawDestination.getY() );
                break;
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            case 2:
                // a-b      d-c    ^
                // | |  =>  | |    |
                // d-c      a-b    v
                resulingMazeField = new int[xLng][yLng];
                for( int x=xLng; --x>=0; ){
                    for( int y = yLng; --y>=0; ){
                        resulingMazeField[x][yMax-y] = rawMazeField[x][y];                            //  |
                    }//for
                }//for
                //
                start       = new Coordinate( rawStart.getX(),       yMax-rawStart.getY() );
                destination = new Coordinate( rawDestination.getX(), yMax-rawDestination.getY() );
                break;
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            case 3:
                // a-b      c-b
                // | |  =>  | |    \
                // d-c      d-a
                resulingMazeField = new int[yLng][xLng];
                for( int x=xLng; --x>=0; ){
                    for( int y = yLng; --y>=0; ){
                        resulingMazeField[yMax-y][xMax-x] = rawMazeField[x][y];                       //  \
                    }//for
                }//for
                //
                start       = new Coordinate( yMax-rawStart.getY(),       xMax-rawStart.getX() );
                destination = new Coordinate( yMax-rawDestination.getY(), xMax-rawDestination.getX() );
                break;
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            case 4:
                // a-b      a-d
                // | |  =>  | |    /
                // d-c      b-c
                resulingMazeField = new int[yLng][xLng];
                for( int x=xLng; --x>=0; ){
                    for( int y = yLng; --y>=0; ){
                        resulingMazeField[y][x] = rawMazeField[x][y];                                 //  /
                    }//for
                }//for
                //
                start       = new Coordinate( rawStart.getY(),       rawStart.getX() );
                destination = new Coordinate( rawDestination.getY(), rawDestination.getX() );
                break;
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            case 5:
                // a-b      d-a
                // | |  =>  | |   90�
                // d-c      c-b
                resulingMazeField = new int[yLng][xLng];
                for( int x=xLng; --x>=0; ){
                    for( int y = yLng; --y>=0; ){
                        resulingMazeField[yMax-y][x] = rawMazeField[x][y];                            //  90�
                    }//for
                }//for
                //
                start       = new Coordinate( yMax-rawStart.getY(),       rawStart.getX() );
                destination = new Coordinate( yMax-rawDestination.getY(), rawDestination.getX() );
                break;
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            case 6:
                // a-b      c-d
                // | |  =>  | |  180�
                // d-c      b-a
                resulingMazeField = new int[xLng][yLng];
                for( int x=xLng; --x>=0; ){
                    for( int y = yLng; --y>=0; ){
                        resulingMazeField[xMax-x][yMax-y] = rawMazeField[x][y];                       //  180�
                    }//for
                }//for
                //
                start       = new Coordinate( xMax-rawStart.getX(),       (short)(yMax-rawStart.getY()) );
                destination = new Coordinate( xMax-rawDestination.getX(), (short)(yMax-rawDestination.getY()) );
                break;
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            case 7:
                // a-b      b-c
                // | |  =>  | |  270�
                // d-c      a-d
                resulingMazeField = new int[yLng][xLng];
                for( int x=xLng; --x>=0; ){
                    for( int y = yLng; --y>=0; ){
                        resulingMazeField[y][xMax-x] = rawMazeField[x][y];                                 //  270�
                    }//for
                }//for
                //
                start       = new Coordinate( rawStart.getY(),       xMax-rawStart.getX() );
                destination = new Coordinate( rawDestination.getY(), xMax-rawDestination.getX() );
                break;
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            default:
                // a-b      a-b
                // | |  =>  | |    0�
                // d-c      d-c
                resulingMazeField = rawMazeField;
                start       = rawStart;
                destination = rawDestination;
                break;
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        }//switch
                
        return new MazeBuilder( resulingMazeField, start, destination );
    }//constructor-method()
    
    
    
    
    
    //##########################################################################
    //###
    //###   special constructed mazes (by program - "without plan")
    //###
    
    // special maze fabric
    static public MazeBuilder createSpecialMaze(
        final int specialMazeId,                                                // ...
        final int modificationId                                                // the way the maze is modified
    ){
        // construct maze
        final MazeBuilder rawMaze = generateSpecialMaze( specialMazeId );
        final MazeBuilder theMaze = modifyMaze( rawMaze, modificationId  );
        
        return theMaze;
    }//method()
    
    // generates special raw maze
    static private MazeBuilder generateSpecialMaze( final int specialMazeId ){
        int[][] mazeFieldProto;
        Coordinate startProto;
        Coordinate destinationProto;
        switch( specialMazeId ){
            case 0:{
                /* Idea: S:"Mitte", D:"Rechts-Unten"
                 * =====
                 * 
                 * +-----+  1024x1024
                 * |     |
                 * |     |
                 * | S   |
                 * |     |
                 * |    D|
                 * +-----+
                 */
                final int xLength = 1024;
                final int yLength = 1024;
                mazeFieldProto = new int[xLength][yLength];
                for( int y=yLength; --y>=0; ){
                    for( int x=xLength; --x>=0; ){
                        mazeFieldProto[x][y] = Integer.MAX_VALUE;
                    }//for
                }//for
                startProto = new Coordinate( (xLength>>>1)-1, (yLength>>>1)-1 );
                destinationProto = new Coordinate( xLength-1, yLength-1 );
            }
            break;  
            case 1:{
                /* Idea: S:"Mitte", D:"Rechts-Unten"
                 * =====
                 * 
                 * +-----+  4096x4096
                 * |     |
                 * |     |
                 * | S   |
                 * |     |
                 * |    D|
                 * +-----+
                 */
                final int xLength = 4096;
                final int yLength = 4096;
                mazeFieldProto = new int[xLength][yLength];
                for( int y=yLength; --y>=0; ){
                    for( int x=xLength; --x>=0; ){
                        mazeFieldProto[x][y] = Integer.MAX_VALUE;
                    }//for
                }//for
                startProto = new Coordinate( (xLength>>>1)-1, (yLength>>>1)-1 );
                destinationProto = new Coordinate( xLength-1, yLength-1 );
            }
            break;                
            case 2:{
                /* Idea: S:"Links-Oben", D:"Rechts-Unten"
                 * =====
                 * 
                 * +-----+  4096x4096
                 * |S    |
                 * |     |
                 * |     |
                 * |     |
                 * |    D|
                 * +-----+
                 */
                final int xLength = 4096;
                final int yLength = 4096;
                mazeFieldProto = new int[xLength][yLength];
                for( int y=yLength; --y>=0; ){
                    for( int x=xLength; --x>=0; ){
                        mazeFieldProto[x][y] = Integer.MAX_VALUE;
                    }//for
                }//for
                startProto = new Coordinate( 0, 0 );
                destinationProto = new Coordinate( xLength-1, yLength-1 );
            }
            break;                
            case 3:{
                /* Idea:
                 * =====
                 * 
                 * +-------+  4096x4095
                 * |       |
                 * | #   # |
                 * |  #  # |
                 * |   # # |
                 * |    ## | Notiz:
                 * |S    #D| 12284  =  4095+4095+4094
                 * +-------+
                 */
                final int xLength = 4096;
                final int yLength = 4095;
                mazeFieldProto = new int[xLength][yLength];
                for( int y=yLength; --y>=0; ){
                    for( int x=xLength; --x>=0; ){
                        mazeFieldProto[x][y] = Integer.MAX_VALUE;
                    }//for
                }//for
                for( int y=1;  y < yLength;  y++ )  mazeFieldProto[xLength-2][y] = Integer.MIN_VALUE;
                for( int x=1,y=1;  x < xLength-2;  x++,y++ )  mazeFieldProto[x][y] = Integer.MIN_VALUE;
                startProto = new Coordinate( 0, yLength-1 );
                destinationProto = new Coordinate( xLength-1, yLength-1 );
            }
            break;                
            case 4:{
                /* Idea:
                 * =====
                 * 
                 * +-------+  3003x6003
                 * |       |
                 * | #   # |
                 * | #   # |
                 * |  #  # |
                 * |  #  # |
                 * |   # # |  Notiz:
                 * |   # # |  8*13, 7*11, 6*9, 5*7, 4*5, 3*3 -> (3+x) * 3+2x)
                 * |    ## |  3003-3 = 3000 -> 3+2*3000 = 6003
                 * |    ## |  ====                        ====
                 * |     # |
                 * |S    #D|  15007  =  6003+3002+6002
                 * +-------+
                 */
                final int xLength = 3003;
                final int yLength = 6003;
                mazeFieldProto = new int[xLength][yLength];
                for( int y=yLength; --y>=0; ){
                    for( int x=xLength; --x>=0; ){
                        mazeFieldProto[x][y] = Integer.MAX_VALUE;
                    }//for
                }//for
                for( int y=1; y<yLength; y++ )  mazeFieldProto[xLength-2][y] = Integer.MIN_VALUE;
                for( int x=1,y=1;  x < xLength-2;  x++,y+=2 ){
                    mazeFieldProto[x][y]   = Integer.MIN_VALUE;
                    mazeFieldProto[x][y+1] = Integer.MIN_VALUE;
                }//for
                startProto = new Coordinate( 0, yLength-1 );
                destinationProto = new Coordinate( xLength-1, yLength-1 );
            }
            break;                
            default:
                throw new IllegalArgumentException( "unexpected control flow as result of unsupported argument" );
        }//switch
        return new MazeBuilder( mazeFieldProto, startProto, destinationProto );
    }//method()
    
}//class
