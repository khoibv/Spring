package vn.com.khoibv.spring.log4j2demo;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.lookup.StrLookup;

public class MarkerLookup implements StrLookup {
    @Override
    public String lookup(String key) {
        return null;
    }

    @Override
    public String lookup(LogEvent event, String key) {
        final Marker marker = event.getMarker();
        return marker == null ? null : marker.getName();
    }
}
