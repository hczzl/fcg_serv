package com.glch.base.util.base64;

import java.io.FileOutputStream;
import java.io.IOException;

public class BASE64DecodedMultipartFile implements org.springframework.web.multipart.MultipartFile
{
   private final byte[] imgContent;
   private final String header;

   public BASE64DecodedMultipartFile(byte[] imgContent, String header)
   {
     this.imgContent = imgContent;
     this.header = header.split(";")[0];
   }

   public String getName()
   {
     return System.currentTimeMillis() + Math.random() + "." + this.header.split("/")[1];
   }

   public String getOriginalFilename()
   {
     return System.currentTimeMillis() + (int)Math.random() * 10000 + "." + this.header.split("/")[1];
   }

   public String getContentType()
   {
     return this.header.split(":")[1];
   }

   public boolean isEmpty()
   {
     return (this.imgContent == null) || (this.imgContent.length == 0);
   }

   public long getSize()
   {
     return this.imgContent.length;
   }

   public byte[] getBytes() throws IOException
   {
     return this.imgContent;
   }

   public java.io.InputStream getInputStream() throws IOException
   {
     return new java.io.ByteArrayInputStream(this.imgContent);
   }

   public void transferTo(java.io.File file) throws IOException, IllegalStateException
   {
     new FileOutputStream(file).write(this.imgContent);
   }
}