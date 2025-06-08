package com.back.domain.global;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    String cmd;

    Map<String, Integer> idMap = new HashMap<>();
    Map<String, String> kewordMap = new HashMap<>();
    String[] splitCmdBits=null;
    public Rq(String keyWord) { // 삭제?id=1
        cmd = keyWord;

        if(cmd.contains("?")) {
            String[] splitCmd = cmd.split("\\?");

            String splitCmdBit = splitCmd[1];
            splitCmdBits = splitCmdBit.split("=");


        }
    }

    // 유연한 사고 안되면 메소드 만들어서 억지로 하라고
    private boolean isNumeric(String splitCmdBit) {
        try  {
            Integer.parseInt(splitCmdBit);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int getParamAsInt(String keyWord, int defaultReturn) {
        int key = -1;

        if(splitCmdBits!=null &&splitCmdBits.length >= 2 && isNumeric(splitCmdBits[1])) {
            idMap.put(splitCmdBits[0],Integer.parseInt(splitCmdBits[1]));
        }
        key = idMap.getOrDefault(keyWord,defaultReturn);

        return key;
    }


    public String getParam(String searchKeyword, String defaultReturn) {



        if(splitCmdBits!=null &&splitCmdBits.length >= 2) {
            kewordMap.put(splitCmdBits[0],splitCmdBits[1]);
        }


        return kewordMap.getOrDefault(searchKeyword,defaultReturn);
    }
}
