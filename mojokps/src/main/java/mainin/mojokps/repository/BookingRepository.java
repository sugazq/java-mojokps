package mainin.mojokps.repository;

import mainin.mojokps.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByTanggalBooking(LocalDate tanggal);

    // Total pendapatan per hari
    @Query("SELECT SUM(b.harga) FROM Booking b WHERE b.tanggalBooking = :tanggal")
    Integer totalPendapatanHari(LocalDate tanggal);

    // Total booking per bulan
    @Query("SELECT COUNT(b) FROM Booking b WHERE MONTH(b.tanggalBooking) = :bulan AND YEAR(b.tanggalBooking) = :tahun")
    Integer totalBookingBulan(int bulan, int tahun);

    // Total pendapatan per bulan
    @Query("SELECT SUM(b.harga) FROM Booking b WHERE MONTH(b.tanggalBooking) = :bulan AND YEAR(b.tanggalBooking) = :tahun")
    Integer totalPendapatanBulan(int bulan, int tahun);
}
