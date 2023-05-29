package com.sky.controller.user;

import com.sky.constant.ShopStatusConstant;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/shop")
@Api(tags = "店铺操作接口")
public class UserShopController {
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/status")
    @ApiOperation("获取店铺营业状态")
    public Result getStatus() {
        String status = (String) redisTemplate.opsForValue().get(ShopStatusConstant.SHOP_STATUS_KEY);
        Integer sta = Integer.valueOf(status);
        return Result.success(sta);
    }
}
