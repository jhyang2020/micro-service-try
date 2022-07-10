package com.fh.trymybatis.Test;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author jhYang
 * @Date 2022/7/10 0010 11:51
 * @Discription todo
 */
@Builder
@Slf4j
public class TestStream {
    public static void main(String[] args) {
        List<Student> list = Arrays.asList(
                  new Student("1", "zhangsan", 21, "北京")
                , new Student("2", "李四", 22, "北京")
                , new Student("3", "zhangsan", 23, "北京")
                , new Student("4", "李四", 24, "上海"));
        testGroupBy(list);
    }



    /**
     * 测试归并操作
     * @param list
     */
    public static void testReduce(List<Student> list) {

        // 流中包含的是User 对象，但是累加函数的参数分别是数字和user 对象，
        // 而累加器的实现是求u和，所以编译器无法推断参数 user 的类型。
        // 可以把代码使用组合器。参数为Integer::sum
        Integer reduce = list.stream().reduce(0
                , (partialAgeResult, user) -> {
                       System.out.println(partialAgeResult +"-----" +user.getAge());
                       return partialAgeResult + user.getAge();
                }, Integer::sum);
        log.info("【reduce:{}】",reduce);
    }


    public static void testCollectingAndThen(List<Student> list) {

        Double collect = list.stream()
                .collect(Collectors.collectingAndThen(Collectors.averagingInt(
                        value -> {
                            System.out.println("v--" + value.getAge() + "--> " + value.getAge() * 2);
                            return value.getAge() * 2;
                        }),
                        s -> {
                            System.out.println("s--" + s + "--> " + (s - 1));
                            return s - 1;
                        })
                );
        log.info("【collectingAndThen {}】", collect);

    }

    /**
     * 排序
     * @param list
     */
    public static void testGroupBy(List<Student> list) {
        Map<String, List<Student>> collect = list.stream()
                .collect(Collectors.groupingBy(Student::getName));
        log.info("【按照姓名分组】{}",collect);

        //按照地址分组后，进行每组count计数
        Map<String, Long> collect1 = list.stream()
                .collect(Collectors.groupingBy(Student::getAddr, Collectors.counting()));
        log.info("【按照地址分组】{}",collect1);

        //根据不同条件分组
        Map<String, List<Student>> prodMap= list.stream().collect(Collectors.groupingBy(item -> {
            if(item.getAge() < 24) {
                return "3";
            }else {
                return "other";
            }
        }));
        log.info("【根据不同条件分组】{}",prodMap);

        // 多级分组
        Map<String, Map<String, List<Student>>> collect2 = list.stream()
                .collect(Collectors
                        .groupingBy(Student::getName, Collectors.groupingBy(Student::getAddr)));
        log.info("【先按照名称后按照年龄分组】{}",collect2);

        List<Map<String, List<Student>>> collect3 = list.stream()
                .collect(Collectors
                        .groupingBy(Student::getName, Collectors.groupingBy(Student::getAddr)))
                .values().stream().collect(Collectors.toList());
        log.info("【先按照名称后按照年龄分组】{}",collect3);

        // 求和
        Map<String, Integer> prodMap3 = list.stream()
                .collect(Collectors.groupingBy(Student::getAddr, Collectors.summingInt(Student::getAge)));
        log.info("【分组求和】{}",prodMap3);

       // 把收集器的结果转换为另一种类型
        Map<String, Student> stringStudentMap = list
                .stream()
                .collect(Collectors.groupingBy(Student::getAddr
                        , Collectors.collectingAndThen(
                                // 取每组最大的年龄的
                                Collectors.maxBy(Comparator.comparingInt(Student::getAge))
                                // 结果转化类型
                                , Optional::get)));

        log.info("【把收集器的结果转换为另一种类型】{}",stringStudentMap);

        List<Student> collect4 = list
                .stream()
                .collect(Collectors.groupingBy(Student::getAddr
                        , Collectors.collectingAndThen(
                                // 取每组最大的年龄的
                                Collectors.maxBy(Comparator.comparingInt(Student::getAge))
                                // 结果转化类型
                                , Optional::get)))
                .values().stream().collect(Collectors.toList());
        log.info("【把收集器的结果转换为另一种类型】{}",collect4);


    }

    /**
     * 排序
     * @param list
     */
    public static void testSorted(List<Student> list) {
        List<Student> collect = list.stream()
                //默认升序
                .sorted(Comparator.comparing(Student::getAge).reversed())
                .collect(Collectors.toList());
        log.info("【按照年龄递增排序：{}】",collect);
    }

        /**
         * 过滤方法使用
         * @param list
         */
    public static void testFilter(List<Student> list){
        List<Student> collect = list.stream()
                .filter(student -> student.getAge() == 23)
                .collect(Collectors.toList());
        log.info("【输出过滤出年龄为23的：{}】",collect);
        List<Student> collect1 = list.stream()
                .filter(student -> {
                    if(student.getAge() < 22){
                        return true;
                    } else {
                        //注意此处 if 的全判断都要有
                        return false;
                    }
                }).collect(Collectors.toList());
        log.info("【输出过滤出年龄为小于22的：{}】",collect1);

    }
}
