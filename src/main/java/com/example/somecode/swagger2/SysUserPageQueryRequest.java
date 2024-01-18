package com.example.somecode.swagger2;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("系统用户分页查询请求体")
public class SysUserPageQueryRequest extends BasePageRequest {
    //@Serial
    private static final long serialVersionUID = -7586253215056854970L;

    @ApiModelProperty("创建时间开始")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeStart;

    @ApiModelProperty("创建时间结束")
    private Date createTimeEnd;

}

