package vjj.web.service.Interface;

public interface IExecuteCallBack {
    public void startExecute();
    public void stopExecute();
    public void showJobName(String jobname);
    public void showExecuteProgress(int mProgress);

}
