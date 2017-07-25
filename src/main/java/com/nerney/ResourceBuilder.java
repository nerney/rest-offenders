package com.nerney;

import com.nerney.domain.Address;
import com.nerney.domain.Offender;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nerney on 7/14/2017.
 */
@Component
public class ResourceBuilder implements ApplicationListener<ApplicationReadyEvent> {
    private OffenderRepository repository;

    public ResourceBuilder(OffenderRepository repository) {
        this.repository = repository;
    }

    private void run() {
        String url = "http://www.paroleboard.ri.gov/sexoffender/olist/listings.php?level=II";
        List<String> links = parseLinks(url);
        url = url + "I";
        links.addAll(parseLinks(url));
        List<Offender> offenderList = parseOffenders(links);
        repository.save(offenderList);
    }

    private List<Offender> parseOffenders(List<String> links) {
        List<Offender> offenders = new ArrayList<>();
        for (String link : links) {
            Offender offender = new Offender(link);
            if (link.endsWith("III")) {
                offender.setLevel("III");
            } else {
                offender.setLevel("II");
            }
            Document doc = new Document("");
            try {
                doc = Jsoup.connect(link).get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Elements elements = doc.select("table table").first().select("td:has(b)");
            Address address = new Address();
            for (Element element : elements) {
                String txt = element.text();
                if (txt.contains("Name:")) {
                    txt = txt.substring(6);
                    offender.setName(txt);
                }
                if (txt.contains("Address:")) {
                    txt = txt.substring(9);
                    address.setStreet(txt);
                }
                if (txt.contains("City/Town:")) {
                    txt = txt.substring(11);
                    address.setCity(txt);
                }
                if (txt.contains("Date of Birth:")) {
                    txt = txt.substring(15);
                    offender.setDob(txt);
                }
                if (txt.contains("Sex:")) {
                    txt = txt.substring(5);
                    offender.setSex(txt);
                }
                if (txt.contains("Race:")) {
                    txt = txt.substring(6);
                    offender.setRace(txt);
                }
                if (txt.contains("Height:")) {
                    txt = txt.substring(8);
                    offender.setHeight(txt);
                }
                if (txt.contains("Weight:")) {
                    txt = txt.substring(8);
                    offender.setWeight(txt);
                }
                if (txt.contains("Eye Color:")) {
                    txt = txt.substring(11);
                    offender.setEyes(txt);
                }
                if (txt.contains("Hair Color:")) {
                    txt = txt.substring(12);
                    offender.setHair(txt);
                }
                if (txt.contains("Convicted of:")) {
                    txt = txt.substring(14);
                    offender.setConvictedOf(txt);
                }
                if (txt.contains("Community Supervision:")) {
                    txt = txt.substring(23);
                    offender.setSupervision(txt);
                }
            }
            offender.setAddress(address);
            offenders.add(offender);
        }
        return offenders;
    }

    private List<String> parseLinks(String url) {
        Document doc = new Document("");
        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Elements elements = doc.select("table").select("a[href]");
        List<String> list = new ArrayList<>();
        for (Element element : elements) {
            String baseUrl = "http://www.paroleboard.ri.gov/sexoffender/olist/";
            String link = baseUrl + element.attr("href");
            list.add(link);
        }
        return list;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        run();
    }
}
