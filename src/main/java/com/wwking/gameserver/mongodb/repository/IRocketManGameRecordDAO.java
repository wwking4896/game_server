package com.wwking.gameserver.mongodb.repository;

import com.wwking.gameserver.mongodb.entity.RocketManGameRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface IRocketManGameRecordDAO extends MongoRepository<RocketManGameRecord, UUID> {
    @Query(value = "{ 'expireTime' : { $gte : ?0 } }", sort = "{ 'expireTime' : -1 }")
    List<RocketManGameRecord> findWithExpireTimeAfter(Date date);
}
