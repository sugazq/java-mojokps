package mainin.mojokps.controller;

import jakarta.servlet.http.HttpSession;
import mainin.mojokps.model.Booking;
import mainin.mojokps.model.Booking.Kategori;
import mainin.mojokps.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public String listBooking(Model model, HttpSession session) {
        if (session.getAttribute("loggedInAdmin") == null) {
            return "redirect:/login";
        }

        List<Booking> bookings = bookingService.findAll();
        model.addAttribute("bookings", bookings);
        return "booking";
    }

    @GetMapping("/add")
    public String showAddForm(Model model, HttpSession session) {
        if (session.getAttribute("loggedInAdmin") == null) {
            return "redirect:/login";
        }

        model.addAttribute("booking", new Booking());
        model.addAttribute("kategoris", Arrays.asList(Kategori.values()));
        return "formBooking";
    }

    @PostMapping("/save")
    public String saveBooking(@ModelAttribute Booking booking, HttpSession session) {
        if (session.getAttribute("loggedInAdmin") == null) {
            return "redirect:/login";
        }

        // Set created_at otomatis dan tanggalBooking tidak null
        if (booking.getTanggalBooking() == null) {
            booking.setTanggalBooking(LocalDate.now());
        }

        bookingService.save(booking);
        return "redirect:/booking";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        if (session.getAttribute("loggedInAdmin") == null) {
            return "redirect:/login";
        }

        Booking booking = bookingService.findById(id);
        if (booking == null) {
            return "redirect:/booking";
        }

        model.addAttribute("booking", booking);
        model.addAttribute("kategoris", Arrays.asList(Kategori.values()));
        return "formBooking";
    }

    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("loggedInAdmin") == null) {
            return "redirect:/login";
        }

        bookingService.delete(id);
        return "redirect:/booking";
    }
}
