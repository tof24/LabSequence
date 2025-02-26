package org.acme.calculo;

import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class LabSeqCalculo {
    private final List<BigInteger> cache = new ArrayList<>();


    public LabSeqCalculo() {
        
            cache.add(BigInteger.ZERO);  
            cache.add(BigInteger.ONE); 
            cache.add(BigInteger.ZERO); 
            cache.add(BigInteger.ONE);  
        
    }

    public BigInteger calculate(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must not be negative");
        }

        synchronized (this) { 
            if (n < cache.size()) {
                return cache.get(n);
            }
    
            for (int i = cache.size(); i <= n; i++) {
                BigInteger value = cache.get(i - 4).add(cache.get(i - 3)); //cache.get(i - 4) + cache.get(i - 3)
                cache.add(value);
            }
        }
    
        return cache.get(n);


    }
}

