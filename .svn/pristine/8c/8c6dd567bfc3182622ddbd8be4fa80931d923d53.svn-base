 package com.glch.base.util.base64;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

 public class Base64Utils
 {
   public static String ImageToBase64ByLocal(String imgFile)
     throws IOException
   {
     InputStream in = null;
     byte[] data = null;

     in = new FileInputStream(imgFile);

     data = new byte[in.available()];
     in.read(data);
     in.close();

     BASE64Encoder encoder = new BASE64Encoder();
     return encoder.encode(data);
   }

   public static String GetImageStrFromUrl(String imgURL)
     throws IOException
   {
     URL url = new URL(imgURL);

     HttpURLConnection conn = (HttpURLConnection)url.openConnection();
     conn.setRequestMethod("GET");
     int count = conn.getContentLength();
     byte[] data = new byte[count];
     int readCount = 0;
     InputStream inStream = conn.getInputStream();
     while (readCount < count) {
       readCount += inStream.read(data, readCount, count - readCount);
     }

     BASE64Encoder encoder = new BASE64Encoder();

     return encoder.encode(data);
   }

   /**
    * 将图片转换为base64格式
    *
    * @param imageUrl：图片路径
    * @param sizeLimit：原图大小上限，当图片原图大小超过该值时先将图片大小 设置为该值以下再转换成base64格式,单位kb
    * @return
    */
   public static String convertImageToBase64(String imageUrl, Integer subHeight,Integer sizeLimit) throws IOException {
     //默认上限为1024k
     if (sizeLimit == null) {
       sizeLimit = 1024;
     }
     sizeLimit = sizeLimit * 1024;
     String base64Image;
     DataInputStream dataInputStream = null;
     ByteArrayOutputStream outputStream = null;
     ByteArrayInputStream inputStream = null;
     try {
       //从远程读取图片
       URL url = new URL(imageUrl);
       dataInputStream = new DataInputStream(url.openStream());
       outputStream = new ByteArrayOutputStream();
       byte[] buffer = new byte[2048];
       int length;
       while ((length = dataInputStream.read(buffer)) > 0) {
         outputStream.write(buffer, 0, length);
       }
       byte[] context = outputStream.toByteArray();

       //将图片数据还原为图片
       inputStream = new ByteArrayInputStream(context);
       BufferedImage image = ImageIO.read(inputStream);
       int imageSize = context.length;
       int type = image.getType();
       int height = image.getHeight();
       int width = image.getWidth();

       //将图片底下文字切除
       image = image.getSubimage(0, 0, width, height - subHeight);
       //重新计算图片大小
       outputStream.reset();
       ImageIO.write(image, "JPEG", outputStream);
       imageSize = outputStream.toByteArray().length;

       BufferedImage tempImage;
       //判断文件大小是否大于size,循环压缩，直到大小小于给定的值
       while (imageSize > sizeLimit) {
         //将图片长宽压缩到原来的90%
         height = new Double(height * 0.9).intValue();
         width = new Double(width * 0.9).intValue();
         tempImage = new BufferedImage(width, height, type);
         // 绘制缩小后的图
         tempImage.getGraphics().drawImage(image, 0, 0, width, height, null);
         //重新计算图片大小
         outputStream.reset();
         ImageIO.write(tempImage, "JPEG", outputStream);
         imageSize = outputStream.toByteArray().length;
       }

       //将图片转化为base64并返回
       byte[] data = outputStream.toByteArray();
       //此处一定要使用org.apache.tomcat.util.codec.binary.Base64，防止再linux上出现换行等特殊符号
       base64Image = Base64.encodeBase64String(data);
     } catch (Exception e) {
       //抛出异常
       throw e;
     } finally {
       if (dataInputStream != null) {
         try {
           dataInputStream.close();
         } catch (IOException e) {
           e.printStackTrace();
         }
       }
       if (outputStream != null) {
         try {
           outputStream.close();
         } catch (IOException e) {
           e.printStackTrace();
         }
       }
       if (inputStream != null) {
         try {
           inputStream.close();
         } catch (IOException e) {
           e.printStackTrace();
         }
       }
     }
     return base64Image;
   }

   public static void main(String[] args) throws IOException
   {
//     String imgurl = "http://127.0.0.1:8090/image/Wz/20191007/196559";
     //String bf = GetImageStrFromUrl(imgurl);
     //System.out.println("before:" + bf.length());
//     String af = convertImageToBase64(imgurl, 64,1024);
//     System.out.println("after:" + af.length());
     //boolean flag1 = Base64Utils.GenerateImage(bf, "C:/Users/Adamlawen/Desktop/before.jpg");
//     boolean flag2 = Base64Utils.GenerateImage(af, "C:/Users/Adamlawen/Desktop/after.jpg");
     //System.out.println(flag1);
//     System.out.println(flag2);
//     long startTime = System.currentTimeMillis();
//     int count = 0;
//     for(int i = 0; i<100; i++) {
//       convertToBase64FromLocal("F:\\未系安全带项目\\安全带120系24未系\\安全带120系24未系\\未系安全带\\4500018000000_2018-06-25 120913_桂AE288S-5731515-0.jpg");
//       count++;
//     }
//     long endTime = System.currentTimeMillis();
//     float excTime = (float) (endTime - startTime);
//     System.out.println("执行次数："+ count +"，总执行时间：" + excTime + "ms");


     String base64Str = Base64Utils.GetImageStrFromUrl("http://127.0.0.1:81/图片/4500018000000_2018-06-25 120424_桂A0051C-5727374-0.jpg");
     System.out.println(base64Str);
   }

   public static MultipartFile base64ToMultipart(String base64)
     throws IOException
   {
     String[] baseStrs = base64.split(",");

     BASE64Decoder decoder = new BASE64Decoder();
     byte[] b = new byte[0];
     b = decoder.decodeBuffer(baseStrs[1]);

     for (int i = 0; i < b.length; i++) {
       if (b[i] < 0) {
         int tmp47_45 = i; byte[] tmp47_44 = b;tmp47_44[tmp47_45] = ((byte)(tmp47_44[tmp47_45] + 256));
       }
     }

     return new BASE64DecodedMultipartFile(b, baseStrs[0]);
   }

   public static boolean GenerateImage(String base64str, String savepath)
   {
     if (base64str == null) {
       return false;
     }
     BASE64Decoder decoder = new BASE64Decoder();

     try
     {
       byte[] b = decoder.decodeBuffer(base64str);

       for (int i = 0; i < b.length; i++)
       {
         if (b[i] < 0)
         {
           int tmp40_38 = i; byte[] tmp40_37 = b;tmp40_37[tmp40_38] = ((byte)(tmp40_37[tmp40_38] + 256));
         }
       }

       //创建目录
       String dirPath = savepath.substring(0, savepath.lastIndexOf("/"));
       File dir = new File(dirPath);
       if (!dir.exists()) {
         dir.mkdirs();
       }

       File saveFile = new File(savepath);
       if(!saveFile.exists()){
         saveFile.createNewFile();
       }
       //OutputStream out = new FileOutputStream(savepath);
       OutputStream out = new FileOutputStream(saveFile);
       out.write(b);
       out.flush();
       out.close();
       return true;
     }
     catch (Exception e) {}

     return false;
   }

   public static String convertToBase64FromLocal(String filePath){
     long startTime = System.currentTimeMillis();
     try {
       InputStream inStream = new FileInputStream(filePath);
       byte[] result = null;
       result = new byte[inStream.available()];
       inStream.read(result);
       inStream.close();

       BASE64Encoder encoder = new BASE64Encoder();

       String base64str = encoder.encode(result);

       long endTime = System.currentTimeMillis();
       float excTime = (float) (endTime - startTime);
       //System.out.println(base64str);
       //System.out.println("执行时间：" + excTime + "ms");
       return base64str;
     }catch (Exception e){
       e.printStackTrace();
       return "";
     }
   }
 }