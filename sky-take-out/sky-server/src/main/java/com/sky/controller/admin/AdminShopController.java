package com.sky.controller.admin;

import com.sky.constant.ShopStatusConstant;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/shop")
@Api(tags = "店铺操作接口")
public class AdminShopController {
    @Autowired
    private RedisTemplate redisTemplate;

    @PutMapping("/{status}")
    @ApiOperation("店铺营业状态")
    public Result shopStatus(@PathVariable Integer status) {
        redisTemplate.opsForValue().set(ShopStatusConstant.SHOP_STATUS_KEY, String.valueOf(status));
        return Result.success();
    }

    @GetMapping("/status")
    @ApiOperation("获取店铺营业状态")
    public Result<Integer> getStatus() {
        String status = (String) redisTemplate.opsForValue().get(ShopStatusConstant.SHOP_STATUS_KEY);
        Integer sta = Integer.valueOf(status);
        return Result.success(sta);
    }
}
