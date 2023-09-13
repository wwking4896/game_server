package com.wwking.gameserver.service.inte;

import com.wwking.gameserver.protocol.rocketManGame.RocketManGameRequest;
import com.wwking.gameserver.protocol.rocketManGame.RocketManGameResponse;

public interface IRocketManService {
    String sayHello();

    RocketManGameResponse dataRequest(RocketManGameRequest request);
    RocketManGameResponse operationRequest(RocketManGameRequest request);
}
