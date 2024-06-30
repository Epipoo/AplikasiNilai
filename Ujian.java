package application;

public class Ujian {
    private String nim;
    private String mkid;
    private char nilaiMk;

    public Ujian(String nim, String mkid, char nilaiMk) {
        this.nim = nim;
        this.mkid = mkid;
        this.nilaiMk = nilaiMk;
    }

    // Getter dan Setter
    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getMkid() {
        return mkid;
    }

    public void setMkid(String mkid) {
        this.mkid = mkid;
    }

    public char getNilaiMk() {
        return nilaiMk;
    }

    public void setNilaiMk(char nilaiMk) {
        this.nilaiMk = nilaiMk;
    }
}
