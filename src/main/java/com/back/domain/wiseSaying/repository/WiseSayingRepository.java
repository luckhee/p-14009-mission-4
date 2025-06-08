package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    private int lastid =0;
    private List<WiseSaying> wiseSayings = new ArrayList<>();


    public WiseSaying save(String content, String author) {

        WiseSaying wiseSaying = new WiseSaying(++lastid, author,content);
        wiseSayings.add(wiseSaying);
        return wiseSaying;
    }

    public List<WiseSaying> findList() {
        return wiseSayings.reversed();
    }

    public String delete(int id) {
        boolean exists = wiseSayings.stream()
                .anyMatch(e -> e.getId() == id);

        if (!exists) {
            return id + "번 명언은 존재하지 않습니다.";
        } else {
            wiseSayings = wiseSayings.stream()
                    .filter(e-> e.getId() != id)
                    .toList();
        }



        return id + "번 명언이 삭제되었습니다.";

    }

    public List<WiseSaying> modify() {
        return wiseSayings;
    }


}
