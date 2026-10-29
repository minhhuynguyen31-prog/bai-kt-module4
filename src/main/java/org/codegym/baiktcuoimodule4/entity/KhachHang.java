package org.codegym.baiktcuoimodule4.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "khach_hang")
@Getter
@Setter
public class KhachHang {

    @Id
    @Column(name = "ma_khach_hang", length = 50)
    private String maKhachHang;

    @Column(name = "ten_khach_hang", length = 100, nullable = false)
    private String tenKhachHang;

    @Column(name = "so_dien_thoai", length = 20)
    private String soDienThoai;

    @Column(name = "email", length = 100)
    private String email;
}
