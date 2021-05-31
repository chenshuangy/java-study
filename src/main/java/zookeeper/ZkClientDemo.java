package zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

/**
 * @author chenshuangyan <chenshuangyan@baijiahulian.com>
 * Created on 2021-05-28
 */
public class ZkClientDemo {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("192.168.238.131:2181,192.168.238.132:2181,192.168.238.133:2181");
        zkClient.create("/chenshuangyan/node1", "2021", CreateMode.PERSISTENT);
        zkClient.delete("/chenshuangyan/node1");
        zkClient.close();
    }

}
