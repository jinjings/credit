package com.pay.card.bootLoader;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.pay.card.Constants;
import com.pay.card.enums.ChannelEnum;
import com.pay.card.model.CreditUserInfo;
import com.pay.card.service.impl.CreditUserInfoServiceImpl;
import com.pay.card.utils.JedisUtil;

/**
 * @Description: 初始化userid至本地缓存
 * @see: UserInfoRunner 此处填写需要参考的类
 * @version 2017年11月30日 下午2:25:52
 * @author zhibin.cui
 */
@Component
public class UserInfoRunner implements ApplicationRunner {

    @Autowired
    private CreditUserInfoServiceImpl creditUserInfoService;

    @Override
    public void run(ApplicationArguments arg0) throws Exception {
        String key = "";
        List<CreditUserInfo> creditUser = creditUserInfoService.findCreditUserInfoAll();
        for (CreditUserInfo userInfo : creditUser) {
            if (ChannelEnum.MPOS.getCode().equals(userInfo.getChannel())) {
                // 手刷
                key = userInfo.getPhoneNo() + userInfo.getChannel();
            } else if (ChannelEnum.POS.getCode().equals(userInfo.getChannel())) {
                // 大pos
                key = userInfo.getCustomerNo() + userInfo.getChannel();
            }

            // Set<String> keys = JedisUtil.getKeys("credit_redis_key_" + "*");
            // JedisUtil.delete(keys);

            JedisUtil.saveString(Constants.REDIS_USERID_KEY + key, userInfo.getId() + "");
            // LocalCacheUtil.put(key, userInfo.getId());
        }
    }

}