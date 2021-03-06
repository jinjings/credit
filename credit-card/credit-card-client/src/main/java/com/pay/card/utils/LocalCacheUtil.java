package com.pay.card.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.pay.card.enums.ChannelEnum;

/**
 * @Description: 本地缓存工具类
 * @see: LocalCache 此处填写需要参考的类
 * @version 2017年11月29日 上午10:22:42
 * @author zhibin.cui
 */
public class LocalCacheUtil {

    /**
     * 清除缓存任务类
     */
    static class CleanWorkerTask extends TimerTask {

        private String key;

        public CleanWorkerTask(String key) {
            this.key = key;
        }

        @Override
        public void run() {
            LocalCacheUtil.remove(key);
        }
    }

    /**
     * 默认有效时长,单位:秒
     */
    private static final long SECOND_TIME = 1000;

    private static final Map<String, Object> map;

    // private static final Timer timer;

    /**
     * 初始化
     */
    static {
        // timer = new Timer();
        map = new ConcurrentHashMap<>();
    }

    /**
     * 清除所有缓存
     * @return
     */
    public static void clear() {
        if (size() > 0) {
            map.clear();
        }
        // timer.cancel();
    }

    /**
     * 查询缓存是否包含key
     * @param key
     * @return
     */
    public static boolean containsKey(String key) {
        return map.containsKey(key);
    }

    /**
     * 获取缓存
     * @param key
     * @return
     */
    public static Object get(String key) {
        return map.get(key);
    }

    /**
     * @Title:
     * @Description: 获取缓存
     * @param phoneNo
     * @param customerNo
     * @param interfaceCode
     * @return
     */
    public static Object get(String phoneNo, String customerNo, String interfaceCode) {
        String key = "";
        if (ChannelEnum.MPOS.getCode().equals(interfaceCode)) {
            // 手刷
            key = phoneNo + interfaceCode;
        } else if (ChannelEnum.POS.getCode().equals(interfaceCode)) {
            // 大pos
            key = customerNo + interfaceCode;
        }
        return map.get(key);
    }

    private static Long getTimeOut() {
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now();
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        LocalDateTime tomorrowMidnight = todayMidnight.plusDays(1);

        LocalDateTime nowTime = LocalDateTime.now();

        System.out.println(TimeUnit.NANOSECONDS.toSeconds(Duration.between(nowTime, tomorrowMidnight).toNanos()));
        return TimeUnit.NANOSECONDS.toSeconds(Duration.between(nowTime, tomorrowMidnight).toNanos());
    }

    /**
     * 增加缓存
     * @param key
     * @param value
     */
    public static void put(String key, Long value) {
        map.put(key, value);
        // timer.schedule(new CleanWorkerTask(key), getTimeOut());
    }

    /**
     * 增加缓存
     * @param key
     * @param value
     * @param expireTime
     *            过期时间
     */
    public static void put(String key, Long value, Date expireTime) {
        map.put(key, value);
        // timer.schedule(new CleanWorkerTask(key), expireTime);
    }

    /**
     * 增加缓存
     * @param key
     * @param value
     * @param timeout
     *            有效时长
     */
    public static void put(String key, Long value, int timeout) {
        map.put(key, value);
        // timer.schedule(new CleanWorkerTask(key), timeout);
    }

    /**
     * @Title: put
     * @Description: 增加缓存
     * @param
     * @return
     */
    public static void put(String key, String value) {
        map.put(key, value);
    }

    /**
     * 增加缓存
     * @param key
     * @param value
     * @param timeout
     *            有效时长
     */
    public static void put(String key, String value, int timeout) {
        map.put(key, value);
        // timer.schedule(new CleanWorkerTask(key), timeout);
    }

    /**
     * 批量增加缓存
     * @param m
     */
    public static void putAll(Map<String, Long> m) {
        map.putAll(m);

        // for (String key : m.keySet()) {
        // timer.schedule(new CleanWorkerTask(key), getTimeOut());
        // }
    }

    /**
     * 批量增加缓存
     * @param m
     */
    public static void putAll(Map<String, Long> m, Date expireTime) {
        map.putAll(m);

        // for (String key : m.keySet()) {
        // timer.schedule(new CleanWorkerTask(key), expireTime);
        // }
    }

    /**
     * 批量增加缓存
     * @param m
     */
    public static void putAll(Map<String, Long> m, int timeout) {
        map.putAll(m);

        // for (String key : m.keySet()) {
        // timer.schedule(new CleanWorkerTask(key), timeout * SECOND_TIME);
        // }
    }

    /**
     * 删除缓存
     * @param key
     */
    public static void remove(String key) {
        map.remove(key);
    }

    /**
     * 返回缓存大小
     * @return
     */
    public static int size() {
        return map.size();
    }

    /**
     * 私有构造函数,工具类不允许实例化
     */
    private LocalCacheUtil() {

    }

}
