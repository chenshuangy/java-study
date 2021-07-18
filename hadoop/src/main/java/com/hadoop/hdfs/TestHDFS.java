package com.hadoop.hdfs;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

/**
 * @author chenshuangyan <chenshuangyan@baijiahulian.com>
 * Created on 2021-07-18
 */
public class TestHDFS {

    public static Configuration configuration = null;

    public static FileSystem fs = null;


    public void initConn() throws URISyntaxException, IOException, InterruptedException {
        configuration = new Configuration();
        fs = FileSystem.get(new URI("hdfs://mycluster"),configuration,"root"); //最后一个参数为用户名
    }

    /**
     * 创建文件夹
     * @throws IOException
     */
    public void mkdir() throws IOException {
        Path dir = new Path("/data");
        if (fs.isDirectory(dir)){
            fs.delete(dir, true);
        }
        fs.mkdirs(dir);
    }

    /**
     * 创建文件
     * @throws IOException
     */
    public void createFile() throws IOException {
        FSDataOutputStream outputStream = fs.create(new Path("hdfs:/data/hello.txt"));
        // 写入文件内容
        outputStream.write("hello world \\n hello java".getBytes());
        outputStream.close();
    }

    /**
     * 上传文件
     * @throws Exception
     */
    public void upload() throws Exception {

        BufferedInputStream input = new BufferedInputStream(new FileInputStream(new File("hadoop/data/hello.txt")));
        Path outfile   = new Path("/data/hello1.txt");
        FSDataOutputStream output = fs.create(outfile);
        IOUtils.copyBytes(input,output,configuration,true);
    }


    /**
     * 读取数据
     * @throws Exception
     */
    public void blocks() throws Exception {

        Path file = new Path("/data/hello1.txt");
        FileStatus fss = fs.getFileStatus(file);
        BlockLocation[] blks = fs.getFileBlockLocations(fss, 0, fss.getLen());
        for (BlockLocation b : blks) {
            System.out.println(b);
        }
        //        0,        1048576,        node04,node02  A
        //        1048576,  540319,         node04,node03  B
        //计算向数据移动~！
        //其实用户和程序读取的是文件这个级别~！并不知道有块的概念~！
        FSDataInputStream in = fs.open(file);  //面向文件打开的输入流  无论怎么读都是从文件开始读起~！

        //        blk01: he
        //        blk02: llo msb 66231

        // block的偏移量
        in.seek(0);
        //计算向数据移动后，期望的是分治，只读取自己关心（通过seek实现），同时，具备距离的概念（优先和本地的DN获取数据--框架的默认机制）
        System.out.println((char)in.readByte());
        System.out.println((char)in.readByte());
        System.out.println((char)in.readByte());
        System.out.println((char)in.readByte());
        System.out.println((char)in.readByte());
        System.out.println((char)in.readByte());
        System.out.println((char)in.readByte());
        System.out.println((char)in.readByte());
        System.out.println((char)in.readByte());
        System.out.println((char)in.readByte());
        System.out.println((char)in.readByte());
        System.out.println((char)in.readByte());
    }

    public static void main(String[] args) throws Exception {
        TestHDFS testHDFS = new TestHDFS();
        testHDFS.initConn();
        testHDFS.mkdir();
        testHDFS.createFile();
        testHDFS.upload();
        testHDFS.blocks();
        fs.close();
    }


}
