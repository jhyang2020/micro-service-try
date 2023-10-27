package com.fh.tryutil.thread;

import java.util.concurrent.*;

/**
 * @Author jhYang
 * @Date 2022/9/17 0017 13:49
 * @Discription todo
 */
public class TestTreadMain {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5
        , 10, 2, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        CompletionService<String> completionService = new ExecutorCompletionService(threadPoolExecutor);
        for (int i = 0; i < 10; i++) {
            completionService.submit(new TestTread(i+""));
        }

        for (int i = 0; i < 10; i++) {
            Future<String> take = null;
            try {
                 take = completionService.take();
                try {
                    String s = take.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
