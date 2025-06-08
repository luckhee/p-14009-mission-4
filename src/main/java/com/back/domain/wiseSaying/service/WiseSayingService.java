package com.back.domain.wiseSaying.service;

import com.back.domain.global.Rq;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;
import java.util.Scanner;

public class WiseSayingService {
    private Scanner scanner;

    public WiseSayingService(Scanner scanner) {
        this.scanner = scanner;
    }

    private WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();

    public WiseSaying write(String content ,String author) {
        WiseSaying wiseSaying = wiseSayingRepository.save(content, author);
        return wiseSaying;
    }

    public List<WiseSaying> findForList() {
        return wiseSayingRepository.findList();
    }

    public String delete(String cmd) {
        Rq rq = new Rq(cmd);
        int id = rq.getParamAsInt("id",-1);

        return wiseSayingRepository.delete(id);



    }

    public String modify(String cmd) {
        Rq rq = new Rq(cmd);
        int id = rq.getParamAsInt("id",-1);

        List<WiseSaying> wiseSayings = wiseSayingRepository.modify();
        String result = requestModify(wiseSayings, id);

        return result;
    }

    public String requestModify(List<WiseSaying> wiseSayings, int id){
        boolean exists = wiseSayings.stream()
                .anyMatch(e -> e.getId() == id);
        if( !exists ) return id + "번 명언은 존재하지 않습니다.";
        String content = "";
        String author = "";
        for(WiseSaying x : wiseSayings) {
            if(x.getId() == id) {
                System.out.println("명언(기존) : " +x.getContent());
                System.out.printf("명언 : ");
                content = scanner.nextLine();
                x.setContent(content);

                System.out.println("작가(기존) : " +x.getAuthor());
                author = scanner.nextLine();
                x.setAuthor(author);
            }

        }
        return "%d / %S / %S".formatted(id,author,content);

    }

}
