/*
 * Copyright (c) Travelsky Corp.
 * All Rights Reserved.
 */
package com.zhoufan.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * FileUtils.
 *
 * @author Create By ZhouFan
 * @version v1.0
 * @since 2019/09/02
 */
public class FileUtils {

    private final  static String FILEPATH="F:\\工作\\开发\\SAL系统研发\\SAL-参考文档\\IPRA示例数据带\\BSP\\CNah9995_20190715_AirlineBillingHOT190704.file";


    public static void main(String[] args) {
        File file=new File(FILEPATH);
        try {
            FileReader reader=new FileReader(file);
            BufferedReader br=new BufferedReader(reader);

            String str=null;
            String msg=null;
            while ((str=br.readLine())!=null){
                msg=str.substring(11,13);
                if("47".equals(msg)){
                    System.out.println(str);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}