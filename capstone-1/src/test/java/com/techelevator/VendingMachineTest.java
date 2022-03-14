package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class VendingMachineTest {

        @Test
        public void feedMoneyTest_wrongMoneyEntered () {

            VendingMachine testMachine = new VendingMachine();
            testMachine.setBalance(2.00);
            testMachine.feedMoney(3);


            Assert.assertEquals("The actual and the expected money are not the same", 2.0,  testMachine.getBalance(), 0.001);
        }

        @Test
        public void feedMoneyTest_correctMoneyAdded () {

            VendingMachine testMachine = new VendingMachine();
            testMachine.setBalance(2.00);
            testMachine.feedMoney(10);


            Assert.assertEquals("The actual and the expected money are not the same", 12.0,testMachine.getBalance(), 0.001);
        }
        @Test
        public void changeEndingBalanceZero() {

            VendingMachine machineTest = new VendingMachine();

            machineTest.setBalance(5.65);
            machineTest.change();



            Assert.assertEquals("The machine withheld money", 0 , machineTest.getBalance(), 0.001);








        }
    }

