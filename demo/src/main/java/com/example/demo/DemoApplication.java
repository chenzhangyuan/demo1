package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;



@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    /**
     * Hello，World
     *
     * @param who 参数，非必须
     * @return Hello, ${who}
     */
    @GetMapping("/hello")
    public String sayHello(@RequestParam(required = false, name = "who") String who) {
     String msg="欢迎使用springBoot" ;
        return msg;
    }

    @ResponseBody
    @RequestMapping(value = "/hello1", method = RequestMethod.POST)
    public String hello1(HttpServletRequest request){
        JSONObject resultJson = new JSONObject();
        try {
            String params = getContentFromRequestInputStream(request);
            resultJson = JSONObject.parseObject(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String responseJson = resultJson.toJSONString();
        return responseJson;
    }


    protected String getContentFromRequestInputStream(HttpServletRequest request) throws Exception {
        try {
            return IOUtils.toString(request.getInputStream(), "UTF-8");
        } catch (IOException e) {

            throw new Exception("");
        }
    }
}
