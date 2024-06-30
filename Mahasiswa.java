	package application;
	
	public class Mahasiswa {
	    private String nim;
	    private String jenisKelamin;
	    private String nama;
	    private String alamat;
	    private String tempatLahir;
	    private String tanggalLahir;
	
	    public Mahasiswa(String nim, String jenisKelamin, String nama, String alamat, String tempatLahir, String tanggalLahir) {
	        this.nim = nim;
	        this.jenisKelamin = jenisKelamin;
	        this.nama = nama;
	        this.alamat = alamat;
	        this.tempatLahir = tempatLahir;
	        this.tanggalLahir = tanggalLahir;
	    }
	
	    public String getNim() {
	        return nim;
	    }
	
	    public void setNim(String nim) {
	        this.nim = nim;
	    }
	
	    public String getJenisKelamin() {
	        return jenisKelamin;
	    }
	
	    public void setJenisKelamin(String jenisKelamin) {
	        this.jenisKelamin = jenisKelamin;
	    }
	
	    public String getNama() {
	        return nama;
	    }
	
	    public void setNama(String nama) {
	        this.nama = nama;
	    }
	
	    public String getAlamat() {
	        return alamat;
	    }
	    
	    public void setAlamat(String alamat) {
	        this.alamat = alamat;
	    }
	
	    public String getTempatLahir() {
	        return tempatLahir;
	    }
	
	    public void setTempatLahir(String tempatLahir) {
	        this.tempatLahir = tempatLahir;
	    }
	
	    public String getTanggalLahir() {
	        return tanggalLahir;
	    }
	
	    public void setTanggalLahir(String tanggalLahir) {
	        this.tanggalLahir = tanggalLahir;
	    }
	}
