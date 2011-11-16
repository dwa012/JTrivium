package jtrivium;

import jtrivium.hardware.FlipFlop;
import jtrivium.hardware.FlipFlop;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author daniel
 */
public class FlipFlopTest {
    
    private FlipFlop ffOne;
    private FlipFlop ffTwo;
    private FlipFlop ffThree;
    
    public FlipFlopTest() {
    }
    
    @Before
    public void setUp() {
        ffOne = new FlipFlop();
        ffTwo = new FlipFlop();
        ffThree = new FlipFlop();
    }

    /**
     * Test of tick method, of class FlipFlop.
     */
    @Test
    public void testTick() {
        //---------TEST ONE CONNECTED FFs---------//
       //test value of single FlipFlop
        assertEquals(ffOne.getValue(), 0);
        
       //test value after tick() with a 1
        ffOne.tick((byte)0x01);
        assertEquals(ffOne.getValue(), 0x01);
        
        //tets value after tick() with a 0
        ffOne.tick((byte)0x00);
        assertEquals(ffOne.getValue(), 0x00);
        
        //---------TEST TWO CONNECTED FFs---------//
        //-----------  -> 2 -> 1 ->  -------------//
        //test two connected FlipFlops
        ffTwo = new FlipFlop(ffOne);
        assertEquals(ffTwo.getValue(), 0x00);
        assertEquals(ffOne.getValue(), 0x00);
        
        ffTwo.tick((byte)0x01);
        assertEquals(ffTwo.getValue(), 0x01);
        assertEquals(ffOne.getValue(), 0x00);
        
        ffTwo.tick((byte)0x01);
        assertEquals(ffTwo.getValue(), 0x01);
        assertEquals(ffOne.getValue(), 0x01);
        
        ffTwo.tick((byte)0x00);
        assertEquals(ffTwo.getValue(), 0x00);
        assertEquals(ffOne.getValue(), 0x01);
        
        ffTwo.tick((byte)0x00);
        assertEquals(ffTwo.getValue(), 0x00);
        assertEquals(ffOne.getValue(), 0x00);
        
        //---------TEST THREE CONNECTED FFs---------//
        //---------  -> 3 -> 2 -> 1 ->  ------------//
        //test two connected FlipFlops
        ffTwo = new FlipFlop(ffOne);
        ffThree = new FlipFlop(ffTwo);
        assertEquals(ffThree.getValue(), 0x00);
        assertEquals(ffTwo.getValue(), 0x00);
        assertEquals(ffOne.getValue(), 0x00);
        
        ffThree.tick((byte)0x01);
        assertEquals(ffThree.getValue(), 0x01);
        assertEquals(ffTwo.getValue(), 0x00);
        assertEquals(ffOne.getValue(), 0x00);
        
        ffThree.tick((byte)0x01);
        assertEquals(ffThree.getValue(), 0x01);
        assertEquals(ffTwo.getValue(), 0x01);
        assertEquals(ffOne.getValue(), 0x00);
        
        ffThree.tick((byte)0x00);
        assertEquals(ffThree.getValue(), 0x00);
        assertEquals(ffTwo.getValue(), 0x01);
        assertEquals(ffOne.getValue(), 0x01);
        
        ffThree.tick((byte)0x01);
        assertEquals(ffThree.getValue(), 0x01);
        assertEquals(ffTwo.getValue(), 0x00);
        assertEquals(ffOne.getValue(), 0x01);
    }

    /**
     * Test of getRightNeighbor method, of class FlipFlop.
     */
    @Test
    public void testGetRightNeighbor() {
        //---------TEST ONE CONNECTED FFs---------//
        //test right neighbor of a single FlipFlop
        assertEquals(ffOne.getRightNeighbor(), null);
        
        //---------TEST TWO CONNECTED FFs---------//
        //-----------  -> 2 -> 1 ->  -------------//
        //test two connected FlipFlops
        ffTwo = new FlipFlop(ffOne);
        assertEquals(ffTwo.getRightNeighbor(), ffOne);
        assertEquals(ffOne.getRightNeighbor(), null);
        
        //---------TEST Three CONNECTED FFs---------//
        //---------  -> 3 -> 2 -> 1 ->  ------------//
        //test three connected FlipFlops
        ffTwo = new FlipFlop(ffOne);        
        ffThree = new FlipFlop(ffTwo);
        assertEquals(ffThree.getRightNeighbor(), ffTwo);
        assertEquals(ffTwo.getRightNeighbor(), ffOne);
        assertEquals(ffOne.getRightNeighbor(), null);
    }

    /**
     * Test of getValue method, of class FlipFlop.
     */
    @Test
    public void testGetValue() {
       //test value of single FlipFlop
        assertEquals(ffOne.getValue(), 0);
        
       //test value after tick() with a 1
        ffOne.tick((byte)0x01);
        assertEquals(ffOne.getValue(), 0x01);
        
        //tets value after tick() with a 0
        ffOne.tick((byte)0x00);
        assertEquals(ffOne.getValue(), 0x00);
    }
}
