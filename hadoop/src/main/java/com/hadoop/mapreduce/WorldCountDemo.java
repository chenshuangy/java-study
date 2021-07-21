package com.hadoop.mapreduce;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 * @author chenshuangyan <chenshuangyan@baijiahulian.com>
 * Created on 2021-07-20
 */
public class WorldCountDemo {

    public static void main(String[] args)
            throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {

        // 加载配置文件
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        FileSystem fs = FileSystem.get(new URI("hdfs://mycluster"),conf,"root");

        // 在本地执行，需要给出jar包的地址
        job.setJar("/Users/chenshuangyan/Documents/study/project/java-study/hadoop/target/hadoop-1.0-SNAPSHOT.jar");
        // 指定启动的main函数
        job.setJarByClass(WorldCountDemo.class);

        // 设置jobname的名称
        job.setJobName("WorldCountDemo");

        // 设置文件路径以及类型
        Path infile = new Path("/data/hello1.txt");
        TextInputFormat.addInputPath(job, infile);

        Path outfile = new Path("/data/wc_output");
        if (outfile.getFileSystem(conf).exists(outfile)) {
            outfile.getFileSystem(conf).delete(outfile, true);
        }
        TextOutputFormat.setOutputPath(job, outfile);

        job.setMapperClass(MyMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setReducerClass(MyReducer.class);

        job.waitForCompletion(true);
        System.out.println("任务结束");


    }
}
