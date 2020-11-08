package pl.coderslab.workshop.users;

import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.workshop.aircraft.AircraftRepository;
import pl.coderslab.workshop.aircraft.AircraftService;
import java.io.IOException;


@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final AircraftService aircraftService;
    private final AircraftRepository aircraftRepository;
    private final String url = "https://www.samoloty.pl";

    @GetMapping("/mainPage")
    public String mainPage(Model model) throws IOException {

        Connection connection = Jsoup.connect(url);
        Document doc = connection.get();

        Elements firstArticle = doc.getElementsByClass("items-row cols-1 row-0 row-fluid");
        Element firstTitle = firstArticle.select("h2 > a").first();
        Element firstDescription = firstArticle.select("p").first();
        model.addAttribute("firstArticle", firstDescription.text());
        model.addAttribute("firstTitle", firstTitle.text());
        String firstHref = firstArticle.select(".btn").attr("href");
        model.addAttribute("firstLink", url + firstHref);

        Elements secondArticle = doc.getElementsByClass("items-row cols-1 row-1 row-fluid");
        Element secondTitle = secondArticle.select("h2 > a").first();
        Element secondDescription = secondArticle.select("p").first();
        model.addAttribute("secondArticle", secondDescription.text());
        model.addAttribute("secondTitle", secondTitle.text());
        String secondHref = secondArticle.select(".btn").attr("href");
        model.addAttribute("secondLink", url + secondHref);

        return "user/mainPage";
    }

    @GetMapping("/changeData")
    @ResponseBody
    public String changeData(@AuthenticationPrincipal UserDetails customUser){

        return "logged as " + customUser;
    }
}
