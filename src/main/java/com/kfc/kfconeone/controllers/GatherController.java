package com.kfc.kfconeone.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kfc.kfconeone.models.GatherInfo;
import com.kfc.kfconeone.models.JoinInfo;
import com.kfc.kfconeone.models.PlayerInfo;
import com.kfc.kfconeone.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller    // 說明這個class是Controller
public class GatherController {
    //@Autowired // 這標籤代表會取得自動產生的操作元件，但不推薦，建議在Constructor注入
    private PlayerInfoRepository playerInfoRepository;
    private GatherInfoRepository gatherInfoRepository;
    private JoinInfoRepository joinInfoRepository;


    @Autowired  //Spring推薦用Constructor注入
    public GatherController(
            PlayerInfoRepository _playerInfoRepository,
            GatherInfoRepository _gatherInfoRepository,
            JoinInfoRepository _joinInfoRepository
            )
    {
        playerInfoRepository = _playerInfoRepository;
        gatherInfoRepository = _gatherInfoRepository;
        joinInfoRepository = _joinInfoRepository;
    }


    @RequestMapping(path="/setgatherinfo",method = RequestMethod.POST)
    public @ResponseBody
    Map setGatherInfo (@RequestBody String _req) {

        Map<String,Object> res = new HashMap<>();
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

            JsonObject gatherReq = req.getAsJsonObject("gatherInfo");
//            =====設定id=====
            String timeStamp = DateTimeFormatter.ofPattern("-yyyyMMddhhmmss-").format(ZonedDateTime.now(ZoneId.of("Asia/Taipei")));
            String gatherId = req.get("accountId").getAsString() + timeStamp +  String.format("%08d",gatherInfoRepository.count());

            GatherInfo newGatherInfo = new GatherInfo();
            newGatherInfo.setGatherId(gatherId);
            newGatherInfo.setAccountId(req.get("accountId").getAsString());
            newGatherInfo.setCity(gatherReq.get("city").getAsString());
            newGatherInfo.setRegion(gatherReq.get("region").getAsString());
            newGatherInfo.setTeamName(gatherReq.get("teamName").getAsString());
            newGatherInfo.setTeamLeader(gatherReq.get("teamLeader").getAsString());

            newGatherInfo.setSubmitDateTime(Timestamp.from(ZonedDateTime.now(ZoneOffset.UTC).plusHours(8).toInstant()));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            ZonedDateTime zdt =ZonedDateTime.parse(gatherReq.get("playStartDateTime").getAsString(),formatter.withZone(ZoneId.of("Asia/Taipei")));
            ZonedDateTime zdt2 =ZonedDateTime.parse(gatherReq.get("playStartDateTime").getAsString(),formatter.withZone(ZoneId.of("Asia/Taipei")));
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

            res.put("result","000 success");

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            res.put("result","100 failed");
        }

        return res;
    }

    @RequestMapping(path="/getmygatherinfo",method = RequestMethod.POST)
    public @ResponseBody
    Map getMyGatherInfo (@RequestBody String _req) {

        Map<String,Object> res = new HashMap<>();
        try
        {
            Gson gson = new Gson();
            JsonObject req = gson.fromJson(_req,JsonObject.class);
            List<GatherInfo> allGatherInfo = gatherInfoRepository.findByAccountId(req.get("accountId").getAsString());
            List<GatherInfo> validGatherInfo = allGatherInfo.parallelStream()
                    .filter(tempInfo -> tempInfo.getPlayStartDateTime().after(Timestamp.from(ZonedDateTime.now(ZoneId.of("Asia/Taipei")).toInstant())))
                    .collect(Collectors.toList());


            res.put("result","000 success");
            res.put("myGatherInfo",validGatherInfo);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            res.put("result","100 failed");
        }

        return res;
    }


    @RequestMapping(path="/getcitygatherinfo",method = RequestMethod.POST)
    public @ResponseBody
    Map getCityGatherInfo (@RequestBody String _req) {

        Map<String,Object> res = new HashMap<>();
        try
        {
            Gson gson = new Gson();
            JsonObject req = gson.fromJson(_req,JsonObject.class);
            List<GatherInfo> allCityGatherInfo = gatherInfoRepository.findByCity(req.get("city").getAsString());
            List<GatherInfo> valiCitydGatherInfo = allCityGatherInfo.parallelStream()
                    .filter(tempInfo -> tempInfo.getPlayStartDateTime().after(Timestamp.from(ZonedDateTime.now(ZoneId.of("Asia/Taipei")).toInstant())))
                    .collect(Collectors.toList());


            res.put("result","000 success");
            res.put("myGatherInfo",valiCitydGatherInfo);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            res.put("result","100 failed");
        }

        return res;
    }


    @RequestMapping(path="/getgatherinfobygatherid",method = RequestMethod.POST)
    public @ResponseBody
    Map getGatherInfoByGatherId (@RequestBody String _req) {

        Map<String,Object> res = new HashMap<>();
        try
        {
            Gson gson = new Gson();
            JsonObject req = gson.fromJson(_req,JsonObject.class);
            GatherInfo gatherInfo = gatherInfoRepository.findByGatherId(req.get("gatherId").getAsString());


            res.put("result","000 success");
            res.put("gatherInfo",gatherInfo);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            res.put("result","100 failed");
        }

        return res;
    }


    @RequestMapping(path="/getmygatherrequests",method = RequestMethod.POST)
    public @ResponseBody
    Map getMyGatherRequests (@RequestBody String _req) {

        Map<String,Object> res = new HashMap<>();
        try
        {
            Gson gson = new Gson();
            JsonObject req = gson.fromJson(_req,JsonObject.class);
            List<String> myGatherIdList = gatherInfoRepository
                    .findByAccountId(req.get("accountId").getAsString())
                    .parallelStream()
                    .map(GatherInfo::getGatherId)
                    .collect(Collectors.toList());


            List<JoinInfo> joinInfos = new ArrayList<>();
            myGatherIdList.forEach(tempId ->
                    {
                        //joinInfoRepository.findByGatherId(tempId).forEach(joinInfo -> joinInfos.add(joinInfo));
                        joinInfos.addAll(joinInfoRepository.findByGatherId(tempId));
                    });



            res.put("result","000 success");
            res.put("gatherInfo",joinInfos);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            res.put("result","100 failed");
        }

        return res;
    }


    @RequestMapping(path="/confirmgatherrequests",method = RequestMethod.POST)
    public @ResponseBody
    Map confirmGatherRequests (@RequestBody String _req) {

        Map<String,Object> res = new HashMap<>();
        try
        {
            Gson gson = new Gson();
            JsonObject req = gson.fromJson(_req,JsonObject.class);
            JoinInfo joinInfo = joinInfoRepository.findByGatherIdAndAccountId(req.get("gatherId").getAsString(),req.get("accountId").getAsString());
            joinInfo.setConfirm(req.get("confirm").getAsInt());
            joinInfoRepository.save(joinInfo);

            res.put("result","000 success");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            res.put("result","100 failed");
        }

        return res;
    }

    @RequestMapping(path="/cancelgatherinfo",method = RequestMethod.POST)
    public @ResponseBody
    Map cancelGatherInfo (@RequestBody String _req) {

        Map<String,Object> res = new HashMap<>();
        try
        {
            Gson gson = new Gson();
            JsonObject req = gson.fromJson(_req,JsonObject.class);
            GatherInfo gatherInfo = gatherInfoRepository.findByGatherId(req.get("gatherId").getAsString());
            gatherInfo.setCancel(true);
            gatherInfoRepository.save(gatherInfo);

            res.put("result","000 success");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            res.put("result","100 failed");
        }

        return res;
    }

}
