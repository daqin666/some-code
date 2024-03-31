package com.example.somecode.file.rar;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UnrarUtil {
    /**
     * zip文件解压
     * @param inputFile  待解压文件夹/文件
     * @param destDirPath  解压路径
     */
    public static void unZipFiles(String inputFile,String destDirPath) throws Exception {
        File srcFile = new File(inputFile);//获取当前压缩文件
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new Exception(srcFile.getPath() + "所指文件不存在");
        }
        ZipFile zipFile = new ZipFile(srcFile, Charset.forName("GBK"));//创建压缩文件对象
        //开始解压
        Enumeration<?> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            // 如果是文件夹，就创建个文件夹
            if (entry.isDirectory()) {
                String dirPath = destDirPath + "/" + entry.getName();
                srcFile.mkdirs();
            } else {
                // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                File targetFile = new File(destDirPath + "/" + entry.getName());
                // 保证这个文件的父文件夹必须要存在
                if (!targetFile.getParentFile().exists()) {
                    targetFile.getParentFile().mkdirs();
                }
                targetFile.createNewFile();
                // 将压缩文件内容写入到这个文件中
                InputStream is = zipFile.getInputStream(entry);
                FileOutputStream fos = new FileOutputStream(targetFile);
                int len;
                byte[] buf = new byte[1024];
                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
                // 关流顺序，先打开的后关闭
                fos.close();
                is.close();
            }
        }
    }

    /**
     * 解压RAR压缩文件到指定路径
     * @param rarPath RAR压缩文件
     * @param dstDir 解压到的文件夹路径
     */
    public static void unRarFile(String rarPath, String dstDir) throws Exception {

        File dstDiretory = new File(dstDir);
        if (!dstDiretory.exists()) {
            dstDiretory.mkdirs();
        }
        try {
            File rarFile= new File(rarPath);
            Archive archive = new Archive(new FileInputStream(rarFile));
            List<FileHeader> fileHeaders = archive.getFileHeaders();
            for (FileHeader fileHeader : fileHeaders) {
                if (fileHeader.isDirectory()) {
                    String fileName=  fileHeader.getFileNameW();
                    if(!existZH(fileName)){
                        fileName = fileHeader.getFileNameString();
                    }
                    File dir = new File(dstDir + File.separator + fileName);
                    if (!dir.exists()){
                        dir.mkdirs();
                    }
                } else {
                    String fileName=  fileHeader.getFileNameW().trim();
                    if(!existZH(fileName)){
                        fileName = fileHeader.getFileNameString().trim();
                    }
                    File file = new File(dstDir + File.separator + fileName);
                    try {
                        if (!file.exists()) {
                            if (!file.getParentFile().exists()) {
                                file.getParentFile().mkdirs();
                            }
                            file.createNewFile();
                        }
                        FileOutputStream os = new FileOutputStream(file);
                        archive.extractFile(fileHeader, os);
                        os.close();
                    } catch (Exception ex) {
                        throw ex;
                    }
                }
            }
            archive.close();
        } catch (Exception e) {
            throw e;
        }
    }
    public static boolean existZH(String str) {
        String regEx = "[\\u4e00-\\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        while (m.find()) {
            return true;
        }
        return false;
    }


    //使用main方法进行测试
    public static void main(String[] args) {
        try {
            String filepath = "E:\\test\\测试1.rar";
            String newpath="E:\\test\\zipTest";
            //获取最后一个.的位置
            int lastIndexOf = filepath.lastIndexOf(".");
            //获取文件的后缀名 .jpg
            String suffix = filepath.substring(lastIndexOf);
            System.out.println(suffix);
            if(suffix.equals(".zip")){
                unZipFiles(filepath,newpath);

            }else if(suffix.equals(".rar")){
                unRarFile(filepath,newpath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
