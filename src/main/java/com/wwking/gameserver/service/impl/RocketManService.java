package com.wwking.gameserver.service.impl;

import com.wwking.gameserver.entity.RocketManGameResult;
import com.wwking.gameserver.mongodb.entity.PlayerBet;
import com.wwking.gameserver.mongodb.entity.RocketManGameRecord;
import com.wwking.gameserver.mongodb.repository.IPlayerBetDAO;
import com.wwking.gameserver.mongodb.repository.IRocketManGameRecordDAO;
import com.wwking.gameserver.protocol.rocketManGame.RocketManGameRequest;
import com.wwking.gameserver.protocol.rocketManGame.RocketManGameResponse;
import com.wwking.gameserver.service.inte.IRandomService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wwking.gameserver.service.inte.IRocketManService;

import java.util.*;

@Service
public class RocketManService implements IRocketManService {

    @Autowired
    IRocketManGameRecordDAO rocketManGameRecordDAO;
    @Autowired
    IPlayerBetDAO playerBetDAO;
    @Autowired
    IRandomService randomService;
    private final boolean isAutoGenerateGame = true; // 設定是否要自動產生遊戲

    @PostConstruct
    public void init() {

        if (isAutoGenerateGame) {
//            redissonClient.getBucket(AUTO_PUBLISH_NOTIFICATION_LOCK).set("For LOCK");

            //於服務起來時，計算排行榜結算時間倒數前 1小時 呼叫結算與刷新
            // 建立 Timer
            Timer timer = new Timer();
            // 要執行的工作
            TimerTask task = new TimerTask() {
                public void run() {
                    generateGameRecord();
                }
            };
            // 開始執行，第一次執行延遲 0 秒，每次執行間隔 1 秒(1000毫秒)
            timer.schedule(task, 0, 3 * 60 * 1000); // 每三分鐘執行一次
        } else {
            generateGameRecord();
        }

    }

    private RocketManGameRecord generateGameRecord() {
        RocketManGameRecord record = new RocketManGameRecord();
        record.setRocketManGameResult(generateGameResult());

        rocketManGameRecordDAO.save(record);

        return record;
    }

    private RocketManGameResult generateGameResult() {
        RocketManGameResult result = new RocketManGameResult();
        result.setMaxWinMultiplier(randomService.RandomInt(0,500));

        return result;
    }

    @Override
    public String sayHello() {
        RocketManGameRecord record = generateGameRecord();
        return String.valueOf(record.getRocketManGameResult().getMaxWinMultiplier());
    }

    @Override
    public RocketManGameResponse dataRequest(RocketManGameRequest request) {

        switch (request.getAction()) {
            case RocketManGameRequest.DATA_REQUEST_CURRENT_GAME_RESULT:
                return getCurrentGameResult();
        }

        return null;
    }

    @Override
    public RocketManGameResponse operationRequest(RocketManGameRequest request) {

        switch (request.getAction()) {
            case RocketManGameRequest.OP_REQUEST_PLAYER_BET:
                return playerBet(request);
            case RocketManGameRequest.OP_REQUEST_PLAYER_CASH_OUT:
                return playerCashOut(request);
        }
        return null;
    }

    private RocketManGameResponse getCurrentGameResult() {
        RocketManGameResponse response = new RocketManGameResponse();
        List<RocketManGameRecord> records = rocketManGameRecordDAO.findWithExpireTimeAfter(new Date());

        response.setGameRecord(records.stream().findFirst().orElse(null));

        return response;
    }

    private RocketManGameResponse playerBet(RocketManGameRequest request) {
        Optional<PlayerBet> playerBetRecord = playerBetDAO.findById(request.getPlayerId());

        PlayerBet playerBet = playerBetRecord.stream().findFirst().orElse(new PlayerBet());
        if (playerBet.getId() == null) playerBet.setId(request.getPlayerId());
        playerBet.setBet(request.getBet());
        playerBet.setCreateTime(request.getRequestTime());

        playerBetDAO.save(playerBet);

        RocketManGameResponse response = new RocketManGameResponse();

        return response;
    }

    private RocketManGameResponse playerCashOut(RocketManGameRequest request) {
        RocketManGameRecord gameRecord = rocketManGameRecordDAO.findWithExpireTimeAfter(request.getRequestTime()).stream().findFirst().orElse(null);
        if (gameRecord == null) {
            // no game record
            System.out.println("NO GAME RECORD");

            return new RocketManGameResponse();
        }

        PlayerBet playerBet = playerBetDAO.findById(request.getPlayerId()).stream().findFirst().orElse(null);
        if (playerBet == null || request.getRequestTime().compareTo(gameRecord.getExpireTime()) < 0) {
            // no bet record
            System.out.println("NO BET RECORD");

            return new RocketManGameResponse();
        }

        RocketManGameResponse response = new RocketManGameResponse();

        response.setPlayerWin(playerBet.getBet() * request.getGameWinMultiplier() / 100);

        return response;
    }
}
