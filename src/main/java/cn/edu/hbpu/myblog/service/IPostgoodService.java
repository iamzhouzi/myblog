package cn.edu.hbpu.myblog.service;

public interface IPostgoodService {
    int addPostGood(int postid,int articleid, int uid);
    int delPostGood(int postid, int uid);
}
