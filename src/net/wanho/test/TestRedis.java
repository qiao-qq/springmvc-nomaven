package net.wanho.test;

import com.alibaba.fastjson.JSONArray;
import net.wanho.system.Utils;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class TestRedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("master", 6379);
//        jedis.set("name","zhangxiaoli");
//        String name = jedis.get("name");


        List<String> months1 = new ArrayList<>();
        months1.add("2016-01");
        months1.add("2016-02");
        months1.add("2016-03");
//
//
//        List<String> counts = new ArrayList<>();
//        counts.add("135");
//        counts.add("1543");
//        counts.add("432");
//
//        jedis.set("months",JSONArray.toJSONString(months));
        String ret1 = JSONArray.toJSONString(jedis.get("months"));
        String ret = JSONArray.toJSONString(months1);

        List<String> months =  Utils.jsonToList(ret,String.class);





        jedis.close();
    }


}

