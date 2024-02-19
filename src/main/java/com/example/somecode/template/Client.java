package com.example.somecode.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

public class Client {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * <T> ResponseEntity<T> exchange(String url
     * 	, HttpMethod method
     * 	, @Nullable HttpEntity<?> requestEntity
     * 	, Class<T> responseType
     * 	, Object... uriVariables) throws RestClientException;
     *
     * url 调用的url地址
     * method 枚举值，HTTP方法：GET、POST、PUT、DELETE等
     * requestEntity 发起请求时携带的对象：请求头header 和/或 请求体body
     * responseType 请求响应对象的类型
     * uriVariables 就是针对url中的@PathVariable参数，可变长度参数列表
     *
     * <T> ResponseEntity<T> exchange(String url
     * 	, HttpMethod method
     * 	, @Nullable HttpEntity<?> requestEntity
     * 	, ParameterizedTypeReference<T> responseType
     * 	, Object... uriVariables) throws RestClientException;
     *
     * 与上面重载的唯一区别是responseType类型变成了ParameterizedTypeReference<T>，其它参数说明不变.
     * 设计这个类的目的：是允许传递泛型类型。用法建议是使用匿名类，如下
     * ParameterizedTypeReference<List<String>> typeRef = new ParameterizedTypeReference<List<String>>() {};
     */

    public void test() {
//        restTemplate.exchange();
    }

}
