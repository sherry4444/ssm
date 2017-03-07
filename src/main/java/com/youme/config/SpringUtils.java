//package com.youme.config;
//
//
//import static org.apache.commons.lang3.StringUtils.removeEnd;
//import static org.apache.commons.lang3.StringUtils.removeStart;
//
///**
// * 配置thymeleaf自定义参数的相关方法
// * Created by Administrator on 2017/3/5.
// */
//public class SpringUtils {
//
//    // 可以在html中使用${e.res('/')}
//    // 将会自动代理到相关静态资源下面
//    public static String res(String relativeResPath) {
//        Minerable resConfig = new Miner().getMiner("poet.base", "res");
//        String resVersion = resConfig.getString("version", "1");
//        String baseResPath = resConfig.getString("prefix");
//        if (baseResPath == null) throw new RuntimeException("prefix config is NULL.");
//
//        return new StringBuilder(removeEnd(baseResPath, "/")).append("/").append(removeStart(relativeResPath, "/")).append("?v=").append(resVersion).toString();
//    }
//}
