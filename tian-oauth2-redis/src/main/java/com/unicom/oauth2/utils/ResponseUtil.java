package com.unicom.oauth2.utils;

import cn.hutool.json.JSONObject;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @Description: 返回结果工具类 用于封装返回结果，返回json @Author: ctf @Date: 2019/5/7 */
public class ResponseUtil {
  private static final Logger log = LoggerFactory.getLogger(ResponseUtil.class);
  /**
   * 使用response输出JSON
   *
   * @param response
   * @param resultMap
   */
  public static void out(ServletResponse response, Map<String, Object> resultMap) {

    PrintWriter out = null;
    try {
      response.setCharacterEncoding("UTF-8");
      response.setContentType("application/json");
      out = response.getWriter();
      out.println(new JSONObject(resultMap));
    } catch (Exception e) {
      log.error(e + "输出JSON出错");
    } finally {
      if (out != null) {
        out.flush();
        out.close();
      }
    }
  }

  public static Map<String, Object> resultMap(Integer code, String msg) {
    Map<String, Object> resultMap = new HashMap<String, Object>(2);
    resultMap.put("message", msg);
    resultMap.put("code", code);
    return resultMap;
  }

  public static Map<String, Object> resultMap(Integer status, String msg, Object data) {
    Map<String, Object> resultMap = new HashMap<String, Object>(3);
    resultMap.put("message", msg);
    resultMap.put("status", status);
    resultMap.put("data", data);
    return resultMap;
  }
}
