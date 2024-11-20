package org.ba.a;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.logging.Level;

public class JsoupProvider {

    public static String getString() {

        try {
            Document doc = Jsoup.connect("http://google.com").get();
            return doc.location();
        } catch (IOException e) {
            java.util.logging.Logger.getLogger(JsoupProvider.class.getSimpleName()).log(Level.SEVERE, e.toString());
            return null;
        }
    }


}
