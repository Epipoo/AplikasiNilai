package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RekamData {
    public static void rekamMahasiswa(Mahasiswa mahasiswa) throws SQLException {
        String query = "INSERT INTO mahasiswa (nim, jenis_kelamin, nama, alamat, tempat_lahir, tanggal_lahir) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Dconnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, mahasiswa.getNim());
            stmt.setString(2, mahasiswa.getJenisKelamin());
            stmt.setString(3, mahasiswa.getNama());
            stmt.setString(4, mahasiswa.getAlamat());
            stmt.setString(5, mahasiswa.getTempatLahir());
            stmt.setString(6, mahasiswa.getTanggalLahir());
            stmt.executeUpdate();
        }
    }

    public static void rekamMataKuliah(MataKuliah mataKuliah) throws SQLException {
        String query = "INSERT INTO matakuliah (mkid, nama_mk, sks) VALUES (?, ?, ?)";
        try (Connection conn = Dconnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, mataKuliah.getMkid());
            stmt.setString(2, mataKuliah.getNamaMk());
            stmt.setInt(3, mataKuliah.getSks());
            stmt.executeUpdate();
        }
    }

    public static void rekamUjian(Ujian ujian) throws SQLException {
        String query = "INSERT INTO ujian (nim, mkid, nilai_mk) VALUES (?, ?, ?)";
        try (Connection conn = Dconnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, ujian.getNim());
            stmt.setString(2, ujian.getMkid());
            stmt.setString(3, ujian.getNilaiMk()+"");
            stmt.executeUpdate();
        }
    }
}