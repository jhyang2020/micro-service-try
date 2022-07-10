package com.fh.tryutil.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author jhYang
 * @Date 2022/7/10 0010 16:14
 * @Discription todo
 */
public class OkhttpTest {
    @Test
    public void addTest(){
        String url = "https://gd3.alicdn.com/imgextra/i2/2583364731/O1CN01oxbeg11koqL72S4Dz_!!2583364731.jpg";
        OkHttpUtils okHttpUtils = new OkHttpUtils();
        okHttpUtils.downFileFromUrl(url, "测试"+".jpg");
    }
}
