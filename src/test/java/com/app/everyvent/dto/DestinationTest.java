package com.app.everyvent.dto;

import com.app.everyvent.constant.Continent;
import com.app.everyvent.constant.Country;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DestinationTest {

    @Test
    public void city로_찾기() throws Exception {
        //given
        String keyword = "Seoul";

        //when
        DestinationDto destination = DestinationDto.of(keyword);

        Country country = Country.findByCity(keyword);
        Continent continent = Continent.findByCountry(country);

        //then
        assertThat(destination.getCountry()).isEqualTo(country);
        assertThat(destination.getContinent()).isEqualTo(continent);
        assertThat(destination.getCity()).isEqualTo(keyword.toUpperCase());
    }

    @Test
    public void country로_찾기() throws Exception {
        //given
        String keyword = "Japan";

        //when
        DestinationDto destination = DestinationDto.of(keyword);

        Country country = Country.findByName(keyword);
        Continent continent = Continent.findByCountry(country);
        //then
        assertThat(destination.getCountry()).isEqualTo(country);
        assertThat(destination.getContinent()).isEqualTo(continent);
        assertThat(destination.getCity()).isNull();
    }
}