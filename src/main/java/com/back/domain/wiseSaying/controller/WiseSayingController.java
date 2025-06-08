package com.back.domain.wiseSaying.controller;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private Scanner scanner;
    private WiseSayingService wiseSayingService;



    public WiseSayingController(Scanner scanner) {
        this.scanner = scanner;
        wiseSayingService = new WiseSayingService(scanner);
    }
    public void actionWirte() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.print("작가 : ");
        String author = scanner.nextLine();

        WiseSaying wiseSaying = wiseSayingService.write(content,author);

        System.out.printf("%d번 명언이 등록되었습니다.",wiseSaying.getId());

    }


    public void actionList() {

        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        List<WiseSaying> wiseSayings = wiseSayingService.findForList();

        for(WiseSaying wiseSaying : wiseSayings) {
            System.out.printf("%d / %s / %s\n",wiseSaying.getId(),wiseSaying.getAuthor(),wiseSaying.getContent());


        }

    }

    public void actionDelete(String cmd) {
        String result = wiseSayingService.delete(cmd);
        System.out.println(result);
    }



    public void actionModify(String cmd) {
        String result = wiseSayingService.modify(cmd);
        System.out.println(result);
    }
}
