package com.ruoyi.common.base;



import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

public class AliOssUtil {
	

	public static OSSClient getOSSClient(String endpoint, String accessId, String accessKey){
        ClientConfiguration conf = new ClientConfiguration();

         // 设置HTTP最大连接数为10
        conf.setMaxConnections(10);
        // 设置TCP连接超时为5000毫秒
        conf.setConnectionTimeout(5000);
        // 设置最大的重试次数为3
        conf.setMaxErrorRetry(3);
        // 设置Socket传输数据超时的时间为2000毫秒
        conf.setSocketTimeout(300000);
        return new OSSClient(endpoint,accessId, accessKey,conf);
    }

    public static String createBucketName(OSSClient ossClient,String bucketName){
        //存储空间
        final String bucketNames=bucketName;
        if(!ossClient.doesBucketExist(bucketName)){
            //创建存储空间
            Bucket bucket=ossClient.createBucket(bucketName);
            return bucket.getName();
        }
        return bucketNames;
    }



    public static void deleteBucket(OSSClient ossClient, String bucketName){
        ossClient.deleteBucket(bucketName);
    }



    public static void deleteFile(OSSClient ossClient, String bucketName, String folder, String key){
        ossClient.deleteObject(bucketName, folder + key);
    }

//    public static String uploadFile(File file,String bucket,String endpoint,String accessId,String accessKey) {
      public static String uploadFile(InputStream is,String bucket,String endpoint,String accessId,String accessKey,String fileName) {
        String bucketName = bucket;
        URL url = null;
        //初始化OSSClient
        OSSClient ossClient=getOSSClient(endpoint,accessId,accessKey);
        try {
            //以输入流的形式上传文件
//            InputStream is = new FileInputStream(file);
//            String path = file.getName();
//            String fileName =path.substring(path.lastIndexOf("\\")+1);
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度
            metadata.setContentLength(is.available());

            //上传文件   (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(bucketName, fileName, is, metadata);

            //解析结果
            url = getUrl(ossClient,fileName,bucket);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url.toString().substring(0,url.toString().lastIndexOf("?"));
    }

    public static String uploadInputStream(String fileName,InputStream in,String bucket,String endpoint,String accessId,String accessKey) {
        String bucketName = bucket;
        URL url = null;
        //初始化OSSClient
        OSSClient ossClient=getOSSClient(endpoint,accessId,accessKey);
        try {
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度
            metadata.setContentLength(in.available());

            //上传文件   (上传文件流的形式)

            ossClient.putObject(bucketName,fileName,in,metadata);

            //解析结果
            url = getUrl(ossClient,fileName,bucket);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url.toString().substring(0,url.toString().lastIndexOf("?"));
    }

    public static URL getUrl(OSSClient ossClient,String key,String bucket) {
        // 设置URL过期时间为10年
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucket, key, expiration);
        return url;
    }


}
