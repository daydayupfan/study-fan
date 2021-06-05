/*
 * Copyright (c) ACCA Corp.
 * All Rights Reserved.
 */
package com.zhoufan.designpattern.strategy;

/**
 * AddOperation.
 *
 * @author Zhou Fan, 2019年12月06日
 * @version OPRA.1.0.0
 */

public class AddOperation implements Strategy {
    
    @Override
    public int doOperation(int num1, int num2) {
        return num1+num2;
    }
}