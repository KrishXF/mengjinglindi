package com.springmvc.pojo;

public class Videomanageplatforminfo {
    private Integer id;

    private String platformip;

    private Integer taskmode;

    private Integer taskruninterval;

    private Integer taskgetinterval;

    private Integer taskbegintime;

    private Integer taskendtime;

    private Integer lasttasktime;

    private Integer compresstaskstatus;

    private Integer errorcode;

    private Integer webservicetaskstatus;

    private Integer resved1;

    private String resved2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlatformip() {
        return platformip;
    }

    public void setPlatformip(String platformip) {
        this.platformip = platformip == null ? null : platformip.trim();
    }

    public Integer getTaskmode() {
        return taskmode;
    }

    public void setTaskmode(Integer taskmode) {
        this.taskmode = taskmode;
    }

    public Integer getTaskruninterval() {
        return taskruninterval;
    }

    public void setTaskruninterval(Integer taskruninterval) {
        this.taskruninterval = taskruninterval;
    }

    public Integer getTaskgetinterval() {
        return taskgetinterval;
    }

    public void setTaskgetinterval(Integer taskgetinterval) {
        this.taskgetinterval = taskgetinterval;
    }

    public Integer getTaskbegintime() {
        return taskbegintime;
    }

    public void setTaskbegintime(Integer taskbegintime) {
        this.taskbegintime = taskbegintime;
    }

    public Integer getTaskendtime() {
        return taskendtime;
    }

    public void setTaskendtime(Integer taskendtime) {
        this.taskendtime = taskendtime;
    }

    public Integer getLasttasktime() {
        return lasttasktime;
    }

    public void setLasttasktime(Integer lasttasktime) {
        this.lasttasktime = lasttasktime;
    }

    public Integer getCompresstaskstatus() {
        return compresstaskstatus;
    }

    public void setCompresstaskstatus(Integer compresstaskstatus) {
        this.compresstaskstatus = compresstaskstatus;
    }

    public Integer getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(Integer errorcode) {
        this.errorcode = errorcode;
    }

    public Integer getWebservicetaskstatus() {
        return webservicetaskstatus;
    }

    public void setWebservicetaskstatus(Integer webservicetaskstatus) {
        this.webservicetaskstatus = webservicetaskstatus;
    }

    public Integer getResved1() {
        return resved1;
    }

    public void setResved1(Integer resved1) {
        this.resved1 = resved1;
    }

    public String getResved2() {
        return resved2;
    }

    public void setResved2(String resved2) {
        this.resved2 = resved2 == null ? null : resved2.trim();
    }
}