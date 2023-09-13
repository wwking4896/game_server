package com.wwking.gameserver.controller;

import com.wwking.gameserver.protocol.rocketManGame.RocketManGameRequest;
import com.wwking.gameserver.protocol.rocketManGame.RocketManGameResponse;
import com.wwking.gameserver.service.inte.IRocketManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("RocketManGameService")
public class RocketManController {

    @Autowired
    IRocketManService rocketManService;

    @GetMapping(value = "/")
    public String sayHello() {
        return rocketManService.sayHello();
    }

    @PostMapping(value = "DataRequest")
    public @ResponseBody
    RocketManGameResponse dataRequest(@RequestBody RocketManGameRequest request) {
        return rocketManService.dataRequest(request);
    }

    @PostMapping(value = "OperationRequest")
    public @ResponseBody
    RocketManGameResponse operationRequest(@RequestBody RocketManGameRequest request) {
        return rocketManService.operationRequest(request);
    }
}
