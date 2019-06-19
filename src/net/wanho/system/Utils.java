package net.wanho.system;
import com.alibaba.fastjson.JSONArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Utils {
	/**
	 * 统计文件中的单词个数
	 * @param filePath
	 * @param splitValue
	 * @return 如果没有结果，则返回的是一个空集合
	 */
	public static Map<String,AtomicInteger> countWordInDoc(String filePath,String splitValue) {
		//key为单词本身，value为单词出现的个数
		Map<String,AtomicInteger> wordMap = new TreeMap<>();

		try
		{

			//待统计的文件路径
//		String filePath = "C:\\Users\\Administrator\\Desktop\\123.txt";

			File file = new File(filePath);
			//如果文件不存在，则退出，并提示原因
			if(!file.exists())
			{

				return wordMap;
			}

			Reader reader = new FileReader(file);
			BufferedReader buffer = new BufferedReader(reader);
			String line = "";
			while(true)
			{
				line = buffer.readLine();
				if(null==line)//如果为null，则已经读完毕，到了文件末尾
				{
					break;
				}
				String[] line2words = line.split(splitValue);//只会对一个空格进行切割





				if(null==line2words||line2words.length==0)//如果分割之后的数组为空，则进行下一次循环
				{
					continue;
				}


					String month =  line2words[1].substring(0,7);


					if(wordMap.containsKey(month))//如果map中包含key,则直接加1
					{
						wordMap.get(month).incrementAndGet();
					}
					else
					{
						wordMap.put(month,new AtomicInteger(1));//map中不包含key，则首次出现，设置为1
					}
			}

			buffer.close();
			reader.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return wordMap;
	}

	public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
		@SuppressWarnings("unchecked")
		List<T> ts = (List<T>) JSONArray.parseArray(jsonString, clazz);
		return ts;
	}
}
