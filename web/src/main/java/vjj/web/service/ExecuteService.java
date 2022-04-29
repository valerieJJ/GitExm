package vjj.web.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vjj.web.entity.Worker;
import vjj.web.service.Interface.IExecuteCallBack;

@Slf4j
@Service
public class ExecuteService implements IExecuteCallBack {
    private Worker worker = null;

    public void execute(String jobname){
        Worker worker = new Worker(ExecuteService.this, jobname);
        worker.work();
    }

    public void startExecute() {
        log.info("start executing...");
    }

    public void stopExecute() {
        log.info("stop executing...");
    }

    public void showJobName(String jobname) {
        System.out.println("job name: " + jobname);
    }

    public void showExecuteProgress(int mProgress) {
        System.out.println("progress: " + mProgress);
    }

}
