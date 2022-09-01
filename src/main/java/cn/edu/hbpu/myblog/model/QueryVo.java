package cn.edu.hbpu.myblog.model;

public class QueryVo {
    private int pageNum;
    private int uid;
    private int touid;
    private int getuid;
    private String keyword;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getGetuid() {
        return getuid;
    }

    public void setGetuid(int getuid) {
        this.getuid = getuid;
    }

    public int getTouid() {
        return touid;
    }

    public void setTouid(int touid) {
        this.touid = touid;
    }
}
