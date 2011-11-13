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
public class JTriviumTest {
    
    public JTriviumTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getKeyBit method, of class JTrivium.
     */
    @Test
    public void testGetKeyBit() {
        System.out.println("getKeyBit");
        JTrivium instance = null;
        byte expResult = 0;
        byte result = instance.getKeyBit();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
