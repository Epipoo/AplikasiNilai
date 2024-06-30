package application;

import java.sql.SQLException;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
       
        GridPane mahasiswaLayout = new GridPane();
        mahasiswaLayout.setHgap(10);
        mahasiswaLayout.setVgap(10);

        GridPane mataKuliahLayout = new GridPane();
        mataKuliahLayout.setHgap(10);
        mataKuliahLayout.setVgap(10);

        GridPane ujianLayout = new GridPane();
        ujianLayout.setHgap(10);
        ujianLayout.setVgap(10);

        GridPane transkripLayout = new GridPane();
        transkripLayout.setHgap(10);
        transkripLayout.setVgap(10);

        // Form untuk mahasiswa
        TextField nimField = new TextField();
        nimField.setPromptText("Masukkan NIM");

        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleRadio = new RadioButton("Laki-laki");
        maleRadio.setToggleGroup(genderGroup);
        RadioButton femaleRadio = new RadioButton("Perempuan");
        femaleRadio.setToggleGroup(genderGroup);

        TextField namaField = new TextField();
        namaField.setPromptText("Masukkan Nama");
        TextArea alamatField = new TextArea();
        alamatField.setPromptText("Masukkan Alamat");
        alamatField.setMaxWidth(200);
        alamatField.setMaxHeight(50);
        TextField tempatLahirField = new TextField();
        tempatLahirField.setPromptText("Masukkan Tempat Lahir");
        DatePicker tanggalLahirPicker = new DatePicker();
        tanggalLahirPicker.setPromptText("Masukkan Tanggal Lahir");
        tanggalLahirPicker.setMaxWidth(200);

        Button rekamMahasiswaButton = new Button("Rekam Mahasiswa");
        rekamMahasiswaButton.setOnAction(e -> {
            String nim = nimField.getText();
            String jenisKelamin = ((RadioButton) genderGroup.getSelectedToggle()).getText();
            String nama = namaField.getText();
            String alamat = alamatField.getText();
            String tempatLahir = tempatLahirField.getText();
            LocalDate tanggalLahir = tanggalLahirPicker.getValue();

            Mahasiswa mahasiswa = new Mahasiswa(nim, jenisKelamin, nama, alamat, tempatLahir, tanggalLahir.toString());
            try {
                RekamData.rekamMahasiswa(mahasiswa);
                resetFields(nimField, namaField, tempatLahirField);
                alamatField.clear();
                tanggalLahirPicker.setValue(null);
                resetToggleGroup(genderGroup);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        mahasiswaLayout.add(new Label("NIM:"), 0, 0);
        mahasiswaLayout.add(nimField, 1, 0);
        mahasiswaLayout.add(new Label("Jenis Kelamin:"), 0, 1);
        mahasiswaLayout.add(maleRadio, 1, 1);
        mahasiswaLayout.add(femaleRadio, 1, 2);
        mahasiswaLayout.add(new Label("Nama:"), 0, 3);
        mahasiswaLayout.add(namaField, 1, 3);
        mahasiswaLayout.add(new Label("Alamat:"), 0, 4);
        mahasiswaLayout.add(alamatField, 1, 4);
        mahasiswaLayout.add(new Label("Tempat Lahir:"), 0, 5);
        mahasiswaLayout.add(tempatLahirField, 1, 5);
        mahasiswaLayout.add(new Label("Tanggal Lahir:"), 0, 6);
        mahasiswaLayout.add(tanggalLahirPicker, 1, 6);
        mahasiswaLayout.add(rekamMahasiswaButton, 1, 7);

        // Form untuk matkul
        TextField mkidField = new TextField();
        mkidField.setPromptText("MKID");
        TextField namaMkField = new TextField();
        namaMkField.setPromptText("Nama Mata Kuliah");
        TextField sksField = new TextField();
        sksField.setPromptText("SKS");
        sksField.setMinWidth(200);
        sksField.setMaxWidth(200);

        Button rekamMataKuliahButton = new Button("Rekam Mata Kuliah");
        rekamMataKuliahButton.setOnAction(e -> {
            String mkid = mkidField.getText();
            String namaMk = namaMkField.getText();
            int sks = Integer.parseInt(sksField.getText());

            MataKuliah mataKuliah = new MataKuliah(mkid, namaMk, sks);
            try {
                RekamData.rekamMataKuliah(mataKuliah);
                resetFields(mkidField, namaMkField, sksField);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        mataKuliahLayout.add(new Label("MKID:"), 0, 0);
        mataKuliahLayout.add(mkidField, 1, 0);
        mataKuliahLayout.add(new Label("Nama Mata Kuliah:"), 0, 1);
        mataKuliahLayout.add(namaMkField, 1, 1);
        mataKuliahLayout.add(new Label("SKS:"), 0, 2);
        mataKuliahLayout.add(sksField, 1, 2);
        mataKuliahLayout.add(rekamMataKuliahButton, 1, 3);

        // Form untuk nilai ujian
        TextField ujianNimField = new TextField();
        ujianNimField.setPromptText("NIM");
        TextField ujianMkidField = new TextField();
        ujianMkidField.setPromptText("MKID");
        TextField nilaiMkField = new TextField();
        nilaiMkField.setPromptText("Nilai Mata Kuliah");
        nilaiMkField.setMinWidth(200);
        nilaiMkField.setMaxWidth(200);

        Button rekamUjianButton = new Button("Rekam Ujian");
        rekamUjianButton.setOnAction(e -> {
            String nim = ujianNimField.getText();
            String mkid = ujianMkidField.getText();
            char nilaiMk = nilaiMkField.getText().charAt(0);

            Ujian ujian = new Ujian(nim, mkid, nilaiMk);
            try {
                RekamData.rekamUjian(ujian);
                resetFields(ujianNimField, ujianMkidField, nilaiMkField);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        ujianLayout.add(new Label("NIM:"), 0, 0);
        ujianLayout.add(ujianNimField, 1, 0);
        ujianLayout.add(new Label("MKID:"), 0, 1);
        ujianLayout.add(ujianMkidField, 1, 1);
        ujianLayout.add(new Label("Nilai Mata Kuliah:"), 0, 2);
        ujianLayout.add(nilaiMkField, 1, 2);
        ujianLayout.add(rekamUjianButton, 1, 3);

        // Transkrip Button
        TextField transkripNimField = new TextField();
        transkripNimField.setPromptText("NIM");
        transkripNimField.setMinWidth(200);
        transkripNimField.setMaxWidth(200);

        Button tampilkanTranskripButton = new Button("Tampilkan Transkrip");
        tampilkanTranskripButton.setOnAction(e -> {
            String nim = transkripNimField.getText();
            try {
            	Laporan laporanInstance = new Laporan();
            	laporanInstance.tampilkanTranskrip(nim, primaryStage);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        transkripLayout.add(new Label("NIM:"), 0, 0);
        transkripLayout.add(transkripNimField, 1, 0);
        transkripLayout.add(tampilkanTranskripButton, 1, 1);
        
        // TabPane setup
        TabPane tabPane = new TabPane();

        Tab mahasiswaTab = new Tab("Rekam Mahasiswa", mahasiswaLayout);
        Tab mataKuliahTab = new Tab("Rekam Mata Kuliah", mataKuliahLayout);
        Tab ujianTab = new Tab("Rekam Ujian", ujianLayout);
        Tab transkripTab = new Tab("Transkrip", transkripLayout);

        tabPane.getTabs().addAll(mahasiswaTab, mataKuliahTab, ujianTab, transkripTab);

        // Scene and Stage
        Scene scene = new Scene(tabPane, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Aplikasi Penilaian");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void resetFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    private void resetToggleGroup(ToggleGroup group) {
        group.getSelectedToggle().setSelected(false);
    }
}
