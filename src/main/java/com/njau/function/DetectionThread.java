package com.njau.function;

public class DetectionThread implements Runnable{

    @Override
    public void run() {
        CagePhotoFunction cf =   new CagePhotoFunction();
        cf.fetchAndProcess();
    }
}
