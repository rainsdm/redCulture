package cn.edu.neusoft.view;

import cn.edu.neusoft.model.User;

public class IndexView {
    /**
     * 模拟普通用户的首页。
     */
    public static void indexOfGeneralUser() {
        System.out.println("=========普通用户===========");
        System.out.println("\t 1 个人信息管理");
        System.out.println("\t 2 学习打卡");
        System.out.println("\t 3 管理学习记录");
        System.out.println("\t 4 查看热门景点排行");
//        System.out.println("\t 5 查看公告");
        System.out.println("\t 0 退出系统");
        System.out.println("==========================");
    }

    /**
     * 模拟管理员用户的首页
     *
     * @param user 已登录用户的信息
     */
    public static void indexOfAdmin(User user) {
        System.out.println("=========管理员===========");
        System.out.println("\t 1 管理用户");
        System.out.println("\t 2 管理景点");
        System.out.println("\t 3 管理公告");
        System.out.println("\t 4 查看学习记录");
        System.out.println("\t 5 查看热门景点排行");
        System.out.println("\t 0 退出系统");
        System.out.println("==========================");
    }
}
