package net.wanho.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.wanho.system.Utils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService {

    static Jedis jedis = new Jedis("master", 6379);

    public boolean analyse()throws Exception
    {
        Map<String,AtomicInteger> map = Utils.countWordInDoc("D:\\大数据上课文件\\阿力\\资料\\user_login.txt",",");
        String months = JSONArray.toJSONString(map.keySet());
        String counts = JSONArray.toJSONString(map.values());
        jedis.set("months",months);
        jedis.set("counts",counts);
        jedis.set("status","200");
        return true;
    }

    public JSONObject query()throws Exception {

        JSONObject jsonObject = new JSONObject();
        String status = jedis.get("status");
        if(status==null||"".equals(status.trim()))//說明此時的分析沒有結束，不允許查詢
        {
            jsonObject.put("status",100);
            jsonObject.put("msg","analyse not completed");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        analyse();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            return jsonObject;
        }

        String ret = jedis.get("months");

        List<String> months =  Utils.jsonToList(ret,String.class);
        ret = jedis.get("counts");
        List<String> counts =  Utils.jsonToList(ret,String.class);

        jsonObject.put("months",months);
        jsonObject.put("counts",counts);
        jsonObject.put("status",200);
        return jsonObject;

    }
}

