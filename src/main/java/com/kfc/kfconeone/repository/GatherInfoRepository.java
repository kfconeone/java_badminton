package com.kfc.kfconeone.repository;

import com.kfc.kfconeone.models.GatherInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GatherInfoRepository extends CrudRepository<GatherInfo,Long> {
    List<GatherInfo> findByAccountId(String _accountId);
    GatherInfo findByGatherId(String _gatherId);
}
