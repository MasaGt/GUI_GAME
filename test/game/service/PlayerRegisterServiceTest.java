/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Masaomi
 */
public class PlayerRegisterServiceTest {
    
    public PlayerRegisterServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of validateName method, of class PlayerRegisterService.
     * case1 invalid name: empty
     */
    @Test
    public void testValidateName1() {
        System.out.println("validateName");
        String name = "";
        PlayerRegisterService instance = new PlayerRegisterService();
        boolean expResult = false;
        boolean result = instance.validateName(name);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validateName method, of class PlayerRegisterService.
     * case2 invalid name: name contains invalid symbols
     */
    @Test
    public void testValidateName2() {
        System.out.println("validateName");
        String name = "inva'lid";
        PlayerRegisterService instance = new PlayerRegisterService();
        boolean expResult = false;
        boolean result = instance.validateName(name);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validateName method, of class PlayerRegisterService.
     * case3 invalid name: name contains a space
     */
    @Test
    public void testValidateName3() {
        System.out.println("validateName");
        String name = "2sample User";
        PlayerRegisterService instance = new PlayerRegisterService();
        boolean expResult = false;
        boolean result = instance.validateName(name);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validateName method, of class PlayerRegisterService.
     * case4 valid name: name contains only one alphabet
     */
    @Test
    public void testValidateName4() {
        System.out.println("validateName");
        String name = "B";
        PlayerRegisterService instance = new PlayerRegisterService();
        boolean expResult = true;
        boolean result = instance.validateName(name);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validateName method, of class PlayerRegisterService.
     * case5 valid name: name contains only one number
     */
    @Test
    public void testValidateName5() {
        System.out.println("validateName");
        String name = "9";
        PlayerRegisterService instance = new PlayerRegisterService();
        boolean expResult = true;
        boolean result = instance.validateName(name);
        assertEquals(expResult, result);
    }
}
