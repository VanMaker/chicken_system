package com.njau.main;

import com.njau.function.CagePhotoFunction;

import java.nio.file.Path;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        CagePhotoFunction cpf = new CagePhotoFunction();
//        cpf.fetchAndProcess();
        cpf.uploadPhoto("F:\\Wens_Detection_System\\Detection_Folder\\20260121_174358_cam8.jpg");
    }


}
