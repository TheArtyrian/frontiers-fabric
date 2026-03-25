package net.vertisoft.vectorlib.exclusive.datagen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VectorDatagen
{
    public static final String EN_US = "en_us";

    public static final List<Caption> CAPTIONS = new ArrayList<>();

    public static class Caption
    {
        private final String id;
        private final Map<String, String> text;

        public static Caption of(String id, Map<String, String> text) { return new Caption(id, text); }
        public static Map.Entry<String, String> englishUS(String text) {return Map.entry(EN_US, text); }

        private Caption(String id, Map<String, String> text)
        {
            this.id = id;
            this.text = text;
        }

        public String id() { return id; }
        public Map<String, String> text() { return text; }
    }
}
