/*
 * Copyright (c) ACCA Corp.
 * All Rights Reserved.
 */
package com.zhoufan.designpattern.strategy;

import lombok.Setter;

/**
 * Context.
 *
 * @author Zhou Fan, 2019年12月06日
 * @version OPRA.1.0.0
 */

@Setter
public class Context {
    
    private Strategy strategy;

    public Context(){}
    
    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }


    public static void main(String[] args) {
        Context c=new Context();
        
        c.setStrategy(new AddOperation());

        System.out.println(c.executeStrategy(1, 2));
    }
}