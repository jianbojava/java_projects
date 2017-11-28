package com.cocosh.framework.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import sun.misc.BASE64Decoder;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ObjectMetadata;

/**
 * OSS文件上传
 * @author jerry
 */
public class OssUtil {
	private static final String ACCESSKEYID= "LTAIkuybdFtykF51";//BBHLQUi9w78hbKYT
	private static final String ACCESSKEYSECRET = "G592xvzS2LsYk4OD7Y8v5GyHWNFyu3";//qpmaFWOjOxGK3g4Pb4taFg29Cf0CHN
	private static final String ENDPOINT = "http://oss-cn-shanghai.aliyuncs.com";
	public static final String BUCKET_APP="nlf";//naolf
	public static final String BUCKET_MANAGE="nlf";//naolf
	private static OSSClient client;
	static {
		client = new OSSClient(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);
	}
	
	/**
	 * 上传文件
	 * @param request
	 * @param bucketName
	 * @return
	 */
	public static String uploadFile(HttpServletRequest request,String bucketName) {
		String filePath = null;
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		MultipartFile mFile = multiRequest.getFile("file");
		try {
			String fileName = ossFileName(mFile.getInputStream()) + suffix(mFile.getOriginalFilename());
			// 获取上传文件的输入流
			InputStream input = mFile.getInputStream();
			// 创建上传Object的Metadata
			ObjectMetadata meta = new ObjectMetadata();
			// 必须设置ContentLength
			meta.setContentLength(input.available());
			// 上传Object.
			client.putObject(bucketName, fileName,input, meta);
			filePath = domain(bucketName)+ fileName;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;
	}
	
	/**
	 * 上传文件(后台文本编辑器)
	 * @param request
	 * @param bucketName
	 * @return
	 */
	public static String uploadFile(File file,String bucketName,String fileName) {
		String filePath = null;
		bucketName=BUCKET_MANAGE;
		try {
			// 获取上传文件的输入流
			InputStream input = new FileInputStream(file);;
			// 创建上传Object的Metadata
			ObjectMetadata meta = new ObjectMetadata();
			// 必须设置ContentLength
			meta.setContentLength(input.available());
			// 上传Object.
			client.putObject(bucketName, fileName,input, meta);
			filePath = domain(bucketName)+ fileName;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;
	}
	
	/**
	 * 多文件
	 * @param request
	 * @param bucketName
	 * @return
	 */
	public static String uploadMoreFile(HttpServletRequest request,String bucketName) {
		String filePath = "";
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> mFiles=multiRequest.getFiles("file");
		for(MultipartFile mFile:mFiles){
			try {
				String fileName = ossFileName(mFile.getInputStream()) + suffix(mFile.getOriginalFilename());
				// 获取上传文件的输入流
				InputStream input = mFile.getInputStream();
				// 创建上传Object的Metadata
				ObjectMetadata meta = new ObjectMetadata();
				// 必须设置ContentLength
				meta.setContentLength(input.available());
				// 上传Object.
				client.putObject(bucketName, fileName,input, meta);
				filePath += domain(bucketName)+ fileName+",";
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		return filePath;
	}

	/**
	 * 通过base64上传文件
	 * @param base64   base64字符串
	 * @param bucketName  Bucket名称
	 * @param fileName  文件名称 
	 * @return
	 */
	public static String uploadBase64(String base64, String bucketName,String fileName){
		String filePath = null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] buffer = decoder.decodeBuffer(base64);
			// 创建上传Object的Metadata
			ObjectMetadata objectMeta=new ObjectMetadata();
			// 必须设置ContentLength
			objectMeta.setContentLength(buffer.length);
			InputStream input = new ByteArrayInputStream(buffer);
			fileName = ossFileNameBase64(base64) + suffix(fileName);
			client.putObject(bucketName, fileName, input, objectMeta);
			filePath = domain(bucketName) + fileName;
		} catch (OSSException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;
	}
	
	/**
	 * 通过base64 多图片上传
	 * @param base64   base64字符串 多个逗号隔开 a,b,c
	 * @param bucketName  Bucket名称
	 * @param fileName  文件名称  多个逗号隔开 m.jpg,n.png,k.jpg
	 * @return
	 */
	public static String uploadMoreBase64(String base64s,String fileNames){
		String filePathList = "";
		try {
			String[] fNames=fileNames.split(",");
			String[] base64=base64s.split(",");
			for (int i = 0; i < fNames.length; i++) {
				BASE64Decoder decoder = new BASE64Decoder();
				// Base64解码
				byte[] buffer = decoder.decodeBuffer(base64[i]);
				// 创建上传Object的Metadata
				ObjectMetadata objectMeta=new ObjectMetadata();
				// 必须设置ContentLength
				objectMeta.setContentLength(buffer.length);
				InputStream input = new ByteArrayInputStream(buffer);
				String fileName=ossFileNameBase64(base64[i])+suffix(fNames[i]);
				client.putObject(BUCKET_APP, fileName, input, objectMeta);
				String filePath = domain(BUCKET_APP) + fileName;
				filePathList+=filePath+",";
			}
		} catch (OSSException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePathList.substring(0, filePathList.length()-1);
	}
	
	/**
	 * 文件下载
	 * @param bucketName Bucket名称
	 * @param fileName 文件名称
	 * @param filePath 存放路径
	 */
	public static void downloadFile(String bucketName, String fileName,String filePath){
		client.getObject(new GetObjectRequest(bucketName, fileName), new File(filePath));
	}

	/**
	 * 获取文件后缀名
	 * @param fileName
	 * @return
	 */
	private static String suffix(String fileName){
		return fileName.substring(fileName.lastIndexOf("."));
	}
	
	private static String ossFileNameBase64(String base64){
		return SecurityUtil.md5(base64);
	}
	
	private static String ossFileName(InputStream input){
		return SecurityUtil.md5(input);
	}
	
	public static String ossFileName(File input){
		return SecurityUtil.md5(input);
	}
	
	private static String domain(String bucketName){
		String domain=null;
		if (bucketName.equals(BUCKET_APP)) {
			domain="http://nlf.oss-cn-shanghai.aliyuncs.com/";//"http://res.a.vway.me/";
		}else if (bucketName.equals(BUCKET_MANAGE)) {
			domain="http://nlf.oss-cn-shanghai.aliyuncs.com/";//"http://res.m.vway.me/";
		}else{
			domain="http://nlf.oss-cn-shanghai.aliyuncs.com/";
		}
		return domain;
	}
	
	public static void main(String[] args) {
        String base64 = "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";
		System.out.println(uploadBase64(base64, "zxjapp",SecurityUtil.md5(base64)+".jpg"));
	}
}
