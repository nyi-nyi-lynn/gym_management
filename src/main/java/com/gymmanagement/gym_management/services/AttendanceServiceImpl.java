package com.gymmanagement.gym_management.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management.dtos.AttendanceRequest;
import com.gymmanagement.gym_management.dtos.AttendanceResponse;
import com.gymmanagement.gym_management.entities.Attendance;
import com.gymmanagement.gym_management.entities.Member;
import com.gymmanagement.gym_management.enums.AttendanceStatus;
import com.gymmanagement.gym_management.repositories.AttendanceRepo;
import com.gymmanagement.gym_management.repositories.MemberRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService{
    private final AttendanceRepo attendanceRepo;
    private final MemberRepo memberRepo;

    @Override
    public AttendanceResponse checkIn(AttendanceRequest req) {

        Member member = memberRepo.findById(req.getMemberId())
                .orElseThrow();

        Attendance att = new Attendance();
        att.setMember(member);
        att.setCheckInTime(LocalDateTime.now());
        att.setDate(LocalDate.now());
        att.setSource(req.getSource());
        System.out.println(req.getSource());
        att.setStatus(AttendanceStatus.CHECKED_IN);

        attendanceRepo.save(att);

        return map(att);
    }

    @Override
    public AttendanceResponse checkOut(Long id) {
        Attendance att = attendanceRepo.findById(id).orElseThrow();

        att.setCheckOutTime(LocalDateTime.now());
        att.setStatus(AttendanceStatus.CHECKED_OUT);

        attendanceRepo.save(att);

        return map(att);
    }

    @Override
    public List<AttendanceResponse> getAll() {
        return attendanceRepo.findAll().stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<AttendanceResponse> getMemberAttendance(Long memberId) {
        return attendanceRepo.findByMemberId(memberId).stream()
                .map(this::map)
                .toList();
     }


    //Helper Methods

    private AttendanceResponse map(Attendance a){
        AttendanceResponse r = new AttendanceResponse();
        r.setId(a.getId());
        r.setMemberId(a.getMember().getId());
        r.setCheckInTime(a.getCheckInTime());
        r.setCheckOutTime(a.getCheckOutTime());
        r.setDate(a.getDate());
        r.setStatus(a.getStatus());
        return r;
    }
}
