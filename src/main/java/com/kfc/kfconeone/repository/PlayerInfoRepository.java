package com.kfc.kfconeone.repository;

import com.kfc.kfconeone.models.PlayerInfo;
import org.springframework.data.repository.CrudRepository;

public interface PlayerInfoRepository extends CrudRepository<PlayerInfo,Long> {
    PlayerInfo findByDeviceId(String _deviceId);
}
