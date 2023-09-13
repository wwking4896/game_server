package com.wwking.gameserver.mongodb.repository;

import com.wwking.gameserver.mongodb.entity.PlayerBet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface IPlayerBetDAO extends MongoRepository<PlayerBet, String> {
}
