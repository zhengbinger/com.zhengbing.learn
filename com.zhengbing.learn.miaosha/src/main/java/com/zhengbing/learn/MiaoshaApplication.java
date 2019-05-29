package com.zhengbing.learn;

import com.zhengbing.learn.miaosha.common.CodeMsg;
import com.zhengbing.learn.miaosha.common.Result;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@SpringBootApplication
@Controller
public class MiaoshaApplication {

	@RequestMapping("/hello")
	@ResponseBody
	public Result<String> hello(){
		return Result.success( "china is good" );
	}

	@RequestMapping("/helloerror")
	@ResponseBody
	public Result<String> helloerror(){
		return Result.error( CodeMsg.SERVER_ERROR );
	}

	@RequestMapping("thymeleaf")
	public String thymeleaf( Model model){
		model.addAttribute( "name","china is good" );
		return "hello";
	}


	public static void main(String[] args) {
		SpringApplication.run(MiaoshaApplication.class, args);
	}

}
