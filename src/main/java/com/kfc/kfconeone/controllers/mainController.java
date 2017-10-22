package com.kfc.kfconeone.controllers;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kfc.kfconeone.models.GatherInfo;
import com.kfc.kfconeone.models.PlayerInfo;
import com.kfc.kfconeone.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller    // 說明這個class是Controller
public class mainController {

    @Autowired // 這標籤代表會取得自動產生的操作元件
    private PlayerInfoRepository playerInfoRepository;
    @Autowired
    private UpdateInfoRepository updateInfoRepository;
    @Autowired
    private CourtInfoRepository courtInfoRepository;
    @Autowired
    private GatherInfoRepository gatherInfoRepository;

    @RequestMapping(path="/getaccount",method = RequestMethod.POST)
    public @ResponseBody
    Map getaccount (@RequestBody Map _req) {

        Map res = new HashMap();
        try
        {
            PlayerInfo player = playerInfoRepository.findByDeviceId(_req.get("deviceId").toString());

            if(player == null)
            {
                player = new PlayerInfo();
                player.setAccountId("Acc".concat(String.format("%08d",playerInfoRepository.count())));
                player.setDeviceId(_req.get("deviceId").toString());
                player.setRegisterDate(Timestamp.from(ZonedDateTime.now(ZoneOffset.UTC).plusHours(8).toInstant()));
                playerInfoRepository.save(player);
                res.put("firstLogin",true);
            }
            else
            {
                res.put("firstLogin",false);
            }


            res.put("result","000 sucess");
            res.put("accountId",player.getAccountId());

            res.put("teamTemplate",new Gson().fromJson(player.getTeamTemplate(),Map.class));


        }
        catch (Exception ex)
        {
            res.put("result","100 failed");
            ex.printStackTrace();
        }

        return res;
    }

    @RequestMapping(path="/getcourtsinfo",method = RequestMethod.POST)
    public @ResponseBody
    Map getCourtsinfo (@RequestBody Map _req) {

        Map res = new HashMap();
        try
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            ZonedDateTime zdt =ZonedDateTime.parse(_req.get("courtsLastUpdate").toString(),formatter.withZone(ZoneId.systemDefault()));

            Timestamp playerUpdateTime = Timestamp.from(zdt.toInstant());
            Timestamp systemUpdateTime = updateInfoRepository.findAll().iterator().next().getCourtFlag();

            if(systemUpdateTime.after(playerUpdateTime))
            {
                res.put("isNeedUpdate",true);
                res.put("courtsInfo", courtInfoRepository.findAll());
            }
            else
            {
                res.put("isNeedUpdate",false);
            }



            res.put("result","000 sucess");

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            res.put("result","100 failed");
        }

        return res;
    }


    @RequestMapping(path="/setgatherinfo",method = RequestMethod.POST)
    public @ResponseBody
    Map setGatherInfo (@RequestBody String _req) {

        Map res = new HashMap();
        try
        {
            Gson gson = new Gson();
            JsonObject req = gson.fromJson(_req,JsonObject.class);
            if(req.get("isSaveTemplate").getAsBoolean())
            {
                PlayerInfo playerInfo = playerInfoRepository.findByAccountId(req.get("accountId").getAsString());
                playerInfo.setTeamTemplate(req.getAsJsonObject("template").toString());
                playerInfoRepository.save(playerInfo);
            }

            JsonObject gatherReq = req.getAsJsonObject("template").getAsJsonObject("teamName");

            GatherInfo newGatherInfo = new GatherInfo();
            newGatherInfo.setGatherId("1");
            newGatherInfo.setAccountId(req.get("accountId").getAsString());
            newGatherInfo.setCity(gatherReq.get("city").getAsString());
            newGatherInfo.setRegion(gatherReq.get("region").getAsString());
            newGatherInfo.setTeamName(gatherReq.get("teamName").getAsString());
            newGatherInfo.setTeamLeader(gatherReq.get("teamLeader").getAsString());

            newGatherInfo.setSubmitDateTime(Timestamp.from(ZonedDateTime.now(ZoneOffset.UTC).plusHours(8).toInstant()));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            ZonedDateTime zdt =ZonedDateTime.parse(gatherReq.get("playStartDateTime").getAsString(),formatter.withZone(ZoneId.systemDefault()));
            ZonedDateTime zdt2 =ZonedDateTime.parse(gatherReq.get("playStartDateTime").getAsString(),formatter.withZone(ZoneId.systemDefault()));
            Timestamp playStartDateTime = Timestamp.from(zdt.toInstant());
            Timestamp playEndDateTime = Timestamp.from(zdt2.toInstant());

            newGatherInfo.setPlayStartDateTime(playStartDateTime);
            newGatherInfo.setPlayEndDateTime(playEndDateTime);
            newGatherInfo.setPlayersCount(gatherReq.get("playersCount").getAsInt());
            newGatherInfo.setPrice(gatherReq.get("price").getAsInt());
            newGatherInfo.setGrade(gatherReq.get("grade").getAsString());
            newGatherInfo.setCourt(gatherReq.get("court").getAsString());
            newGatherInfo.setLineId(gatherReq.get("lineId").getAsString());
            newGatherInfo.setPhone(gatherReq.get("phone").getAsString());
            newGatherInfo.setFacebookUrl(gatherReq.get("facebookUrl").getAsString());
            newGatherInfo.setInformation(gatherReq.get("information").getAsString());

            gatherInfoRepository.save(newGatherInfo);

            res.put("result","000 sucess");

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            res.put("result","100 failed");
        }

        return res;
    }
}
