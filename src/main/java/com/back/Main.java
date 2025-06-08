package com.back;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        lab1();

        lab2();
    }

    private static void lab2() {
        System.out.println("안녕하세요.");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 데이터를 메모리의 바이트로 저장하는 출력 스트림
        PrintStream printStream = new PrintStream(byteArrayOutputStream,false); // 이거 쓰면 목적지가 콘솔이 아니라 byteArrayOutputStream 으로
        System.setOut(printStream); // 이건 출력을 printStream으로 변경

        System.out.println("반값습니다.");
        String str = byteArrayOutputStream.toString();

        System.out.println("좋아요 하하.");
        String str1 = byteArrayOutputStream.toString();


    }

    private static void lab1() {
        String input = "등록\n나의 죽음을 적에게 알리지 말라";


        Scanner sc = new Scanner(input);

        String cmd = sc.nextLine();
        String content = sc.nextLine();


        System.out.println(cmd);
        System.out.println(content);
    }
}
