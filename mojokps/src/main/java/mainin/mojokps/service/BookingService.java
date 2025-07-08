package mainin.mojokps.service;

import mainin.mojokps.model.Booking;
import mainin.mojokps.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public Booking findById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }

    public List<Booking> findByTanggal(LocalDate tanggal) {
        return bookingRepository.findByTanggalBooking(tanggal);
    }

    public int getTotalPendapatanHari(LocalDate tanggal) {
        Integer total = bookingRepository.totalPendapatanHari(tanggal);
        return total != null ? total : 0;
    }

    public int getTotalBookingBulan(int bulan, int tahun) {
        Integer total = bookingRepository.totalBookingBulan(bulan, tahun);
        return total != null ? total : 0;
    }

    public int getTotalPendapatanBulan(int bulan, int tahun) {
        Integer total = bookingRepository.totalPendapatanBulan(bulan, tahun);
        return total != null ? total : 0;
    }
}
