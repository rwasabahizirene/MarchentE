package com.restAssured.solution.pojos.users;

import java.util.Objects;

public class Geo {

    private float lat;
    private float lng;

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Geo geo = (Geo) o;
        return Float.compare(geo.getLat(), getLat()) == 0 &&
                Float.compare(geo.getLng(), getLng()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLat(), getLng());
    }

    @Override
    public String toString() {
        return "Geo{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
