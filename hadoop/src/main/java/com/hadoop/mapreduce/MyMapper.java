package com.hadoop.mapreduce;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @author chenshuangyan <chenshuangyan@baijiahulian.com>
 * Created on 2021-07-20
 */
public class MyMapper extends Mapper<Object, Text, Text, IntWritable> {


    private final static IntWritable one = new IntWritable(1);

    private Text word = new Text();

    /**
     * @param key 是每一行字符串自己第一个字节面向源文件的偏移量
     * @param value 每一行具体的值
     */
    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {

        StringTokenizer stringTokenizer = new StringTokenizer(value.toString());
        while (stringTokenizer.hasMoreTokens()) {
            word.set(stringTokenizer.nextToken());
            context.write(word, one);
        }
    }
}
