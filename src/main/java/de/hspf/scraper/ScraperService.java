package de.hspf.scraper;

import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScraperService {
    private final String url = "https://www.efa-bw.de/nvbw/XSLT_DM_REQUEST?language=de&itdLPxx_frames=&itdLPxx_origin=";

    public List<Departure> getdepartures(String station){
        List<Departure> departureList = new ArrayList<>();
        try (final WebClient webClient = new WebClient()){

            final HtmlForm form;
            final HtmlElement submitButton;
            final HtmlPage startPage;
            final HtmlTextInput inputFieldOrigin;

            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getCookieManager().setCookiesEnabled(true);
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setTimeout(2000);
            webClient.getOptions().setUseInsecureSSL(true);
            // overcome problems in JavaScript
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setPrintContentOnFailingStatusCode(false);
            webClient.setCssErrorHandler(new SilentCssErrorHandler());
            startPage = webClient.getPage(url);
            form = startPage.getForms().get(0);
            submitButton = (HtmlElement) form.getByXPath("//button[@class='btn_send']").get(0);
            inputFieldOrigin = (HtmlTextInput) startPage.getElementById("input_extended_search_dm");

            inputFieldOrigin.focus();
            inputFieldOrigin.type(station);
            final HtmlPage resultPage = submitButton.click();
            final HtmlForm form2 = resultPage.getForms().get(0);
            final HtmlElement button = (HtmlElement) form2.getByXPath("//button[@class='btn_send']").get(0);
            final HtmlSelect select = (HtmlSelect) form2.getByXPath("//select[@class='no_border']").get(0);
            final HtmlOption option = (HtmlOption) select.getOptionByValue("all");
            final HtmlPage page = button.click();
            final List<HtmlTable> tables = page.getByXPath("//table[@class='table-monitor margin_top_little']");
            for (final HtmlTableRow row : tables.get(0).getRows()) {
                Departure departure = new Departure();
                departure.setDestination(row.getCell(5).asText());
                departure.setTime(row.getCell(1).asText());
                departure.setTransportationNumber(row.getCell(4).asText());
                HtmlElement transportationType = (HtmlElement) row.getByXPath("//img").get(1);
                departure.setTransportationType(transportationType.getAttribute("alt"));
                departureList.add(departure);
            }

        }catch (Exception e){

        }
        departureList.remove(0);
        return departureList;
    }
}
