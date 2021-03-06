package com.coolweather.android.util;

import android.text.TextUtils;


import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    //解析和处理服务器返回的省级数据
    public static boolean handleProvinceResponse(String response) {
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray arrProvinces = new JSONArray(response);
                for (int i = 0; i< arrProvinces.length(); i++){
                    JSONObject provincesObject = arrProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provincesObject.getString("name"));
                    province.setProvinceCode(provincesObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    //解析和处理服务器返回的市级数据
    public static boolean handleCityResponse(String response, int provinceId){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray arrCities = new JSONArray(response);
                for (int i = 0; i < arrCities.length(); i++){
                    JSONObject countyObject = arrCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(countyObject.getString("name"));
                    city.setCityCode(countyObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    //解析和处理服务器返回县级数据
    public static boolean handleCountyResponse(String response , int cityId){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray arrCounties = new JSONArray(response);
                for (int i = 0; i < arrCounties.length(); i++){
                    JSONObject countyObject = arrCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
