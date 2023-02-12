package com.app.flyvent.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("develop")
@SpringBootTest
class DestinationServiceTest {
    @Autowired
    DestinationService destinationService;

    @Test
    public void 영어_text에서_destination을_찾은_경우() throws Exception {
        //given
        String text = "Asia로!!";

        //when
        boolean canFind = destinationService.canFindDestinationIn(text);

        //then
        assertThat(canFind).isTrue();
    }

    @Test
    public void 영어_text에서_destination을_못_찾은_경우() throws Exception {
        //given
        String text = "집으로!!";

        //when
        boolean canFind = destinationService.canFindDestinationIn(text);

        //then
        assertThat(canFind).isFalse();
    }

    @Test
    public void 한글_text에서_destination을_찾은_경우() throws Exception {
        //given
        String text = "아시아로!!";

        //when
        boolean canFind = destinationService.canFindDestinationIn(text);

        //then
        assertThat(canFind).isTrue();
    }
    @Test
    public void 한글_text에서_destination을_못_찾은_경우() throws Exception {
        //given
        String text = "집으로!!";

        //when
        boolean canFind = destinationService.canFindDestinationIn(text);

        //then
        assertThat(canFind).isFalse();
    }
}