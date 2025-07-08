package mainin.mojokps.controller;

import jakarta.servlet.http.HttpSession;
import mainin.mojokps.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class DashboardController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        // Cek apakah admin sudah login
        if (session.getAttribute("loggedInAdmin") == null) {
            return "redirect:/login";
        }

        LocalDate today = LocalDate.now();

        int totalBookingHari = bookingService.findByTanggal(today).size();
        int totalPendapatanHari = bookingService.getTotalPendapatanHari(today);

        int totalBookingBulan = bookingService.getTotalBookingBulan(today.getMonthValue(), today.getYear());
        int totalPendapatanBulan = bookingService.getTotalPendapatanBulan(today.getMonthValue(), today.getYear());

        model.addAttribute("totalBookingHari", totalBookingHari);
        model.addAttribute("totalPendapatanHari", totalPendapatanHari);
        model.addAttribute("totalBookingBulan", totalBookingBulan);
        model.addAttribute("totalPendapatanBulan", totalPendapatanBulan);

        return "dashboard";
    }
}
