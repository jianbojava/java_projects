package com.cocosh.framework.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * PriceMonitor.java
 * 
 * @author Techzero
 * @Email techzero@163.com
 * @Time 2014-5-21 下午1:24:30
 */
class MyThread extends Thread {
  public void run() {
    try {
      // 此处参数为监控持续分钟数
      PriceMonitor.monitorButton(10);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
};

public class PriceMonitor {
  // 监控的商品URL
//  private static String URL = "http://detail.ju.taobao.com/home.htm?spm=608.2214381.3.1.AdPEjn&item_id=38260927591&id=10000002781939";

  private static String URL = "http://detail.ju.taobao.com/home.htm?spm=608.2291429.102212b.27.deoyci&id=10000006320584&item_id=39850480112";

  // 监视按钮
  public static void monitorButton(int lastMinute) {
    int nowMinute = Integer.parseInt(new SimpleDateFormat("mm").format(new Date()));
    int endMinute = Integer.parseInt(new SimpleDateFormat("mm").format(new Date())) + lastMinute;
    while (nowMinute < endMinute) {
      nowMinute = Integer.parseInt(new SimpleDateFormat("mm").format(new Date()));
      String result[] = getCurrentButtonAndForm(URL, "gb2312").split(",");
      // 当前按钮状态
      String currentButton = result[0];
      // 马上抢 表单
      //String form = result[1];
      String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
      System.out.println(nowTime + " - 现在按钮是 " + currentButton);

      if (currentButton == "马上抢" || currentButton.equals("马上抢") || currentButton == "还有机会" || currentButton.equals("还有机会")) {
        System.out.println("赶紧下单！");
        try {
          java.awt.Desktop.getDesktop().browse(new URI(URL));
        } catch (Exception e) {
          e.printStackTrace();
        }
        //doPost(form);
        break;
      } else if (currentButton == "卖光了" || currentButton.equals("卖光了") || currentButton.equals("已结束") || currentButton.equals("已结束")) {
        System.out.println("下次再试吧！");
        break;
      } else {
        System.out.println("还没开始呢，再等等吧！");
      }
    }
  }

  // 获取当前按钮状态
  public static String getCurrentButtonAndForm(String url, String encoding) {
    if (url == null || "".equals(url.trim()))
      return null;
    String buttonState = "";
    StringBuffer content = new StringBuffer();
    boolean formFlag = false;
    try {
      // 新建URL对象
      URL u = new URL(url);
      InputStream is = new BufferedInputStream(u.openStream());
      InputStreamReader theHTML = new InputStreamReader(is, encoding != null ? encoding : "gb2312");
      BufferedReader br = new BufferedReader(theHTML);
      String s = "";
      while ((s = br.readLine()) != null) {
        if (s.indexOf("<input type=\"submit\" class=\"buyaction J_BuySubmit\"  title=\"马上抢\" value=\"马上抢\"/>") != -1) {
          buttonState = "马上抢";
        } else if (s.indexOf("<a href=\"#\" class=\"extra  notice J_BuyButtonSub\">开团提醒</a>") != -1) {
          buttonState = "开团提醒";
        } else if (s.indexOf("<div class=\"main-box chance \">") != -1) {
          buttonState = "还有机会";
        } else if (s.indexOf("<span class=\"out floatright\">卖光了...</span>") != -1) {
          buttonState = "卖光了";
        } else if (s.indexOf("<span class=\"out floatright\">已结束...</span>") != -1) {
          buttonState = "已结束";
        }
        if (s.indexOf("<form class=\"J_BuySubForm\" data-ccb=\"0\" data-ques=\"0\" action") != -1) {
          content.append(s + "\r\n");
          formFlag = true;
        }
        if (formFlag == true) {
          if (s.indexOf("<input name=\'_tb_token_\' type=\'hidden\' value") != -1) {
            content.append(s + "\r\n");
          }
          if (s.indexOf("<input type=\"hidden\" name=\"_input_charset\" value") != -1) {
            content.append(s + "\r\n");
          }
          if (s.indexOf("<input type=\"hidden\" name=\"itemId\" value") != -1) {
            content.append(s + "\r\n");
          }
          if (s.indexOf("<input type=\"hidden\" name=\"id\" value") != -1) {
            content.append(s + "\r\n");
          }
          if (s.indexOf("<input type=\"hidden\" name=\"tgType\" value") != -1) {
            content.append(s + "\r\n");
          }
          if (s.indexOf("<input type=\"submit\" class=\"buyaction J_BuySubmit\"") != -1) {
            content.append(s + "\r\n");
          }
          if (s.indexOf("</form>") != -1) {
            content.append(s + "\r\n");
          }
        }
        if (s.indexOf("<div class=\"time-banner\">") != -1) {
          break;
        }
      }
      br.close();
    } catch (Exception e) {
      System.err.println(e);
      return "Open URL Error";
    }
    return buttonState + "," + content;
  }

  // 提交表单
  public static String doPost(String form) {
    StringBuffer content = new StringBuffer();
    try {
      URLConnection connection = new URL(URL).openConnection();
      connection.setDoOutput(true);
      OutputStreamWriter os = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
      os.write(form);
      os.flush();
      os.close();
      InputStream is = connection.getInputStream();
      InputStreamReader theHTML = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(theHTML);
      String s = "";
      while ((s = br.readLine()) != null) {
        content.append(s + "\r\n");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    // 返回提交表单后返回的页面内容
    return content.toString();
  }
  
  // 登录
  public static void doLogin(String username, String password) {
    String form = "<form id=\"J_StaticForm\" action=\"https://login.taobao.com/member/login.jhtml\" method=\"post\" autocomplete=\"on\"><input type=\"text\" name=\"TPL_username\" id=\"TPL_username_1\" value=\"" + username + "\"><input type=\"password\" name=\"TPL_password\" id=\"TPL_password_1\" value=\"" + password + "\"><input type=\"hidden\" id=\"J_TPL_redirect_url\" name=\"TPL_redirect_url\" value=\"http://www.taobao.com/?spm=a2107.1.1000340.1.AL2Mpn\"><button type=\"submit\" id=\"J_SubmitStatic\">登　录</button></form>";
    doPost(form);
  }

  public static void main(String[] args) {
    //doLogin();
    // new MyThread().start();
    // new MyThread().start();
    // new MyThread().start();
    // new MyThread().start();
    // new MyThread().start();
    // new MyThread().start();
    // new MyThread().start();
    new MyThread().start();
  }
}