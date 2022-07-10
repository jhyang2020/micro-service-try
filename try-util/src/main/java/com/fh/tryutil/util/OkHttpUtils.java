package com.fh.tryutil.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author jhYang
 * @Date 2022/7/10 0010 16:18
 * @Discription todo
 */
@Slf4j
public class OkHttpUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(OkHttpUtils.class);

    private OkHttpClient okHttpClientPool;

    private static final int MAX_IDLE_CONNECTIONS = 15;
    private static final int KEEP_ALIVE_DURATION = 15;
    private static final int READ_TIMEOUT = 15;
    private static final int CONNECT_TIMEOUT = 15;
    private static final int WRITE_TIMEOUT = 15;

    public OkHttpUtils() {
        try {
            //OKHttp连接池
            ConnectionPool connectionPool = new ConnectionPool(MAX_IDLE_CONNECTIONS, KEEP_ALIVE_DURATION, TimeUnit.SECONDS);
            okHttpClientPool = new OkHttpClient.Builder()
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)

                    //添加 过滤器
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            return null;
                        }
                    })
                    .connectionPool(connectionPool)
                    .retryOnConnectionFailure(true).build();
        } catch (Exception e) {
            LOGGER.error("【下载出错...】", e);
        }
    }

    /**
     * 下载某文件
     *
     * @param url      要下载的链接
     * @param fileName 下载后文件名
     */
    public void downFileFromUrl(String url, String fileName) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        //构建FormBody,传入参数
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Authorization", "XXX")
                .build();

        try {
            Response response = client.newCall(request).execute();
            byte[] resp = Objects.requireNonNull(response.body()).bytes();
            FileHandleUtils.getFileByBytes(resp, fileName );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
