package com.fh.trymybatis.Test;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import javax.swing.text.Style;
import java.nio.file.LinkOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author jhYang
 * @Date 2022/7/10 0010 11:51
 * @Discription todo
 */
@Slf4j
public class TestStream {
    public static void main(String[] args) {
        List<Student> list = Arrays.asList(
                  new Student("1", "zhangsan", 21, "北京")
                , new Student("1", "zhangsan", 21, "北京")
                , new Student("2", "李四", 22, "北京")
                , new Student("3", "zhangsan", 23, "北京")
                , new Student("4", "李四", 24, "上海"));

        testFlatMap(list);
    }

    /**
        * 功能描述: 扁平映射  flatMap  接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
        * 如果函数返回的是一个流，使用flatMap会使得函数返回的流中的元素放到一个流中
        * @author jhyang
        * @date 2022/7/11
        * @param list the parameter of the function
        * @return void the parameter of the function
    */
    public static void testFlatMap(List<Student> list) {

        list.stream().toArray();
    }

        /**
            * 功能描述:  去重h后跳过第一个返回两条 按idno降序排序 输出
            * @author jhyang
            * @date 2022/7/11
            * @param list the parameter of the function
            * @return void the parameter of the function
        */
    public static void testDistinct(List<Student> list) {
       list.stream()
               .distinct()
               .skip(1)
               .limit(2)
               .sorted(Comparator.comparing(Student::getIdNo).reversed())
               .forEach(System.out::println);
    }

        /**
            * 功能描述: Stream测试
            * @author jhyang
            * @date 2022/7/11
            * @param
            * @return void the parameter of the function
        */
    public static void testStream() {
        Stream<String> generate = Stream.generate(() -> "测试");
        List<String> collects = Stream.of("测试1","测试2").collect(Collectors.toList());
        System.out.println(collects);

        log.info("【generate输出：{}】",generate);
        log.info("【generate to list 输出：{}】",collects);
    }

    /**
        * 功能描述: map测试  map操作会改变初始值
        * @author jhyang
        * @date 2022/7/11
        * @param list the parameter of the function
        * @return void the parameter of the function
    */
    public static void testMap(List<Student> list) {
        List<Student> collect = list.stream().map(student -> {
            student.setAddr("中国 " + student.getAddr());
            //此处可以自定义返回类型
            return student;
        }).collect(Collectors.toList());
        log.info("【map操作的地址全称为：{}】"+collect);

        List<String> collect1 = list.stream().map(student -> {
            //此处可以自定义返回类型
            return student.getAddr();
        }).collect(Collectors.toList());
        log.info("【map操作仅仅返回操作后的地址：{}】"+collect1);

        list.stream().map(Student::getAge).forEach(System.out::println);


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
