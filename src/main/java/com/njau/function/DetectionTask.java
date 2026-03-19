package com.njau.function;

import com.njau.utils.ConfigUtils;
import com.njau.utils.TimeUtils;

import java.util.TimerTask;
import java.util.concurrent.ExecutorService;

public class DetectionTask extends TimerTask {
    private ExecutorService executorService;

    public DetectionTask(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void run() {
        DetectionThread detectionThread = new DetectionThread();
        executorService.submit(detectionThread);
    }
}
