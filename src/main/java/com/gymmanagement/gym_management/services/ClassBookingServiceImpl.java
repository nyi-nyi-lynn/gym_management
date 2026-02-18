package com.gymmanagement.gym_management.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management.dtos.ClassBookingResponse;
import com.gymmanagement.gym_management.entities.ClassBooking;
import com.gymmanagement.gym_management.entities.ClassEntity;
import com.gymmanagement.gym_management.entities.Member;
import com.gymmanagement.gym_management.entities.Order;
import com.gymmanagement.gym_management.enums.BookingStatus;
import com.gymmanagement.gym_management.enums.ClassStatus;
import com.gymmanagement.gym_management.enums.PaymentStatus;
import com.gymmanagement.gym_management.repositories.ClassBookingRepo;
import com.gymmanagement.gym_management.repositories.ClassRepo;
import com.gymmanagement.gym_management.repositories.MemberRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassBookingServiceImpl implements ClassBookingService{
    private final ClassBookingRepo bookingRepo;
    private final ClassRepo classRepo;
    private final MemberRepo memberRepo;

    // ========================================
    // 1️ BOOK CLASS
    // ========================================
    @Override
    public ClassBookingResponse book(Order order) {


        if (!order.getPaymentStatus().equals(PaymentStatus.PAID)) {
            throw new RuntimeException("Order is not paid");
        }

        ClassEntity clazz = classRepo.findById(order.getReferenceId())
                .orElseThrow(() -> new RuntimeException("Class not found"));

        if (!clazz.getStatus().equals(ClassStatus.ACTIVE)) {
            throw new RuntimeException("Class is not active");
        }

        long bookedCount = bookingRepo.countByClassEntityId(clazz.getId());

        if (bookedCount >= clazz.getCapacity()) {
            throw new RuntimeException("Class is full");
        }

        Member member = memberRepo.findById(order.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // Prevent duplicate booking
        boolean alreadyBooked = bookingRepo
                .existsByMemberIdAndClassEntityIdAndStatus(
                        member.getId(), clazz.getId(), BookingStatus.BOOKED);

        if (alreadyBooked) {
            throw new RuntimeException("You already booked this class");
        }

        ClassBooking booking = new ClassBooking();
        booking.setOrder(order);
        booking.setMember(member);
        booking.setClassEntity(clazz);
        booking.setBookingDate(LocalDateTime.now());
        booking.setStatus(BookingStatus.BOOKED);

        bookingRepo.save(booking);

        return mapToResponse(booking);
    }

    // ========================================
    // 2️ CANCEL BOOKING
    // ========================================
    @Override
    public void cancel(Long bookingId, Long memberId) {

        ClassBooking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getMember().getId().equals(memberId)) {
            throw new RuntimeException("You are not allowed to cancel this booking");
        }

        if (!booking.getStatus().equals(BookingStatus.BOOKED)) {
            throw new RuntimeException("Only booked classes can be cancelled");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepo.save(booking);
    }

    // ========================================
    // 3️ MARK ATTENDANCE
    // ========================================
    @Override
    public void markAttendance(Long bookingId) {

        ClassBooking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getStatus().equals(BookingStatus.BOOKED)) {
            throw new RuntimeException("Cannot mark attendance");
        }

        booking.setStatus(BookingStatus.ATTENDED);
    }

    // ========================================
    // 4️ GET MY BOOKINGS
    // ========================================
    @Override
    public List<ClassBookingResponse> getMyBookings(Long memberId) {

        return bookingRepo.findByMemberId(memberId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ========================================
    // 5 GET BOOKINGS BY CLASS
    // ========================================
    @Override
    public List<ClassBookingResponse> getBookingsByClass(Long classId) {

        return bookingRepo.findByClassEntityId(classId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ========================================
    // MAPPER
    // ========================================
    private ClassBookingResponse mapToResponse(ClassBooking booking) {

        ClassBookingResponse response = new ClassBookingResponse();
        response.setId(booking.getId());
        response.setClassName(booking.getClassEntity().getName());
        response.setMemberName(booking.getMember().getUser().getName());
        response.setStatus(booking.getStatus());
        response.setBookingDate(booking.getBookingDate());
        return response;
    }
}
