package com.trigo.scrapeandnoti.scrape;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@XmlRootElement(name = "CSchedule")
class CgvMovie {
    @XmlElement(name = "PlayDays")
    PlayDays PlayDays;

    @Getter
    @XmlRootElement(name = "PlayDays")
    static class PlayDays {
        @XmlElement(name = "CPlayDay")
        List<CPlayDay> CPlayDayList = null;
    }

    @Getter
    @XmlRootElement(name = "CPlayDays")
    @XmlAccessorType(XmlAccessType.FIELD)
    static class CPlayDay {
        @XmlElement(name = "PLAY_YMD")
        String PLAY_YMD;
        @XmlElement(name = "FORMAT_DATE")
        String FORMAT_DATE;
    }
    public String getDateList(){
        //List<String> dateList = new ArrayList<>();
        String dateList = "";
        for(CPlayDay item : PlayDays.CPlayDayList){
            dateList+=item.PLAY_YMD +",";
        }
        return dateList;
    }

}
