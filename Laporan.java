package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Laporan {
    public Label mnclNama = new Label();
    public Label mnclTempatTanggalLahir = new Label();
    public Label mnclJenisKelamin = new Label();
    public Label mnclNIM = new Label();
    public Label mnclAlamat = new Label();
    public Label mnclIPK = new Label();
    public TableView<MataKuliahNilai> tableView = new TableView<>();

    public void tampilkanTranskrip(String nim, Stage primaryStage) throws SQLException {
        Stage modal = new Stage();
        VBox vbox = new VBox();
        vbox.getChildren().addAll(mnclNama, mnclTempatTanggalLahir, mnclJenisKelamin, mnclNIM, mnclAlamat, mnclIPK, tableView);
        vbox.setPrefWidth(400);
        vbox.setPrefHeight(400);
        Scene scene = new Scene(vbox);
        modal.setScene(scene);
        modal.setTitle("Transkrip Mahasiswa");
        modal.show();

        String query = "SELECT m.nama, m.tempat_lahir, m.tanggal_lahir, m.jenis_kelamin, m.nim, m.alamat, mk.mkid, mk.nama_mk, mk.sks, u.nilai_mk, CharToNumeric(u.nilai_mk) AS konversi FROM mahasiswa m JOIN ujian u ON m.nim = u.nim JOIN matakuliah mk ON u.mkid = mk.mkid WHERE m.nim = ?";

        try (Connection conn = Dconnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nim);
            ResultSet rs = stmt.executeQuery();

            ObservableList<MataKuliahNilai> mataKuliahList = FXCollections.observableArrayList();
            double totalNilai = 0;
            int totalSKS = 0;

            while (rs.next()) {
                if (mnclNama.getText().isEmpty()) {
                    mnclNama.setText("Nama					: " + rs.getString("nama"));
                    mnclTempatTanggalLahir.setText("Tempat dan Tanggal Lahir	: " + rs.getString("tempat_lahir") + ", " + rs.getString("tanggal_lahir"));
                    mnclJenisKelamin.setText("Jenis Kelamin				: " + rs.getString("jenis_kelamin"));
                    mnclNIM.setText("Nomor Induk Mahasiswa	: " + rs.getString("nim"));
                    mnclAlamat.setText("Alamat					: " + rs.getString("alamat"));
                }

                int sks = rs.getInt("sks");
                double konversi = rs.getDouble("konversi");
                totalNilai += konversi * sks;
                totalSKS += sks;

                mataKuliahList.add(new MataKuliahNilai(
                        rs.getString("mkid"),
                        rs.getString("nama_mk"),
                        sks,
                        rs.getString("nilai_mk").charAt(0),
                        konversi
                ));
            }

            double ipk = totalSKS == 0 ? 0 : totalNilai / totalSKS;
            mnclIPK.setText("IPK						: " + String.format("%.2f", ipk));

            TableColumn<MataKuliahNilai, String> colID = new TableColumn<>("ID");
            colID.setCellValueFactory(new PropertyValueFactory<>("id"));

            TableColumn<MataKuliahNilai, String> colNamaMK = new TableColumn<>("Mata Kuliah");
            colNamaMK.setCellValueFactory(new PropertyValueFactory<>("matkul"));

            TableColumn<MataKuliahNilai, Integer> colSKS = new TableColumn<>("SKS");
            colSKS.setCellValueFactory(new PropertyValueFactory<>("sks"));

            TableColumn<MataKuliahNilai, String> colNilai = new TableColumn<>("Nilai");
            colNilai.setCellValueFactory(new PropertyValueFactory<>("nilai"));

            TableColumn<MataKuliahNilai, Double> colKonversi = new TableColumn<>("Konversi");
            colKonversi.setCellValueFactory(new PropertyValueFactory<>("konversi"));

            tableView.getColumns().setAll(colID, colNamaMK, colSKS, colNilai, colKonversi);
            tableView.setItems(mataKuliahList);
        }
    }

    public static class MataKuliahNilai {
        private final SimpleStringProperty id;
        private final SimpleStringProperty matkul;
        private final SimpleIntegerProperty sks;
        private final SimpleStringProperty nilai;
        private final SimpleIntegerProperty konversi;

        public MataKuliahNilai(String id, String matkul, int sks, char nilai, double konversi) {
            this.id = new SimpleStringProperty(id);
            this.matkul = new SimpleStringProperty(matkul);
            this.sks = new SimpleIntegerProperty(sks);
            this.nilai = new SimpleStringProperty(String.valueOf(nilai));
            this.konversi = new SimpleIntegerProperty((int) konversi);
        }

        public String getId() {
            return id.get();
        }

        public String getMatkul() {
            return matkul.get();
        }

        public int getSks() {
            return sks.get();
        }

        public String getNilai() {
            return nilai.get();
        }

        public int getKonversi() {
            return konversi.get();
        }
    }
}
