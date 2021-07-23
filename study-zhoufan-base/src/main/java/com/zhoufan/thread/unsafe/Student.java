/*
 * Copyright (c) ACCA Corp.
 * All Rights Reserved.
 */
package com.zhoufan.thread.unsafe;

import lombok.Data;

/**
 * Student.
 *
 * @author Zhou Fan, 2021年07月14日
 * @version OPRA. v.1.0.0
 */

@Data
public class Student {

    private volatile String name;
    
    private Integer age;
    
}