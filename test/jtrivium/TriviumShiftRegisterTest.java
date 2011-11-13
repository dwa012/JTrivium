/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtrivium;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author daniel
 */
public class TriviumShiftRegisterTest {
    
   private TriviumShiftRegister register1;
    
    public TriviumShiftRegisterTest() {
        
    }
    
    @Before
    public void setUp() {
        register1 = new TriviumShiftRegister(10);
    }

    /**
     * Test of size method, of class TriviumShiftRegister.
     */
    @Test
    public void testSize() {
       assertEquals(register1.size(), 10);
    }

    /**
     * Test of getBitAt method, of class TriviumShiftRegister.
     */
    @Test
    public void testGetBitAt() {
        for (int i = 0; i < register1.size(); i++) {
           assertEquals(register1.getBitAt(i), 0x00);           
        }
        
        for (int i = 0; i < 3; i++) {
           register1.loadValue((byte)0x01);         
        }
        
        for (int i = 0; i < 3; i++) {
           assertEquals(register1.getBitAt(i), 0x01);        
        }
        
        for (int i = 0; i < 3; i++) {
           register1.loadValue((byte)0x00);         
        }
        
        for (int i = 0; i < 3; i++) {
           assertEquals(register1.getBitAt(i), 0x00);        
        }
        
        for (int i = 3; i < 6; i++) {
           assertEquals(register1.getBitAt(i), 0x01);        
        }
    }

    /**
     * Test of loadValue method, of class TriviumShiftRegister.
     */
    @Test
    public void testLoadValue() {
        for (int i = 0; i < 3; i++) {
           register1.loadValue((byte)0x01);         
        }
        
        for (int i = 0; i < 3; i++) {
           assertEquals(register1.getBitAt(i), 0x01);        
        }
        
        for (int i = 0; i < 3; i++) {
           register1.loadValue((byte)0x00);         
        }
        
        for (int i = 0; i < 3; i++) {
           assertEquals(register1.getBitAt(i), 0x00);        
        }
        
        for (int i = 3; i < 6; i++) {
           assertEquals(register1.getBitAt(i), 0x01);        
        }
    }
}
