package com.example.somecode.file;

import com.example.somecode.io.CustomFileException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Slf4j
public class FileCustomUtil {


    /**
     * 递归查找某一路径下的所有文件路径
     * @param folderPath 文件路径
     * @return --
     */
    public static List<String> listAllFilePath(String folderPath) {
        List<String> filePathList = new ArrayList<>();

        // 创建一个File对象，表示要查找的文件夹
        File folder = new File(folderPath);
        if(!folder.exists()) {
            throw new CustomFileException("listAllFilePath error, file is not exists...");
        }

        // 获取文件夹中的所有文件和子文件夹
        File[] files = folder.listFiles();
        for (File file : files) {
            // 判断当前项是否为文件
            if (file.isFile()) {
                // 添加文件名到列表中
                filePathList.add(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                // 如果是文件夹，递归调用listAllFiles方法来获取子文件夹中的文件名
                List<String> subFolderFilePaths = listAllFilePath(file.getAbsolutePath());
                filePathList.addAll(subFolderFilePaths);
            }
        }

        return filePathList;
    }

    /**
     * 解压zip文件
     * @param srcFile srcFile
     * @param destDirPath destDirPath
     * @return Boolean
     * @throws RuntimeException --
     */
    public Boolean unZip(File srcFile, String destDirPath) throws RuntimeException {
        System.out.println("unZip destDirPath :" + destDirPath);
        long start = System.currentTimeMillis();
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
        }
        // 开始解压
        ZipFile zipFile = null;
        try {
            FileUtils.cleanDirectory(new File(destDirPath));
            zipFile = new ZipFile(srcFile);
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                System.out.println("解压" + entry.getName());

                // 如果是文件夹，就创建个文件夹
                if (entry.isDirectory()) {
                    String dirPath = destDirPath + "/" + entry.getName();
                    File dir = new File(dirPath);
                    dir.mkdirs();
                } else {
                    // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                    File targetFile = new File(destDirPath + "/" + entry.getName());
                    // 保证这个文件的父文件夹必须要存在
                    if(!targetFile.getParentFile().exists()){
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
            long end = System.currentTimeMillis();
            System.out.println("解压完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("unzip error from ZipUtils", e);
        } finally {
            if(zipFile != null){
                try {
                    zipFile.close();
                } catch (IOException e) {
                    log.error("unZip close error...", e);
                }
            }
        }

        return Boolean.TRUE;
    }

    /**
     * 递归查找某一路径下的所有文件名
     * @param folderPath 文件路径
     * @return --
     */
    public static List<String> listAllFiles(String folderPath) {
        List<String> fileList = new ArrayList<>();

        // 创建一个File对象，表示要查找的文件夹
        File folder = new File(folderPath);

        // 检查文件夹是否存在
        if (folder.exists() && folder.isDirectory()) {
            // 获取文件夹中的所有文件和子文件夹
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    // 判断当前项是否为文件
                    if (file.isFile()) {
                        // 添加文件名到列表中
                        fileList.add(file.getName());
                    } else if (file.isDirectory()) {
                        // 如果是文件夹，递归调用listAllFiles方法来获取子文件夹中的文件名
                        List<String> subFolderFiles = listAllFiles(file.getAbsolutePath());
                        fileList.addAll(subFolderFiles);
                    }
                }
            }
        } else {
            fileList.add("文件夹不存在或不是一个文件夹");
        }
        return fileList;
    }

    /**
     * 递归查找某一路径下的所有文件名并进行去重
     * @param folderPath
     * @return
     */
    public static Set<String> listUniqueFiles(String folderPath) {
        Set<String> uniqueFileNames = new HashSet<>();

        // 创建一个File对象，表示要查找的文件夹
        File folder = new File(folderPath);

        // 检查文件夹是否存在
        if (folder.exists() && folder.isDirectory()) {
            // 获取文件夹中的所有文件和子文件夹
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    // 判断当前项是否为文件
                    if (file.isFile()) {
                        // 添加文件名到Set集合中
                        uniqueFileNames.add(file.getName());
                    } else if (file.isDirectory()) {
                        // 如果是文件夹，递归调用listUniqueFiles方法来获取子文件夹中的不重复文件名
                        Set<String> subFolderFiles = listUniqueFiles(file.getAbsolutePath());
                        uniqueFileNames.addAll(subFolderFiles);
                    }
                }
            }
        } else {
            uniqueFileNames.add("文件夹不存在或不是一个文件夹");
        }
        return uniqueFileNames;
    }

    /**
     * 递归查找某一路径下指定类型的所有文件名
     * @param folderPath --
     * @param fileType --
     * @return --
     */
    public static List<String> listAllFiles(String folderPath, final String fileType) {
        List<String> fileList = new ArrayList<>();

        // 创建一个File对象，表示要查找的文件夹
        File folder = new File(folderPath);

        // 检查文件夹是否存在
        if (folder.exists() && folder.isDirectory()) {
            // 获取文件夹中的所有文件和子文件夹
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    // 判断当前项是否为文件
                    if (file.isFile()) {
                        // 判断文件名是否以指定的文件类型结尾
                        if (file.getName().endsWith(fileType)) {
                            // 添加符合条件的文件名到列表中
                            fileList.add(file.getName());
                        }
                    } else if (file.isDirectory()) {
                        // 如果是文件夹，递归调用listFilesByType方法来获取子文件夹中的文件名
                        List<String> subFolderFiles = listAllFiles(file.getAbsolutePath(), fileType);
                        fileList.addAll(subFolderFiles);
                    }
                }
            }
        } else {
            fileList.add("文件夹不存在或不是一个文件夹");
        }
        return fileList;
    }

    /**
     * 递归查找某一路径下匹配文件类型的所有文件名（去重）
     * @param folderPath --
     * @param fileType --
     * @return --
     */
    public static Set<String> listFilesByTypeWithSet(String folderPath, String fileType) {
        Set<String> uniqueFileNames = new HashSet<>();

        // 创建一个File对象，表示要查找的文件夹
        File folder = new File(folderPath);

        // 检查文件夹是否存在
        if (folder.exists() && folder.isDirectory()) {
            // 获取文件夹中的所有文件和子文件夹
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    // 判断当前项是否为文件
                    if (file.isFile()) {
                        // 判断文件名是否以指定的文件类型结尾
                        if (file.getName().endsWith(fileType)) {
                            // 添加符合条件的文件名到Set集合中
                            uniqueFileNames.add(file.getName());
                        }
                    } else if (file.isDirectory()) {
                        // 如果是文件夹，递归调用listFilesByTypeWithSet方法来获取子文件夹中的不重复文件名
                        Set<String> subFolderFiles = listFilesByTypeWithSet(file.getAbsolutePath(), fileType);
                        uniqueFileNames.addAll(subFolderFiles);
                    }
                }
            }
        } else {
            uniqueFileNames.add("文件夹不存在或不是一个文件夹");
        }

        return uniqueFileNames;
    }

    /**
     * 递归查找某一路径下的所有文件数量
     * @param folderPath --
     * @return --
     */
    public static int countFiles(String folderPath) {
        int fileCount = 0;

        // 创建一个File对象，表示要统计的文件夹
        File folder = new File(folderPath);

        // 检查文件夹是否存在
        if (folder.exists() && folder.isDirectory()) {
            // 获取文件夹中的所有文件和子文件夹
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    // 判断当前项是否为文件
                    if (file.isFile()) {
                        // 如果是文件，增加文件计数
                        fileCount++;
                    } else if (file.isDirectory()) {
                        // 如果是文件夹，递归调用countFiles方法来获取子文件夹中的文件数量
                        fileCount += countFiles(file.getAbsolutePath());
                    }
                }
            }
        }
        return fileCount;
    }

    /**
     * 递归统计某一路径下所有指定文件类型的数量
     * @param folderPath --
     * @param fileType --
     * @return --
     */
    public static int countFilesByType(String folderPath, String fileType) {
        int fileCount = 0;

        // 创建一个File对象，表示要统计的文件夹
        File folder = new File(folderPath);

        // 检查文件夹是否存在
        if (folder.exists() && folder.isDirectory()) {
            // 获取文件夹中的所有文件和子文件夹
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    // 判断当前项是否为文件
                    if (file.isFile()) {
                        // 判断文件名是否以指定的文件类型结尾
                        if (file.getName().endsWith(fileType)) {
                            // 增加文件计数
                            fileCount++;
                        }
                    } else if (file.isDirectory()) {
                        // 如果是文件夹，递归调用countFilesByType方法来获取子文件夹中的文件数量
                        fileCount += countFilesByType(file.getAbsolutePath(), fileType);
                    }
                }
            }
        }
        return fileCount;
    }

    /**
     * 递归删除某一路径下所有文件（包括该文件夹以及所有文件夹）
     * @param folderPath --
     * @return --
     */
    public static boolean deleteFolder(String folderPath) {
        // 创建一个File对象，表示要删除的文件夹
        File folder = new File(folderPath);

        // 检查文件夹是否存在
        if (folder.exists() && folder.isDirectory()) {
            // 获取文件夹中的所有文件和子文件夹
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    // 判断当前项是否为文件
                    if (file.isFile()) {
                        // 删除文件
                        file.delete();
                    } else if (file.isDirectory()) {
                        // 如果是文件夹，递归调用deleteFolder方法来删除子文件夹及其内容
                        deleteFolder(file.getAbsolutePath());
                    }
                }
            }

            // 删除空文件夹
            folder.delete();
            return true;
        } else {
            return false;
        }
    }

}
