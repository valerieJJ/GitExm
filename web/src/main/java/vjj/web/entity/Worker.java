package vjj.web.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import vjj.web.service.Interface.IExecuteCallBack;

import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@Data//注解在类上，为该类提供读写属性、以及equals(),hashCode(),toString()
public class Worker {
    private IExecuteCallBack iExecuteCallBack;
    private String jobname = null;
    private Timer timer = null;

    private int mProgress = 0;

    public Worker(IExecuteCallBack iExecuteCallBack, String jobname){
        this.iExecuteCallBack = iExecuteCallBack;
        this.jobname = jobname;
        this.mProgress = 0;
    }

    public void work(){
        log.info("worker working...");
        iExecuteCallBack.showJobName(this.jobname);
        iExecuteCallBack.startExecute();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                iExecuteCallBack.showExecuteProgress(mProgress);
                if(mProgress == 50){
                    iExecuteCallBack.stopExecute();
                    timer.cancel();
                }
                mProgress += 10;
            }
        }, 0, 1000);

    }
}
