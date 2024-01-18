package com.example.somecode.swagger2;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys/user")
@Api(value = "SysUserController", tags = {"用户管理相关操作接口"})
public class SysUserController {


    @PostMapping("/pageQuery")
    @ApiOperation(value = "用户分页查询接口", notes = "notes用户分页查询接口")
    public String pageQuery(@RequestBody SysUserPageQueryRequest pageQueryRequest) {
        return "ok";
    }


    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除用户的接口")
    @ApiImplicitParam(name = "id", value = "用户id", dataType = "Integer", defaultValue = "10", required = true)
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功"),
            @ApiResponse(code = 500, message = "删除失败")
    })
    public void delete(@PathVariable Integer id) {
        System.out.println("删除成功:" + id);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "更新用户", notes = "根据用户id更新用户名接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", dataType = "Integer", defaultValue = "10", required = true),
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "String", defaultValue = "lisi", required = true)
    })
    public String update(@PathVariable Integer id, String username) {
        return id + "--" + username;
    }

}
