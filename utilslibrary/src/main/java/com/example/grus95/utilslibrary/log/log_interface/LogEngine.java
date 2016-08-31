package com.example.grus95.utilslibrary.log.log_interface;

/**
 * Created by grus95 on 16/8/31
 */
public interface LogEngine {


    /**
     * 初始化
     */
    void init();

    /**
     * 初始化
     * @param global_tag
     */
    void init(String global_tag);


    //===============================标准区====================//

    /**
     * 标准Log输出  v
     * @param tag
     * @param msg
     */
    void v(String tag, String msg);

    /**
     * 标准Log输出  d
     * @param tag
     * @param msg
     */
    void d(String tag, String msg);

    /**
     * 标准Log输出  i
     * @param tag
     * @param msg
     */
    void i(String tag, String msg);

    /**
     * 标准Log输出  e
     * @param tag
     * @param msg
     */
    void e(String tag, String msg);

    /**
     * 标准Log输出  w
     * @param tag
     * @param msg
     */
    void w(String tag, String msg);

    //===============================标准区====================//


    //============================不使用Tag===================//

    /**
     * 不使用TAG   v
     * @param msg
     */
    void v(String msg);

    /**
     * 不使用TAG   v
     * @param msg
     */
    void d(String msg);

    /**
     * 不使用TAG   v
     * @param msg
     */
    void i(String msg);

    /**
     * 不使用TAG   v
     * @param msg
     */
    void e(String msg);

    /**
     * 不使用TAG   v
     * @param msg
     */
    void w(String msg);

    //============================不使用Tag===================//


    //============================格式化==================//

    /**
     * 格式化  d
     * 例如Logger.d("hello %s %d", "world", 5);   // String.format
     * @param message
     * @param args
     */
    void d(String message, Object... args);

    /**
     * 格式化  e
     * 例如Logger.d("hello %s %d", "world", 5);   // String.format
     * @param message
     * @param args
     */
    void e(String message, Object... args);

    /**
     * 格式化  i
     * 例如Logger.d("hello %s %d", "world", 5);   // String.format
     * @param message
     * @param args
     */
    void i(String message, Object... args);

    /**
     * 格式化  w
     * 例如Logger.d("hello %s %d", "world", 5);   // String.format
     * @param message
     * @param args
     */
    void w(String message, Object... args);

    /**
     * 格式化  d
     * 例如Logger.d("hello %s %d", "world", 5);   // String.format
     * @param message
     * @param args
     */
    void v(String message, Object... args);

    /**
     * 格式化  wtf
     * 例如Logger.d("hello %s %d", "world", 5);   // String.format
     * @param message
     * @param args
     */
    void wtf(String message, Object... args);

    //============================格式化==================//


    //============================扩展====================//

    /**
     * 输出并格式化Json
     * @param json
     */
    void json(String json);

    /**
     * 输出并格式化xml
     * @param xml
     */
    void xml(String xml);

    /**
     * 输出异常信息
     * @param throwable
     * @param message
     * @param args
     */
    void e(Throwable throwable, String message, Object... args);


    //============================扩展====================//



    boolean isDebug();

}
