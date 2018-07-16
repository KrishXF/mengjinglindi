package com.springmvc.pojo;

import java.util.Date;

public class Zrvcompresstaskassigninfo {
    private String recordnum;

    private String filename;

    private String filesuffixname;

    private Integer filesize;

    private String uploadunit;

    private Integer uploadtime;

    private String storagepath;

    private Integer yshfilesize;

    private String yshstoragepath;

    private Integer assignflag;

    private String platformip;

    private String zrvdeviceip;

    private Date taskcreatetime;

    private Integer zrvcompressbegintime;

    private Integer zrvcompressendtime;

    private Integer taskstatus;

    private Integer taskresult;

    private Integer errorcode;

    private Integer resved1;

    private String resved2;

    public String getRecordnum() {
        return recordnum;
    }

    public void setRecordnum(String recordnum) {
        this.recordnum = recordnum == null ? null : recordnum.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getFilesuffixname() {
        return filesuffixname;
    }

    public void setFilesuffixname(String filesuffixname) {
        this.filesuffixname = filesuffixname == null ? null : filesuffixname.trim();
    }

    public Integer getFilesize() {
        return filesize;
    }

    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

    public String getUploadunit() {
        return uploadunit;
    }

    public void setUploadunit(String uploadunit) {
        this.uploadunit = uploadunit == null ? null : uploadunit.trim();
    }

    public Integer getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Integer uploadtime) {
        this.uploadtime = uploadtime;
    }

    public String getStoragepath() {
        return storagepath;
    }

    public void setStoragepath(String storagepath) {
        this.storagepath = storagepath == null ? null : storagepath.trim();
    }

    public Integer getYshfilesize() {
        return yshfilesize;
    }

    public void setYshfilesize(Integer yshfilesize) {
        this.yshfilesize = yshfilesize;
    }

    public String getYshstoragepath() {
        return yshstoragepath;
    }

    public void setYshstoragepath(String yshstoragepath) {
        this.yshstoragepath = yshstoragepath == null ? null : yshstoragepath.trim();
    }

    public Integer getAssignflag() {
        return assignflag;
    }

    public void setAssignflag(Integer assignflag) {
        this.assignflag = assignflag;
    }

    public String getPlatformip() {
        return platformip;
    }

    public void setPlatformip(String platformip) {
        this.platformip = platformip == null ? null : platformip.trim();
    }

    public String getZrvdeviceip() {
        return zrvdeviceip;
    }

    public void setZrvdeviceip(String zrvdeviceip) {
        this.zrvdeviceip = zrvdeviceip == null ? null : zrvdeviceip.trim();
    }

    public Date getTaskcreatetime() {
        return taskcreatetime;
    }

    public void setTaskcreatetime(Date taskcreatetime) {
        this.taskcreatetime = taskcreatetime;
    }

    public Integer getZrvcompressbegintime() {
        return zrvcompressbegintime;
    }

    public void setZrvcompressbegintime(Integer zrvcompressbegintime) {
        this.zrvcompressbegintime = zrvcompressbegintime;
    }

    public Integer getZrvcompressendtime() {
        return zrvcompressendtime;
    }

    public void setZrvcompressendtime(Integer zrvcompressendtime) {
        this.zrvcompressendtime = zrvcompressendtime;
    }

    public Integer getTaskstatus() {
        return taskstatus;
    }

    public void setTaskstatus(Integer taskstatus) {
        this.taskstatus = taskstatus;
    }

    public Integer getTaskresult() {
        return taskresult;
    }

    public void setTaskresult(Integer taskresult) {
        this.taskresult = taskresult;
    }

    public Integer getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(Integer errorcode) {
        this.errorcode = errorcode;
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