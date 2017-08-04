package enbledu.todolist.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class NoteEntity implements Serializable{

    private String title;
    private String context;
    private String createTime;
    private int stopYear;
    private int stopMonth;
    private int stopdate;
    private int stopHour;
    private int stopMinute;
    private int priorty;
    private boolean isFinished;

    public NoteEntity(String title, String context, String createTime, int stopYear, int stopMonth, int stopdate, int stopHour, int stopMinute, int priorty, boolean isFinished) {
        this.title = title;
        this.context = context;
        this.createTime = createTime;
        this.stopYear = stopYear;
        this.stopMonth = stopMonth;
        this.stopdate = stopdate;
        this.stopHour = stopHour;
        this.stopMinute = stopMinute;
        this.priorty = priorty;
        this.isFinished = isFinished;
    }

    public NoteEntity() {

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setStopYear(int stopYear) {
        this.stopYear = stopYear;
    }

    public void setStopMonth(int stopMonth) {
        this.stopMonth = stopMonth;
    }

    public void setStopdate(int stopdate) {
        this.stopdate = stopdate;
    }

    public void setStopHour(int stopHour) {
        this.stopHour = stopHour;
    }

    public void setStopMinute(int stopMinute) {
        this.stopMinute = stopMinute;
    }

    public void setPriorty(int priorty) {
        this.priorty = priorty;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public String getTitle() {
        return title;
    }

    public String getContext() {
        return context;
    }

    public String getCreateTime() {
        return createTime;
    }

    public int getStopYear() {
        return stopYear;
    }

    public int getStopMonth() {
        return stopMonth;
    }

    public int getStopdate() {
        return stopdate;
    }

    public int getStopHour() {
        return stopHour;
    }

    public int getStopMinute() {
        return stopMinute;
    }

    public int getPriorty() {
        return priorty;
    }

    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public String toString() {
        return "NoteEntity{" +
                "title='" + title + '\'' +
                ", context='" + context + '\'' +
                ", createTime='" + createTime + '\'' +
                ", stopYear=" + stopYear +
                ", stopMonth=" + stopMonth +
                ", stopdate=" + stopdate +
                ", stopHour=" + stopHour +
                ", stopMinute=" + stopMinute +
                ", priorty=" + priorty +
                ", isFinished=" + isFinished +
                '}';
    }
}
