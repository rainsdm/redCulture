package cn.edu.neusoft.controller;

/**
 * 用户登录应用后，在首页选择的项目。
 */
public enum HomepageSelections {
    exit,
    inLogin,
    inRegister;

    /**
     * 根据整数参数，返回对应的常量值。
     *
     * @param ordinal 待匹配的整数。
     * @return 匹配到的常量值。可能为null。
     */
    public static HomepageSelections fromOrdinal(int ordinal) {
        HomepageSelections[] hs = HomepageSelections.values();
        if (ordinal >= 0 && ordinal < hs.length) {
            return hs[ordinal];
        }
        return null;
    }
}
