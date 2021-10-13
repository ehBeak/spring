package jpabook.jpashop.domain;

import jdk.jfr.Enabled;

import javax.persistence.Entity;

@Entity // Entity 추가!!
public class Album {
    private String artist;
    private String ect;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getEct() {
        return ect;
    }

    public void setEct(String ect) {
        this.ect = ect;
    }
}
