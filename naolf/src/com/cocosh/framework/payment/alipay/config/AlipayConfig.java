package com.cocosh.framework.payment.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	/*//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088221869301969";
	// 商户的私钥
	public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMQB6MIWpBd+MgMZ5Hr2EgA1O4tyfM/GYKh6kTF8hdHu19Ojv2J6qf5fAKguq4CiSDmsTDa7OHv/gqdZ+sLvLFYDL5bvr6LH6oORZNfvQ2+PGQ6gLOOtsm80RXrbVrATy3vYuJmJZml82iQ1FnaNvNiQaiyTOnzihQHc4yulJh6/AgMBAAECgYEAqEPPUldndPeT7Y+b0mdOWrlYLcUSGrh9oATggjAZHXjB9WD8WoldSA68nKS7/EjUYhZksxOUUPavbgVTvWbfklKkGBsDQZuzS4V5d+nwCcQg+0fLCvfdQkTf3nf74L265hMBx3aGfeJwhPJOy0RQV5k/i/MA5WXK6GQykGyNzsECQQDlH0C6aVg1jvZkOmN+BNgaZEj4g2Fzyo9Xu0agQ1zJlzaujTKaC3RIOb61OU8dE3cYf1rrm07ZrskaaSFCzZDfAkEA2wAxl4xxm7acB4bFxEEgoEe7t+CuZBzR+mgpidVs5nlck3eFRkPFyJITPvOcgM2VK37h3DNHUk7OaVMOVU3OIQJALT8Eu0s+XiVDRvu41hQC0QNo3ZqkTgd8Uyjqg4+J7U1tQP8lNQJ6DiFJJeSRWP6VEENmIwzif15i3FJSfSy3JQJASOavm3ss9mC7X+YFP2dOrQwz+OnaI4lUfL7f1Pou3inYH17SBqnY1QOLVJFTr6y/01LRbLPFMij5nSYAx3S3AQJAbaE4rXXRqbMFbc345VdbOz+XjOEAesX0KoJsdvjSt3UnoqRJGIUKpKUu5gpl4uA7reEyzhy3yRWCh27+6PqYHQ==";
	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
										 
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";*/
	

	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088521696907838";//2088421412951665
	//MD5验证用到
	public static String key = "hnq5vjf8yuz4fzbr2czwqbhnry9mz423";//8sad5ampgn9yse7zmpx97v2nflglsrck
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
		// 合作身份者ID，以2088开头由16位纯数字组成的字符串
//		
	// 商户的私钥（RSA用到）
	public static String private_key = "MIICXAIBAAKBgQC6EOOyMw1JUkYxYo1CWN5T7co5Bg687kGCmn4/5gVbexuhvK2ixoRCLzYOwsaGtZ0mFqnMOq3KCZO61w+4FGlHPYvdF9LNO7yR0lkfST4mka8cX+ehapEwNAZwxOcwBB+5yU3JUbuKbX6CZ+GtGJQ9BDxay00n+sKQ1cuYuTHuHQIDAQABAoGBALH8ZANbqJMB29lz35Ke9hYs+muv6rs54nqvlKNFQsyup9EAbsytv2icRsa59TR5WFlOBqExZHtGzAVHombBR65V4fTgQInH037zpS4TqdLOPfq8LnrxHFRWnX8BfvT3sCync2R+OkK4P9oaGlhY3botPFx/lVuT0N0WqsF6OWxBAkEA7vbZEKRYWFf+noshsHq8vPTW9nml+tqnB5JOBLh1p7OXhxay3EqjgMYH9ODYr3pw6EaybVNZ0eXou2vnK9hvkQJBAMdUosxvKwMEw6cbPV2aCH3nmSsT1GyRTwIZnSJE03rvExkbL21Mq0+2A7eLFnXsN4Spt7BN43ApmUcVvIAJp80CQCfdTEbnc8mBHHvsb/lV8GiBW98QWi0oDtdHg/Zro00Pu8aQddQkK3SPFMK4Q826Wl2VdayhTAmlFhc2IAGDgNECQGlrFX5Q/Mpn5qGmAlpScFNF/PePUTTp1IMj0Lqi88V8UvlgK0D+30sU0/pqEMLk3qfGqmMPjk1vcqTplfuRH7ECQEaL8vAqUU2kllIT5KRy2HAzkI59jIESI22v7EDhqsmBGHm5ZgYsu6D5jM9120EO/r0VCKIsDBY48glWpie0X5k=";
	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
										 
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "MD5";


}
