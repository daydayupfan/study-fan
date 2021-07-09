/*
 * Copyright (c) ACCA Corp.
 * All Rights Reserved.
 */
package com.zhoufan.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * CopyFile.
 *
 * @author Zhou Fan, 2021年07月08日
 * @version OPRA. v.1.0.0
 */

public class CopyFile {

    public static void main(String[] args) {

    }


    public static void copyFileAndReName(String srcFile, String reFileName) throws IOException {
        File src = new File(srcFile);
        File dsc = new File(reFileName);
        byte[] buff = new byte[1024];


        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(dsc);
        int len = 0;
        while ((len = fis.read(buff)) != -1) {
            fos.write(buff, 0, len);
        }

        fos.flush();
        fos.close();
        fis.close();
    }
}