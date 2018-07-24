package com.sun.fighter.base;

import com.alibaba.fastjson.JSONObject;
import com.jayway.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

import static com.jayway.restassured.RestAssured.given;

/**
 * @创建人 chengyin
 * @创建时间 2018/6/19
 * @描述
 */
public abstract  class TestBase {
    @Before
    public void doBefore() {
        RestAssured.port = port;//4: 告诉restAssured使用哪个端口来访问
    }

//    protected static final Matcher<Object> SUCCESS = new Equals(200);
//    protected static final Matcher<Object> BAD_REQUEST = new Equals(400);
//    protected static final Matcher<Object> DUPLICATE = new Equals(302);
//    protected static final Matcher<Object> MODIFIED = new Equals(409);

    protected String generateUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Value("${local.server.port}")
    int port;


    public static JSONObject httpGet(String httpUrl) {
        return
                JSONObject.parseObject(
                        given().request()
                                .when().get(httpUrl)
                                .then()
                                .extract().asString()
                );
    }

    public static JSONObject httpGet(String httpUrl, Matcher matcher) {
        return
                JSONObject.parseObject(
                        given().request()
                                .when().get(httpUrl)
                                .then()
                                .statusCode(matcher)
                                .extract().asString()
                );
    }

    public static JSONObject httpPost(String httpUrl, JSONObject parm) {
        return
                JSONObject.parseObject(
                        given().contentType("application/json")
                                .request().body(parm == null ? null : parm.toJSONString())
                                .when().post(httpUrl)
                                .then()
                                .extract().asString()
                );
    }

    public static JSONObject httpPost(String httpUrl, JSONObject parm, Matcher matcher) {
        return
                JSONObject.parseObject(
                        given().contentType("application/json")
                                .request().body(parm == null ? null : parm.toJSONString())
                                .when().post(httpUrl)
                                .then()
                                .statusCode(matcher)
                                .extract().asString()
                );
    }

    public static JSONObject httpPut(String httpUrl, JSONObject parm) {
        return
                JSONObject.parseObject(
                        given().contentType("application/json")
                                .request().body(parm == null ? null : parm.toJSONString())
                                .when().put(httpUrl)
                                .then()
                                .extract().asString()
                );
    }

    public static JSONObject httpDelete(String httpUrl, JSONObject parm) {
        return
                JSONObject.parseObject(
                        given().contentType("application/json")
                                .request().body(parm == null ? null : parm.toJSONString())
                                .when().delete(httpUrl)
                                .then()
                                .extract().asString()
                );
    }
}
