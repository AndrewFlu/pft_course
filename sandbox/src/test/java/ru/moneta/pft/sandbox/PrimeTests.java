package ru.moneta.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PrimeTests {

    @Test
    public void testPrimeFor(){
        Assert.assertTrue(Primes.isPrimesLoopFor(Integer.MAX_VALUE));
    }

    @Test (enabled = false)
    public void testPrimeForLong(){
        long n = Integer.MAX_VALUE;
        Assert.assertTrue(Primes.isPrimesLoopFor(n));
    }

    @Test
    public void testPrimeForFast(){
        Assert.assertTrue(Primes.isPrimesLoopForFast(Integer.MAX_VALUE));
    }

    @Test
    public void testNonPrimeFor(){
        Assert.assertFalse(Primes.isPrimesLoopFor(Integer.MAX_VALUE - 3));
    }

    @Test
    public void testPrimeWhile(){
        Assert.assertTrue(Primes.isPrimesLoopWhile(3));
    }

    @Test
    public void testNonPrimeWhile(){
        Assert.assertFalse(Primes.isPrimesLoopWhile(8));
    }
}
