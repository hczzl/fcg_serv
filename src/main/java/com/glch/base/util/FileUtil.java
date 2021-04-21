package com.glch.base.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class FileUtil {

    public static void dowloadFileFromUrl(String imageUrl, String destPath) {
        if (StringUtil.isEmpty(imageUrl)) {
            return;
        }
        //File file=new File(destPath);
        FileOutputStream fos = null;
        FileInputStream fis = null;
        byte[] buf = new byte['ࠀ'];
        int size = 0;
        try {
            //创建输入流对象
            fis = new FileInputStream(imageUrl);
            fos = new FileOutputStream(destPath);
            while ((size = fis.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }
            fos.flush();
            return;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteFileOrDirector(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFileOrDirector(files[i]);
                }
                file.delete();
            }
        }
    }

    public static void createDirsIfNotExist(String filepath) throws Exception {
        File file = new File(filepath);
        String loaclPath = file.getCanonicalPath();
        if (loaclPath.contains("\\")) {
            loaclPath = loaclPath.substring(0, loaclPath.lastIndexOf("\\"));
        }
        File filePath = new File(loaclPath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
    }

    /**
     * 复制文件，不覆盖
     *
     * @param src
     * @param desc
     * @return
     */
    public static boolean copyFile(String src, String desc) {
        File srcFile = new File(src);
        File descFile = new File(desc);
        if (!srcFile.isFile() || desc == null || desc.length() == 0 || descFile.exists()) {
            return false;
        }
        try {
            FileUtil.createDirsIfNotExist(desc);
            FileUtil.createDirsIfNotExist(desc);
        } catch (Exception e) {
            e.printStackTrace();
            descFile.delete();
            return false;
        }
        boolean ok = false;
        InputStream ins = null;
        OutputStream os = null;
        try {
            ins = new FileInputStream(srcFile);
            os = new FileOutputStream(descFile);
            byte[] buffer = new byte[1024 * 1024];
            int byteRead = 0;
            while (byteRead != -1) {
                byteRead = ins.read(buffer);
                if (byteRead > 0) {
                    os.write(buffer, 0, byteRead);
                }
            }
            os.flush();
            os.close();
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (os != null) {
                    ins.close();
                }
            } catch (Exception e1) {
            }
            descFile.delete();
        } finally {
            try {
                if (ins != null) {
                    ins.close();
                }
            } catch (Exception e) {
            }
            try {
                if (os != null) {
                    ins.close();
                }
            } catch (Exception e) {
            }
        }
        return ok;
    }


}
