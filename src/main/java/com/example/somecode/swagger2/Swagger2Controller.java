package com.example.somecode.swagger2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/swagger2")
//说明接口文件
@Api(value = "swagger2测试接口", tags = "swagger2测试相关的接口", description = "swagger2测试相关的接口")
public class Swagger2Controller {

    /**
     * 保存数据
     * @param param
     * @return
     */
    @PostMapping(value = "/save")
    //方法参数说明，name参数名；value参数说明，备注；dataType参数类型；required 是否必传；defaultValue 默认值
    @ApiImplicitParam(name = "user", value = "新增用户数据")
    //说明是什么方法(可以理解为方法注释)
    @ApiOperation(value = "添加用户", notes = "添加用户")
    public String saveUser(String param){
        return param;
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @GetMapping(value = "findById")
    @ApiOperation(value = "根据id获取用户信息", notes = "根据id查询用户信息")
    public Integer getUser(Integer id){
        return id;
    }

    @DeleteMapping(value = "deleteById")
    @ApiOperation(value = "根据id删除数据", notes = "删除用户")
    public Integer delete(Integer id){
        return id;
    }

}
