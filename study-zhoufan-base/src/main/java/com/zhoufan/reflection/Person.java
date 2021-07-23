/*
 * Copyright (c) ACCA Corp.
 * All Rights Reserved.
 */
package com.zhoufan.reflection;

import lombok.Data;

import java.util.List;

/**
 * Person.
 *
 * @author Zhou Fan, 2021年07月21日
 * @version OPRA. v.1.0.0
 */

@Data
public class Person {
    
    private String name;
    
    private  Integer age;
    
    public List<String> like;
}