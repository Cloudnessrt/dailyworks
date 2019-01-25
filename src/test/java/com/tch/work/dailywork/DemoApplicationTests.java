package com.tch.work.dailywork;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.*;

import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    List<POI> list=new ArrayList();
    @Test
    public void contextLoads() {

        HttpPost post = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();
            String url="http://10.5.74.49:8080/api/global/json/getPoiDetail";
            post = new HttpPost(url);
            // 构造消息头
            post.setHeader("Content-type", "application/json; charset=utf-8");
            post.setHeader("SOA20-Client-AppId", "760101");

            // 构建消息实体
            StringEntity entity = new StringEntity(body, Charset.forName("UTF-8"));
            entity.setContentEncoding("UTF-8");
            // 发送Json格式的数据请求
            entity.setContentType("application/json");
            post.setEntity(entity);

            HttpResponse response = httpClient.execute(post);

            // 检验返回码
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode != HttpStatus.SC_OK) {
                System.out.printf(" false");
            }else{
                String str = "";
                /**读取服务器返回过来的json字符串数据**/
                str = EntityUtils.toString(response.getEntity());
                POIResult json_test = JSONObject.parseObject(str,POIResult.class);
                list.addAll(json_test.getResult());

                String [] a={"poiId","businessId","name","districtId","districtName"};
                Map<String,String> map=new HashMap();
                for(int i=0;i<a.length;i++){
                    map.put(a[i],a[i]);
                }
                ExportExcel.excelExport(list,map,"poi");
            }
            }
            catch (Exception e){

            }
    }

    static  String body="{\n" +
            "    \"poiIds\":[\n" +
            "    \t10523391\t,\n" +
            "10559094\t,\n" +
            "80294\t,\n" +
            "18700865\t,\n" +
            "10530177\t,\n" +
            "80574\t,\n" +
            "10644492\t,\n" +
            "80575\t,\n" +
            "80296\t,\n" +
            "24140241\t,\n" +
            "98333\t,\n" +
            "23868376\t,\n" +
            "76012\t,\n" +
            "10524148\t,\n" +
            "85219\t,\n" +
            "10524147\t,\n" +
            "90778\t,\n" +
            "84620\t,\n" +
            "87802\t,\n" +
            "10758354\t,\n" +
            "10524151\t,\n" +
            "76014\t,\n" +
            "81947\t,\n" +
            "76019\t,\n" +
            "82116\t,\n" +
            "76033\t,\n" +
            "76032\t,\n" +
            "81943\t,\n" +
            "81942\t,\n" +
            "81945\t,\n" +
            "76060\t,\n" +
            "84889\t,\n" +
            "97655\t,\n" +
            "10558311\t,\n" +
            "76037\t,\n" +
            "76061\t,\n" +
            "94434\t,\n" +
            "23058059\t,\n" +
            "76810\t,\n" +
            "31044673\t,\n" +
            "82839\t,\n" +
            "81974\t,\n" +
            "95058\t,\n" +
            "76047\t,\n" +
            "94488\t,\n" +
            "82844\t,\n" +
            "10520728\t,\n" +
            "84722\t,\n" +
            "30464880\t,\n" +
            "10572520\t,\n" +
            "86685\t,\n" +
            "78807\t,\n" +
            "10559113\t,\n" +
            "92647\t,\n" +
            "78805\t,\n" +
            "10558700\t,\n" +
            "78816\t,\n" +
            "78808\t,\n" +
            "10558880\t,\n" +
            "89541\t,\n" +
            "91999\t,\n" +
            "78820\t,\n" +
            "78814\t,\n" +
            "92016\t,\n" +
            "92000\t,\n" +
            "78804\t,\n" +
            "78823\t,\n" +
            "94795\t,\n" +
            "10758467\t,\n" +
            "80615\t,\n" +
            "98700\t,\n" +
            "101913\t,\n" +
            "10570019\t,\n" +
            "15131377\t,\n" +
            "82520\t,\n" +
            "76133\t,\n" +
            "76137\t,\n" +
            "13086709\t,\n" +
            "76129\t,\n" +
            "10354609\t,\n" +
            "13345853\t,\n" +
            "10524277\t,\n" +
            "22847619\t,\n" +
            "10758882\t\n" +
            "\t]\n" +
            "}";



}
