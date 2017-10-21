package com.kfc.kfconeone.controllers;


import com.kfc.kfconeone.models.PlayerInfo;
import com.kfc.kfconeone.repository.PlayerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller    // 說明這個class是Controller
public class mainController {

    @Autowired // 這標籤代表會取得自動產生的操作元件UserRepository
    private PlayerInfoRepository playerInfoRepository;

    @RequestMapping(path="/getaccount",method = RequestMethod.POST)
    public @ResponseBody
    Map addUser (@RequestBody Map _req) {

        Map res = new HashMap();
        try
        {
            PlayerInfo player = playerInfoRepository.findByDeviceId(_req.get("deviceId").toString());

            if(player == null)
            {
                player = new PlayerInfo();
                player.setAccountId("Acc".concat(String.format("%08d",playerInfoRepository.count())));
                player.setDeviceId(_req.get("deviceId").toString());
                player.setRegisterDate(Timestamp.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant()));
                playerInfoRepository.save(player);
                res.put("'firstLogin'",true);
            }
            else
            {
                res.put("'firstLogin'",false);
            }


            res.put("result","000 sucess");
            res.put("'accountId'",player.getAccountId());
            res.put("'teamTemplate'",player.getTeamTemplate());

        }
        catch (Exception ex)
        {
            res.put("result","100 failed");
        }

        return res;
    }
}
