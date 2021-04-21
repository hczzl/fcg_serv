 package com.glch.base.util;

 import org.apache.tools.zip.ZipEntry;
 import org.apache.tools.zip.ZipOutputStream;

 import java.io.BufferedInputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.util.zip.CRC32;
 import java.util.zip.CheckedOutputStream;

 public class FileZipCompressorUtil
 {
   static final int BUFFER = 8192;
   private File zipFile;

   public FileZipCompressorUtil(String pathName)
   {
     this.zipFile = new File(pathName);
   }

   public void compressExe(String srcPathName)
   {
     File file = new File(srcPathName);
     if (!file.exists()) {
       throw new RuntimeException(srcPathName + "不存在！");
     }
     try {
       FileOutputStream fileOutputStream = new FileOutputStream(this.zipFile);
       CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());

       ZipOutputStream out = new ZipOutputStream(cos);
       out.setEncoding("utf-8");
       String basedir = "";
       compressByType(file, out, basedir);
       out.close();
     } catch (Exception e) {
       e.printStackTrace();
     }
   }

   private void compressByType(File file, ZipOutputStream out, String basedir)
   {
     if (file.isDirectory()) {
       compressDirectory(file, out, basedir);
     } else {
       compressFile(file, out, basedir);
     }
   }

   private void compressDirectory(File dir, ZipOutputStream out, String basedir)
   {
     if (!dir.exists()) {
       return;
     }

     File[] files = dir.listFiles();
     for (int i = 0; i < files.length; i++)
     {
       compressByType(files[i], out, basedir + dir.getName() + "/");
     }
   }

   private void compressFile(File file, ZipOutputStream out, String basedir)
   {
     if (!file.exists()) {
       return;
     }
     try {
       BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

       ZipEntry entry = new ZipEntry(basedir + file.getName());
       out.putNextEntry(entry);

       byte[] data = new byte[' '];
       int count; while ((count = bis.read(data, 0, 8192)) != -1) {
         out.write(data, 0, count);
       }
       bis.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 }
