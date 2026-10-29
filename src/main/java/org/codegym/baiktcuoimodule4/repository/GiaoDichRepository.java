package org.codegym.baiktcuoimodule4.repository;

import java.util.List;
import org.codegym.baiktcuoimodule4.entity.GiaoDich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GiaoDichRepository extends JpaRepository<GiaoDich, String> {

    @Query("SELECT g FROM GiaoDich g WHERE " +
           "(:ten IS NULL OR LOWER(g.khachHang.tenKhachHang) LIKE LOWER(CONCAT('%', :ten, '%'))) " +
           "AND (:loai IS NULL OR g.loaiDichVu = :loai)")
    List<GiaoDich> search(@Param("ten") String ten, @Param("loai") String loai);
}
