package com.gqz.wx.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author ：yp
 * @description ：地理信息工具类
 */
public class LocationUtil {
	/**
	 * 通过地址位置信息，解析城市信息
	 * @param location 地理信息，格式 经度,纬度
	 *  114.05,22.55
	 * @return
	 */
	public static String parseLocation(String location){
		// https://lbs.amap.com/api/webservice/guide/api/georegeo 逆地址解析
		// amap_api 注册高德地图开发者，创建应用，获取apikey
		String ak = "oZ5hPUgF2NCo8AbInoq0yB0cUmdGRwPi";
		String url = "http://api.map.baidu.com/reverse_geocoding/v3/?ak="+ak+"&output=json&coordtype=wgs84ll&location="+location;
		String jsonData =  HttpUtil.httpGet(url);
		JSONObject jsonLocation = JSON.parseObject(jsonData);
		System.out.println(jsonLocation);
		String city = "";
		if("0".equals(jsonLocation.getString("status"))){
			JSONObject addressComponent =jsonLocation.getJSONObject("result").getJSONObject("addressComponent");
			Object obj = null;
			// 如果是非直辖市，city返回数据
			if((obj = addressComponent.get("city")) instanceof String){
				city=  (String)obj;
			}
//			else if ((obj = addressComponent.get("province")) instanceof String){
//				// 如果是直辖市，通过province返回数据
//				city= (String)obj;
//			}
			city = city.replace("市","");
		}
		return city;
	}
}
