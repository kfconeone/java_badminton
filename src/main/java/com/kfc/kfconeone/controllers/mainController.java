package com.kfc.kfconeone.controllers;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kfc.kfconeone.models.*;
import com.kfc.kfconeone.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller    // 說明這個class是Controller
public class mainController {

    @Autowired // 這標籤代表會取得自動產生的操作元件
    private PlayerInfoRepository playerInfoRepository;
    @Autowired
    private GatherInfoRepository gatherInfoRepository;
    @Autowired
    private JoinInfoRepository joinInfoRepository;

    @RequestMapping(path="/getaccount",method = RequestMethod.POST)
    public @ResponseBody
    Map getaccount (@RequestBody Map _req) {

        Map res = new HashMap();
        try
        {
            PlayerInfo player = playerInfoRepository.findByUId(_req.get("uid").toString());

            if(player == null)
            {
                player = new PlayerInfo();
                player.setAccountId("Acc".concat(String.format("%08d",playerInfoRepository.count())));
                player.setuId(_req.get("uid").toString());
                player.setRegisterDate(Timestamp.from(ZonedDateTime.now(ZoneId.of("Asia/Taipei")).toInstant()));
                player.setName(_req.get("name").toString());
                player.seteMail(_req.get("email").toString());
                player.setPhone(_req.get("phone").toString());
                playerInfoRepository.save(player);
                res.put("firstLogin",true);
            }
            else
            {
                res.put("firstLogin",false);
            }


            res.put("result","000 success");
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


    @RequestMapping(path="/setjoininfo",method = RequestMethod.POST)
    public @ResponseBody
    Map setJoinInfo (@RequestBody String _req) {

        Map res = new HashMap();
        try
        {
            Gson gson = new Gson();
            JsonObject req = gson.fromJson(_req,JsonObject.class);
            //=====檢查是否存在揪團訊息 && 是否已經參加=====
            GatherInfo gatherInfo = gatherInfoRepository.findByGatherId(req.get("gatherId").getAsString());
            if(gatherInfo == null)
            {
                res.put("result","001 不存在揪團訊息");
                return res;
            }

            if(joinInfoRepository.findByGatherIdAndAccountId(req.get("gatherId").getAsString(),req.get("accountId").getAsString()) != null)
            {
                res.put("result","002 已經參加揪團了");
                return res;
            }

            //=============================================
            JoinInfo joinInfo = new JoinInfo();
            joinInfo.setAccountId(req.get("accountId").getAsString());
            joinInfo.setName(req.get("name").getAsString());
            joinInfo.setGatherId(req.get("gatherId").getAsString());
            joinInfo.setInformation(req.get("information").getAsString());
            joinInfo.setPlayDate(gatherInfo.getPlayStartDateTime());

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

    @RequestMapping(path="/getmyjoininfo",method = RequestMethod.POST)
    public @ResponseBody
    Map getMyJoinInfo (@RequestBody String _req) {

        Map res = new HashMap();
        try
        {
            Gson gson = new Gson();
            JsonObject req = gson.fromJson(_req,JsonObject.class);
            List<JoinInfo> allJoinInfo = joinInfoRepository.findByAccountId(req.get("accountId").getAsString());
            List<JoinInfo> validJoinInfo = allJoinInfo.parallelStream()
                    .filter(tempInfo -> tempInfo.getPlayDate().after(Timestamp.from(ZonedDateTime.now(ZoneId.of("Asia/Taipei")).toInstant())))
                    .collect(Collectors.toList());


            res.put("result","000 success");
            res.put("myJoinInfo",validJoinInfo);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            res.put("result","100 failed");
        }

        return res;
    }



}
