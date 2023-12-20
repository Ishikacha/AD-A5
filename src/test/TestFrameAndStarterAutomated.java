// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package test;


import static org.junit.jupiter.api.Assertions.*;
import static supportStuff.testSupport.MazeDefinition.MazeId.*;


import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import application.Maze;
import application.PathFinder;
import application.PathFinder_I;
import supportStuff.applicationSupport.Coordinate;
import supportStuff.testSupport.MazeBuilder;
import supportStuff.testSupport.MazeDefinition;
import supportStuff.testSupport.Tools;
import supportStuff.testSupport.MazeDefinition.MazeId;
import supportStuff.utility.Beautician;
import supportStuff.utility.EnvironmentAnalyzer;
import supportStuff.utility.Herald;
import supportStuff.utility.Version;
import version.GivenCodeVersion;
import version.StudentSolutionVersion;


/**
 * (Automated Unit-)TestFrame for PathFinder / Maze : Sequential(P2) & Parallel(PPJ)
 * For further information see ReadMe.txt resp. task.
 * 
 * @version {@value #encodedVersion}
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          Px@Hamburg-UAS.eu 
 */
@TestMethodOrder(OrderAnnotation.class)
public class TestFrameAndStarterAutomated {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00011_005___2023_12_20__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    /**
     * Get decoded version of code {@link TestFrameAndStarterAutomated}
     * 
     * @return decoded version
     */
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung.
    
    
    
    
    
    final private boolean enableAdditonalTestsForIterativeSolutions = false;
    
    
    
    
    
    /**
     * Print information ahead - e.g. about test and environment
     */
    @BeforeAll
    public static void init(){
        System.out.printf( "TestFrame information\n" );
        System.out.printf( "=====================\n" );
        System.out.printf( "\n\n" );
        System.out.printf( "Release:\n" );
        System.out.printf( "    GivenCode version:            %29s\n",  GivenCodeVersion.getDecodedVersion() );
        System.out.printf( "    TestFrame version:            %29s\n",  version.getDecodedVersion() );
        System.out.printf( "    Maze-Interface version:       %29s\n",  Maze.getDecodedVersion() );
        System.out.printf( "    PathFinder-Interface version: %29s\n",  PathFinder_I.getDecodedVersion() );
        System.out.printf( "    Maze-Definition version:      %29s\n",  MazeDefinition.getDecodedVersion() );
        System.out.printf( "\n\n" );
        System.out.printf( "Environment:\n" );
        System.out.printf( "    Java (v1):  %s bzw. %s\n",  System.getProperty( "java.specification.version" ), System.getProperty( "java.version" ) );
        System.out.printf( "    Java (v2):          %s\n",  EnvironmentAnalyzer.getJavaVersion() );
        System.out.printf( "    JUnit5/Jupiter:     %s\n",  EnvironmentAnalyzer.getJUnitJupiterVersion() );
        System.out.printf( "    JUnit5/Platform:    %s\n",  EnvironmentAnalyzer.getJUnitPlatformVersion() );
        System.out.printf( "    assert enabled?:    %s\n",  EnvironmentAnalyzer.isAssertEnabled() );
        System.out.printf( "    UTF-8 configured?:  %s          <- check output\n",  "[ÄËÏÖÜẞäëïöüß␣🙂😉🙁😟😓😎☠]" );
        System.out.printf( "    #Cores:             %d\n",  EnvironmentAnalyzer.getAvailableCores() );
        final long theStudentSolutionVersion = StudentSolutionVersion.getEncodedVersion() % 1__000_000__000_000__000_000L;
        if( theStudentSolutionVersion > 0 ){
            System.out.printf( "\n\n" );
            System.out.printf( "Student-Solution/Implementation version:  %s\n",  StudentSolutionVersion.getDecodedVersion() );
        }//if
        System.out.printf( "\n\n\n\n" );
        System.out.printf( "Start of actual tests\n" );
        System.out.printf( "=====================\n" );
        System.out.printf( "\n" );
        System.out.flush();
    }//method()
    
    
    
    //--------------------------------------------------------------------------
    //
    // TESTs
    //
    
    // 1st very simple tests vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    
    /**
     * Test functionality of PathFinder with maze "3x3p1a"
     */
    @Test
    @Order(110_0110)
    public void testFunctionalityWithMaze3x3p1a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_3x3p1a, 3, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(110_0120)
    public void testFunctionalityWithMaze14x15p1a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_14x15p1a, 92, 0 );
    }//method()
    
    
    
    
    
    // mazes, considered as simple tests vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    
    /**
     * ...
     */
    @Test
    @Order(210_0110)
    public void testFunctionalityWithMaze10x10p1a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_10x10p1a, 24, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(210_0120)
    public void testFunctionalityWithMaze10x10p1b(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_10x10p1b, 10, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(210_0130)
    public void testFunctionalityWithMaze10x10p8a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_10x10p8a, 27, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(210_0140)
    public void testFunctionalityWithMaze21x21p1a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_21x21p1a, 63, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(210_0150)
    public void testFunctionalityWithMaze31x31p1a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_31x31p1a, 165, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(210_0160)
    public void testFunctionalityWithMaze31x31p1b(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_31x31p1b, 165, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(210_0170)
    public void testFunctionalityWithMaze31x39pXa(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_31x39pXa, 73, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(210_0180)
    public void testFunctionalityWithMaze31x39pXb(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_31x39pXb, 73, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(210_0190)
    public void testFunctionalityWithMaze37x37pXa(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_37x37pXa, 87, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(210_0200)
    public void testFunctionalityWithMaze37x37pXb(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_37x37pXb, 87, 0 );
    }//method()
    
    
    
    
    
    // special tests or designed "special" mazes vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    
    /**
     * ...
     */
    @Test
    @Order(310_0110)
    public void testFunctionalityWithMaze2x1p1a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_2x1p1a, 2, 0 );
    }//method()
     
    /**
     * ...
     */
    @Test
    @Order(310_0120)
    public void testFunctionalityWithMaze4x4p2a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_4x4p2a, 7, 0 );
    }//method()
     
    /**
     * ...
     */
    @Test
    @Order(310_0130)
    public void testFunctionalityWithMaze4x4p20a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_4x4p20a, 7, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0140)
    public void testFunctionalityWithMaze17x17p1a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_17x17p1a, 17, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0150)
    public void testFunctionalityWithMaze20x20p1a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_20x20p1a, 20, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0160)
    public void testFunctionalityWithMaze20x20p2a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_20x20p2a, 3, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0170)
    public void testFunctionalityWithMaze20x20pMANYa(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_20x20pMANYa, 34, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0180)
    public void testFunctionalityWithMaze36x36p1a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_36x36p1a, 36, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0190)
    public void testFunctionalityWithMaze63x35p2a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_63x35p2a, 97, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0200)
    public void testFunctionalityWithMaze63x36p1a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_63x36p1a, 63, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0210)
    public void testFunctionalityWithMaze63x36p6a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_63x36p6a, 99, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0220)
    public void testFunctionalityWithMaze64x35p1a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_64x35p1a, 64, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0230)
    public void testFunctionalityWithMaze64x64p1a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_64x64p1a, 3, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0240)
    public void testFunctionalityWithMaze64x64p2a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_64x64p2a, 3, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0250)
    public void testFunctionalityWithMaze64x64p4a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_64x64p4a, 127, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0260)
    public void testFunctionalityWithMaze64x64p64a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_64x64p64a, 65, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0270)
    public void testFunctionalityWithMaze64x64p80a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_64x64p80a, 83, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0280)
    public void testFunctionalityWithMaze64x64pMANYa(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_64x64pMANYa, 13, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0290)
    public void testFunctionalityWithMaze65x65p1a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_65x65p1a, 3, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0300)
    public void testFunctionalityWithMaze65x65p2a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_65x65p2a, 5, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0310)
    public void testFunctionalityWithMaze65x65p2b(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_65x65p2b, 11, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0320)
    public void testFunctionalityWithMaze65x65pMANYa(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_65x65pMANYa, 237, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0330)
    public void testFunctionalityWithMaze66x66p1a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_66x66p1a, 133, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0340)
    public void testFunctionalityWithMaze68x69pMANYa(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_68x69pMANYa, 194, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0350)
    public void testFunctionalityWithMaze119x119pMANYa(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_119x119pMANYa, 166, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0360)
    public void testFunctionalityWithMaze153x33p1a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_153x33pMANYa, 138, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0361)
    public void testFunctionalityWithMaze153x33p1b(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_153x33pMANYb, 140, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0362)
    public void testFunctionalityWithMaze153x33p1c(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_153x33pMANYc, 142, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0363)
    public void testFunctionalityWithMaze153x33p1d(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_153x33pMANYd, 144, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0364)
    public void testFunctionalityWithMaze153x33p1e(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_153x33pMANYe, 146, 0 );
    }//method()
    
    /**
     * ...
     */
    @Test
    @Order(310_0370)
    public void testFunctionalityWithMaze255x255p1a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_255x255p1a, 32767, 0 );
    }//method()
    
    
    
    
    
    // advanced tests vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    
    /**
     * ...
     */
    @Test
    @Order(410_0110)
    public void testFunctionalityWithMaze20x20p0a(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        testFunctionality( testName, MAZE_20x20p0a, null, 0 );
    }//method()
    
    
    
    
    
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    
    /**
     * ...
     */
    @Test
    @Order(910_0110)
    public void testFunctionalityWithSpecialMaze_ONLY_FOR_single_threaded_ITERATIVE_Solutions1024x1024pMANYa(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        if( enableAdditonalTestsForIterativeSolutions ){
            testFunctionality( testName, "SpecialMazeForIterativeSolution_1024x1024pMANYa", 1025, -1 );
        }else{
            Herald.proclaimMessage( String.format( "%s skipped\n", testName ));
        }//if
    }//method() 
    
    /**
     * ...
     */
    @Test
    @Order(910_0210)
    public void testFunctionalityWithSpecialMaze_ONLY_FOR_single_threaded_ITERATIVE_Solutions4096x4096pMANYa(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        if( enableAdditonalTestsForIterativeSolutions ){
            testFunctionality( testName, "SpecialMazeForIterativeSolution_4096x4096pMANYa", 4097, -1 );
        }else{
            Herald.proclaimMessage( String.format( "%s skipped\n", testName ));
        }//if
    }//method() 
    
    /**
     * ...
     */
    @Test
    @Order(910_0220)
    public void testFunctionalityWithSpecialMaze_ONLY_FOR_single_threaded_ITERATIVE_Solutions4096x4096pMANYb(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        if( enableAdditonalTestsForIterativeSolutions ){
            testFunctionality( testName, "SpecialMazeForIterativeSolution_4096x4096pMANYb", 8191, -1 );
        }else{
            Herald.proclaimMessage( String.format( "%s skipped\n", testName ));
        }//if
    }//method() 
    
    /**
     * ...
     */
    @Test
    @Order(910_0230)
    public void testFunctionalityWithSpecialMaze_ONLY_FOR_single_threaded_ITERATIVE_Solutions4096x4095pMANYa(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        if( enableAdditonalTestsForIterativeSolutions ){
            testFunctionality( testName, "SpecialMazeForIterativeSolution_4096x4095pMANYa", 12284, -1 );
        }else{
            Herald.proclaimMessage( String.format( "%s skipped\n", testName ));
        }//if
    }//method() 
    
    /**
     * ...
     */
    @Test
    @Order(910_0310)
    public void testFunctionalityWithSpecialMaze_ONLY_FOR_single_threaded_ITERATIVE_Solutions3003x6003pMANYa(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        if( enableAdditonalTestsForIterativeSolutions ){
            testFunctionality( testName, "SpecialMazeForIterativeSolution_3003x6003pMANYa", 15007, -1 );
        }else{
            Herald.proclaimMessage( String.format( "%s skipped\n", testName ));
        }//if
    }//method() 
    
    
    
    
    
    
    
    
    
    //##########################################################################
    //###
    //###
    //###   test support
    //###
    
    private void testFunctionality(
        final String testName,
        final MazeId mazeId,
        final Integer expectedPathLength,
        final int selectedAlternative
    ){
        testFunctionality( testName, mazeId.toString(), expectedPathLength, selectedAlternative );  // mazeId.toString() "bad" workaround :-(
    }//method()
    //
    private void testFunctionality(
        final String testName,
        final String mazeId_s,
        final Integer expectedPathLength,
        final int selectedAlternative
    ){
        final StringBuilder sb = new StringBuilder();
        sb.append( testName );
        sb.append( "  /  " );
        
        boolean validEnum;
        MazeId mazeId_e = null;
        int specialMazeId = -1;
        try{
            mazeId_e = MazeId.valueOf( mazeId_s );
            validEnum = true;
        }catch( final IllegalArgumentException ex ){
            validEnum = false;
            switch( mazeId_s ){
                case "SpecialMazeForIterativeSolution_1024x1024pMANYa" :
                    specialMazeId = 0;
                  break;
                case "SpecialMazeForIterativeSolution_4096x4096pMANYa" :
                    specialMazeId = 1;
                  break;
                case "SpecialMazeForIterativeSolution_4096x4096pMANYb" :
                    specialMazeId = 2;
                  break;
                case "SpecialMazeForIterativeSolution_4096x4095pMANYa" :
                    specialMazeId = 3;
                  break;
                case "SpecialMazeForIterativeSolution_3003x6003pMANYa" :
                    specialMazeId = 4;
                  break;
                default :
                    throw new IllegalArgumentException( "unexpected control flow as result of unsupported argument" );
            }//switch
        }//try
        
        // print size of maze
        final MazeBuilder tmpMaze = validEnum
            ? MazeBuilder.createMaze( MazeDefinition.definition.get(mazeId_e), 0 )
            : MazeBuilder.createSpecialMaze( specialMazeId, 0 );
        final int[][] tmpMazeField = tmpMaze.getMazeField();
        sb.append( String.format( "X:%d * Y:%d  ->  ", tmpMazeField.length, tmpMazeField[0].length ));
        
        // generate maze and test with that maze
        final String requestedRefTypeName = "PathFinder";
        long sumTime = 0;
        for( int alternative=0; alternative<8; alternative++ ){
            final MazeBuilder testMaze = validEnum
                ? MazeBuilder.createMaze( MazeDefinition.definition.get(mazeId_e), alternative )
                : MazeBuilder.createSpecialMaze( specialMazeId, alternative );
            final Maze copyForCodeUnderTest = (Maze)( testMaze.clone() );
            //
            final long beforeTime = System.nanoTime();
            final PathFinder_I pathFinder = new PathFinder( copyForCodeUnderTest );
            final List<Coordinate> path = pathFinder.getShortestPath();
            final long afterTime = System.nanoTime();
            //
            checkPath(
                testMaze,
                path,
                expectedPathLength,
                mazeId_s,
                alternative,
                selectedAlternative,
                sb
            );
            //
            final long deltaTime = afterTime - beforeTime;
            sb.append( Beautician.nanoSecondBasedTimeToString( deltaTime ));
            sb.append( "  " );
            sumTime += deltaTime;
        }//for
        sb.append( "\n=>  average: " );
        sb.append( Beautician.nanoSecondBasedTimeToString( sumTime/8 ));
        sb.append( "\n\n" );
        Herald.proclaimMessage( sb.toString() );
    }//method()
    
    
    private void checkPath(
        final Maze testMaze,
        final List<Coordinate> path,
        final Integer expectedPathLength,
        final String mazeId,
        final int alternative,
        final int selectedAlternative,
        final StringBuilder sb
    ){
        if( null != expectedPathLength ){
            final int[][] testMazeField = testMaze.getMazeField();
            final int xMax=testMazeField.length;
            final int yMax=testMazeField[0].length;
            
            assertNotNull(
                path,
                String.format( "NO path was found at all  @[%s;%d]",  mazeId, alternative )
            );
            //
            final int pathLength = path.size();
            assertEquals(
                (int)( expectedPathLength ),
                pathLength,
                String.format( "FALSE path (size)  @[%s;%d]",  mazeId, alternative )                
            );
            if( selectedAlternative==alternative ){
                sb.append( pathLength );
                sb.append( " steps\n" );
            }//if
            //
            assertEquals(
                testMaze.getStart(),
                path.get(0),
                String.format( "FALSE start  @[%s;%d]",  mazeId, alternative )
            );
            assertEquals(
                testMaze.getDestination(),
                path.get(path.size() -1 ),
                String.format( "FALSE destination  @[%s;%d]",  mazeId, alternative )
            );
            //
            Coordinate cLast = path.get(0);
            if( selectedAlternative==alternative ){
                sb.append( alternative );
                sb.append( ": " );
                sb.append( cLast.toString() );
            }//if
            for( int indx=1 ; indx<path.size(); indx++ ){
                final Coordinate cNew = path.get( indx );
                final int x = cNew.getX();
                final int y = cNew.getY();
                if( x<0 || xMax<=x || y<0 || yMax<=y ){
                    fail(
                        String.format( "INVALID field coordinate: %s  @[%s;%d]",  cNew, mazeId, alternative )
                    );
                }//if
                final int fieldValue = testMazeField[x][y];
                if( fieldValue==Integer.MIN_VALUE ){
                    fail(
                        String.format( "INVALID step on coordinate: %s  @[%s;%d]",  cNew, mazeId, alternative )
                    );
                }//if
                assertEquals(
                    1,  Tools.delta( cNew, cLast ),
                    String.format( "INVALID step on coordinate: %s  @[%s;%d]",  cNew, mazeId, alternative )
                );
                cLast = cNew;
                if( selectedAlternative==alternative ){
                    sb.append( " " );
                    sb.append( cLast.toString() );
                }//if
            }//for
        }else{
            assertNull(
                path,
                String.format( "FALSE path (size)  @[%s;%d]",  mazeId, alternative )
            );
        }//if
        //
        if( selectedAlternative==alternative ){
            sb.append( "\n" );
        }//if        
    }//method()
    
}//class

