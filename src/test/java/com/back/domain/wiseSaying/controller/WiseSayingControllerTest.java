package com.back.domain.wiseSaying.controller;

import com.back.AppTestRunner;
import com.back.domain.global.Rq;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingControllerTest {
    @Test
    @DisplayName("등록")
    void t1() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(rs)
                .contains("명언 : ")
                .contains("작가 : ");
    }

    @Test
    @DisplayName("등록시 생성된 명언번호 노출")
    void t2() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(rs)
                .contains("1번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("등록할때 마다 생성되는 명언번호가 증가")
    void t3() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(rs)
                .contains("1번 명언이 등록되었습니다.")
                .contains("2번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("목록")
    void t4() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록
                """);

        assertThat(rs)
                .contains("번호 / 작가 / 명언")
                .contains("----------------------")
                .contains("2 / 작자미상 / 과거에 집착하지 마라.")
                .contains("1 / 작자미상 / 현재를 사랑하라.");
    }

    @Test
    @DisplayName("Rq테스트")
    void t5() {
        Rq rq = new Rq("삭제?id=1");
        int id = rq.getParamAsInt("id", -1);

        assertThat(id).isEqualTo(1);
    }

    @Test
    @DisplayName("Rq테스트 - id 파라미터가 없는 경우")
    void t6() {
        Rq rq = new Rq("삭제");
        int id = rq.getParamAsInt("id", -1);

        assertThat(id).isEqualTo(-1);
    }

    @Test
    @DisplayName("Rq테스트 - id 파라미터가 없는 경우2")
    void t7() {
        Rq rq = new Rq("삭제?id=");
        int id = rq.getParamAsInt("id", -1); // -1

        assertThat(id).isEqualTo(-1);
    }

    @Test
    @DisplayName("Rq테스트 - id 파라미터가 없는 경우2")
    void t8() {
        Rq rq = new Rq("삭제?id=일번");
        int id = rq.getParamAsInt("id", -1); // -1

        assertThat(id).isEqualTo(-1);
    }



    @Test
    @DisplayName("Rq테스트 - id 파라미터가 없는 경우2")
    void t9() {
        Rq rq = new Rq("목록?searchKeyword=영광");
        String searchKeyword = rq.getParam("searchKeyword", ""); // "영광"

        assertThat(searchKeyword).isEqualTo("영광");
    }


    @Test
    @DisplayName("Rq테스트 - id 파라미터가 없는 경우2")
    void t10() {
        Rq rq = new Rq("목록");
        String searchKeyword = rq.getParam("searchKeyword", ""); // ""

        assertThat(searchKeyword).isEqualTo("");
    }

    Rq rq = new Rq("목록?searchKeyword=");
    String searchKeyword = rq.getParam("searchKeyword", ""); // ""

    @Test
    @DisplayName("Rq테스트 - searchKeyword 파라미터 값이 없는 경우")
    void t11() {
        Rq rq = new Rq("목록");
        String searchKeyword = rq.getParam("searchKeyword", ""); // ""

        assertThat(searchKeyword).isEqualTo("");
    }

    @Test
    @DisplayName("1번 명언 삭제")
    void t12() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록
                삭제?id=1
                """);

        assertThat(rs)
                .contains("번호 / 작가 / 명언")
                .contains("----------------------")
                .contains("2 / 작자미상 / 과거에 집착하지 마라.")
                .contains("1 / 작자미상 / 현재를 사랑하라.")
                .contains("1번 명언이 삭제되었습니다.");
    }

    @Test
    @DisplayName("없는 번호 삭제시 예외처리")
    void t13() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                삭제?id=1
                목록
                """);

        assertThat(rs)
                .contains("1번 명언이 삭제되었습니다.")
                .contains("2 / 작자미상 / 과거에 집착하지 마라.")
                .doesNotContain("1 / 작자미상 / 현재를 사랑하라.");
    }

    @Test
    @DisplayName("수정?id=1")
    void t14() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                수정?id=3
                수정?id=1
                현재를 사랑하세요.
                아리스토텔레스
                목록
                """);

        assertThat(rs)
                .contains("3번 명언은 존재하지 않습니다.")
                .contains("1 / 아리스토텔레스 / 현재를 사랑하세요.")
                .doesNotContain("1 / 작자미상 / 현재를 사랑하라.");
    }

}
