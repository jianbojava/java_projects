package com.cocosh.framework.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	public static boolean sendMail(String receiver,String content,String title) {
		String txt="<table style=\"margin:0px auto\" width=\"577\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td>"
	+"<table width=\"575\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td align=\"center\"><br /></td></tr>"
	+"<tr><td bgcolor=\"#336633\" style=\"padding-left: 14px; font-family: Arial, Helvetica, sans-serif;height:21px;font-size: 12px;color: #ffffff;font-weight: bold;\">Content</td>"
	+"</tr><tr><td height=\"150\"><p style=\"font-family: Arial, Helvetica, sans-serif;&#xD;&#xA;font-size: 14px;&#xD;&#xA;line-height: 20px;&#xD;&#xA;color: #000000; font-weight: bold;\">"+
	title
	+"</p><p style=\"font-family: Arial, Helvetica, sans-serif;&#xD;&#xA;font-size: 14px;&#xD;&#xA;line-height: 20px;&#xD;&#xA;color: #000000;\">"+content+"</p></td>"
	+"</tr></tbody></table><table width=\"575\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">"
	+"<tbody><tr><td bgcolor=\"#336633\" style=\"padding-left: 14px; font-family: Arial, Helvetica, sans-serif;height:21px;font-size: 12px;color: #ffffff; font-weight: bold;\">About our</td>"
	+"</tr></tbody></table><table width=\"575\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">"
	+"<tbody><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td width=\"14\" height=\"33\" bgcolor=\"#badaba\">&nbsp;</td>"
	+"<td width=\"561\" bgcolor=\"#badaba\"><table width=\"557\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">"
	+"<tbody><tr><td width=\"312\"><span style=\"font-family: Arial, Helvetica, sans-serif;&#xD;&#xA;font-size: 12px;&#xD;&#xA;line-height: 20px;&#xD;&#xA;color: #000000;\">ECK Cloud Tech S.A.S. 地址：xxx<br />Tel:0576-88808760</span><br /></td>"
	+"<td width=\"260\" style=\"font-family: Arial, Helvetica, sans-serif;&#xD;&#xA;font-size: 12px;&#xD;&#xA;line-height: 20px;&#xD;&#xA;color: #000000;\">E-mail: xx@xx.com<br />www.eckcloud.com</td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table>";
        Properties props = System.getProperties();
 
        // 设置smtp服务器
        props.setProperty("mail.smtp.host", "mailhost");
 
        // 现在的大部分smpt都需要验证了
        props.put("mail.smtp.auth", "true");
 
        Session s = Session.getInstance(props);
 
        // 为了查看运行时的信息
        s.setDebug(true);
 
        // 由邮件会话新建一个消息对象
        MimeMessage message = new MimeMessage(s);
 
        try {
            // 发件人
            InternetAddress from = new InternetAddress("send_name");
            message.setFrom(from);
 
            // 收件人
            InternetAddress to = new InternetAddress(receiver);
            message.setRecipient(Message.RecipientType.TO, to);
 
            // 邮件标题
            message.setSubject(title);
            
            // 邮件内容,也可以使纯文本"text/plain"
            message.setContent(txt, "text/html;charset=gb2312");
 
            message.saveChanges();
            Transport transport = s.getTransport("smtp");
 
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            //发件服务器 --发邮件用户名--密码
            transport.connect("smtp.exmail.qq.com","sendname","pwd");
 
            // 发送
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        } catch (Exception e) {
            return false;
        }
 
    }
	public static void main(String[] args) {
		String content="请勿回复本邮件.如重复发送了邮件，最新的邮件为有效邮件.";
		String title="ECK";
		sendMail("779724801@qq.com",content,title);
	}
}
