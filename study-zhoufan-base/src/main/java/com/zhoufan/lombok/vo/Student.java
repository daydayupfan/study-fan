/*
 * Copyright (c) Travelsky Corp.
 * All Rights Reserved.
 */
package com.zhoufan.lombok.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

/**
 * Student.
 *
 * @version Araf v1.0
 * @author zhoufan, 2018年8月20日
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Student {

    private String name;

    private String sNo;

    private int age;
}
