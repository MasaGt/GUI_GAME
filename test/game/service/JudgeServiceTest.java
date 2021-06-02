/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.service;

import game.entity.GameData;
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
public class JudgeServiceTest {
    
    public JudgeServiceTest() {
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
     * Test of judgeAnswer method, of class JudgeService.
     * case1 player's answer is wrong.
     */
    @Test
    public void testJudgeAnswer() {
        System.out.println("judgeAnswer");
        int answer = 0;
        int input = 1;
        JudgeService instance = new JudgeService();
        boolean expResult = false;
        boolean result = instance.judgeAnswer(answer, input);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of judgeAnswer method, of class JudgeService.
     * case2 player's answer is correct.
     */
    @Test
    public void testJudgeAnswer2() {
        System.out.println("judgeAnswer");
        int answer = 1;
        int input = 1;
        JudgeService instance = new JudgeService();
        boolean expResult = true;
        boolean result = instance.judgeAnswer(answer, input);
        assertEquals(expResult, result);
    }

    
}
