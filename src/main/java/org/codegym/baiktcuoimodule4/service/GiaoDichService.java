package org.codegym.baiktcuoimodule4.service;

import java.util.List;
import org.codegym.baiktcuoimodule4.entity.GiaoDich;
import org.codegym.baiktcuoimodule4.entity.KhachHang;
import org.codegym.baiktcuoimodule4.repository.GiaoDichRepository;
import org.codegym.baiktcuoimodule4.repository.KhachHangRepository;
import org.springframework.stereotype.Service;

@Service
public class GiaoDichService {

    private final GiaoDichRepository giaoDichRepository;
    private final KhachHangRepository khachHangRepository;

    public GiaoDichService(GiaoDichRepository giaoDichRepository, KhachHangRepository khachHangRepository) {
        this.giaoDichRepository = giaoDichRepository;
        this.khachHangRepository = khachHangRepository;
    }

    public List<GiaoDich> findAll() {
        return giaoDichRepository.findAll();
    }

    public GiaoDich findById(String ma) {
        return giaoDichRepository.findById(ma).orElse(null);
    }

    public void save(GiaoDich gd) {
        giaoDichRepository.save(gd);
    }

    public void delete(String ma) {
        giaoDichRepository.deleteById(ma);
    }

    public List<KhachHang> findAllKhachHang() {
        return khachHangRepository.findAll();
    }

    public List<GiaoDich> search(String ten, String loai) {
        String t = (ten == null || ten.isBlank()) ? null : ten;
        String l = (loai == null || loai.isBlank()) ? null : loai;
        return giaoDichRepository.search(t, l);
    }
}
