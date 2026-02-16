package cn.edu.neusoft.core.start.view;

public class IndexView {
    /**
     * 模拟普通用户的首页。
     */
    public static void indexOfGeneralUser() {
        IO.println("=========普通用户===========");
        IO.println("\t 1 个人信息管理");
        IO.println("\t 2 学习打卡");
        IO.println("\t 3 管理学习记录");
        IO.println("\t 4 查看热门景点排行");
        IO.println("\t 5 查看公告");
        IO.println("\t 0 退出系统");
        IO.println("==========================");
    }

    /**
     * 模拟管理员用户的首页
     */
    public static void indexOfAdmin() {
        IO.println("=========管理员===========");
        IO.println("\t 1 管理用户");
        IO.println("\t 2 管理景点");
        IO.println("\t 3 管理公告");
        IO.println("\t 4 查看学习记录");
        IO.println("\t 5 查看热门景点排行");
        IO.println("\t 0 退出系统");
        IO.println("==========================");
    }
}
