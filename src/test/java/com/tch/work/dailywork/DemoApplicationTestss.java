package com.tch.work.dailywork;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTestss {

    List<Locations> list=new ArrayList();
    @Test
    public void contextLoads() throws IOException {

        HttpPost post = null;

        HttpClient httpClient = new DefaultHttpClient();
        String url = "http://m.ctrip.com/restapi/soa2/13373/map.json";
        post = new HttpPost(url);
        // 构造消息头
        post.setHeader("Content-type", "application/json; charset=utf-8");
        post.setHeader("SOA20-Client-AppId", "760101");
        String[] array = lists.split(",");
        try {
            for (int i = 0; i < array.length; i++) {
                // 构建消息实体
                StringEntity entity = new StringEntity(body.replace("{0}",array[i]), Charset.forName("UTF-8"));
                entity.setContentEncoding("UTF-8");
                // 发送Json格式的数据请求
                entity.setContentType("application/json");
                post.setEntity(entity);

                HttpResponse response = httpClient.execute(post);

                // 检验返回码
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    System.out.printf(" false");
                } else {
                    String str = "";
                    /**读取服务器返回过来的json字符串数据**/
                    str = EntityUtils.toString(response.getEntity());
                    ExchangeResult json_test = JSONObject.parseObject(str, ExchangeResult.class);
                    Optional<Locations> temp=json_test.getLocations().stream()
                            .filter((p) -> ("6".equals(p.getGeocategoryid())))
                            .filter((a) -> ("gs_district".equals(a.getType()))).findFirst();
                    Optional<Locations> hoteltemp=json_test.getLocations().stream()
                            .filter((p) -> ("3".equals(p.getGeocategoryid())))
                            .filter((a) -> ("base".equals(a.getType()))).findFirst();
                    Locations locations= exchange(temp,hoteltemp);
                    locations.setHotelid(array[i]);
                    list.add(locations);
                    System.out.printf(i+"_"+array[i]+"_"+locations.getGlobalid()+","+locations.getHotelid()+"\n");
                }
            }
        } catch (Exception e) {
            throw e;
        }
        String[] a = {"globalid", "name","hotelid","hotelname"};
        Map<String, String> map = new HashMap();
        for (int i = 0; i < a.length; i++) {
            map.put(a[i], a[i]);
        }
        ExportExcel.excelExport(list, map, "trip已开城市清单");
    }

    private Locations exchange(Optional<Locations> a1,Optional<Locations> a2){
        Locations locations=new Locations();
        if(a1!=null && a1.isPresent()){
            locations=a1.get();
            if(a2!=null && a2.isPresent()){
                locations.setHotelid(a2.get().getGlobalid());
                locations.setHotelname(a2.get().getName());
            }else{
                locations=a1.get();
            }
        }else{
            if(a2!=null && a2.isPresent()){
                locations.setHotelid(a2.get().getGlobalid());
                locations.setHotelname(a2.get().getName());
            }else{
                int i=-1;
            }
        }
        return locations;
    }

    private  String body="{\n" +
            "    \"head\":{\n" +
            "        \"syscode\":\"String\",\n" +
            "        \"lang\":\"String\",\n" +
            "        \"auth\":\"String\",\n" +
            "        \"cid\":\"String\",\n" +
            "        \"ctok\":\"String\",\n" +
            "        \"cver\":\"String\",\n" +
            "        \"sid\":\"String\",\n" +
            "        \"extension\":[\n" +
            "            {\n" +
            "                \"name\":\"String\",\n" +
            "                \"value\":\"String\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"pauth\":\"String\",\n" +
            "        \"sauth\":\"String\",\n" +
            "        \"appid\":\"String\"\n" +
            "    },\n" +
            "    \"globalid\":{0},\n" +
            "    \"geocategoryid\":3,\n" +
            "    \"type\":\"base\"\n" +
            "}";

    private String lists="58," +
            "59," +
            "73," +
            "219," +
            "228," +
            "359," +
            "617," +
            "622," +
            "623," +
            "725," +
            "734," +
            "1229," +
            "4126," +
            "176," +
            "641," +
            "248," +
            "207," +
            "253," +
            "301," +
            "524," +
            "286," +
            "1231," +
            "476," +
            "720," +
            "248," +
            "207," +
            "723," +
            "807," +
            "220," +
            "332," +
            "803," +
            "651," +
            "501," +
            "358," +
            "1210," +
            "769," +
            "260," +
            "250," +
            "193," +
            "4054," +
            "192," +
            "35815," +
            "274," +
            "253," +
            "176," +
            "476," +
            "461," +
            "303," +
            "1369," +
            "1288," +
            "315," +
            "625," +
            "1225," +
            "1393," +
            "347," +
            "633," +
            "313," +
            "26282," +
            "549," +
            "26848," +
            "1186," +
            "511," +
            "813," +
            "522," +
            "1360," +
            "812," +
            "683," +
            "684," +
            "304," +
            "1231," +
            "641," +
            "360," +
            "262," +
            "207," +
            "248," +
            "420," +
            "434," +
            "1405," +
            "532," +
            "357," +
            "40795," +
            "3576," +
            "3567," +
            "710," +
            "678," +
            "7505," +
            "343," +
            "687," +
            "1262," +
            "688," +
            "361," +
            "524," +
            "338," +
            "722," +
            "301," +
            "286," +
            "1356," +
            "1775," +
            "1776," +
            "1777," +
            "3849," +
            "2," +
            "1," +
            "30," +
            "32," +
            "17," +
            "28," +
            "25," +
            "10," +
            "34," +
            "12," +
            "33," +
            "4," +
            "27," +
            "5," +
            "3," +
            "43," +
            "41," +
            "7," +
            "67," +
            "31," +
            "32," +
            "501," +
            "720," +
            "3847," +
            "31," +
            "1239," +
            "1356," +
            "623," +
            "725," +
            "622," +
            "11113," +
            "358," +
            "7250," +
            "617," +
            "58," +
            "274," +
            "726," +
            "301," +
            "725," +
            "723," +
            "315," +
            "35831," +
            "7250," +
            "360," +
            "262," +
            "50150," +
            "1165," +
            "3783," +
            "726," +
            "753," +
            "725," +
            "723," +
            "35831," +
            "192," +
            "343," +
            "361," +
            "1288," +
            "687," +
            "688," +
            "710," +
            "250," +
            "196," +
            "3576," +
            "9187," +
            "1262," +
            "193," +
            "420," +
            "366," +
            "775," +
            "776," +
            "363," +
            "637," +
            "357," +
            "176," +
            "651," +
            "706," +
            "666," +
            "798," +
            "1231," +
            "532," +
            "260," +
            "721," +
            "803," +
            "434," +
            "831," +
            "3901," +
            "1343," +
            "739," +
            "717," +
            "1350," +
            "277," +
            "3713," +
            "826," +
            "1483," +
            "1214," +
            "617," +
            "58," +
            "59," +
            "32," +
            "274," +
            "253," +
            "737," +
            "286," +
            "301," +
            "1775," +
            "1356," +
            "359," +
            "623," +
            "1229," +
            "1405," +
            "622," +
            "810," +
            "364," +
            "304," +
            "315," +
            "1393," +
            "723," +
            "495," +
            "1369," +
            "303," +
            "501," +
            "358," +
            "728," +
            "1210," +
            "681," +
            "4497," +
            "680," +
            "1446," +
            "1243," +
            "682," +
            "1444," +
            "61019," +
            "3860," +
            "678," +
            "1807," +
            "6117," +
            "843," +
            "3716," +
            "26282," +
            "313," +
            "347," +
            "1186," +
            "26363," +
            "26848," +
            "549," +
            "698," +
            "1187," +
            "25773," +
            "511," +
            "3808," +
            "3803," +
            "1188," +
            "9995," +
            "3228," +
            "27072," +
            "866," +
            "3777," +
            "4235," +
            "4188," +
            "26153," +
            "3791," +
            "1234," +
            "7244," +
            "64284," +
            "699," +
            "1193," +
            "26631," +
            "3779," +
            "476," +
            "461," +
            "62407," +
            "759," +
            "3437," +
            "34043," +
            "20413," +
            "812," +
            "691," +
            "3330," +
            "3206," +
            "807," +
            "813," +
            "220," +
            "1360," +
            "766," +
            "332," +
            "1282," +
            "6233," +
            "633," +
            "50152," +
            "1," +
            "7," +
            "501," +
            "220," +
            "623," +
            "207," +
            "207," +
            "735," +
            "734," +
            "734," +
            "219," +
            "219";

}
