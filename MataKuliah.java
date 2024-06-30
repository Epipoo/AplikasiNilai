package application;

public class MataKuliah {
    private String mkid;
    private String namaMk;
    private int sks;

    public MataKuliah(String mkid, String namaMk, int sks) {
        this.mkid = mkid;
        this.namaMk = namaMk;
        this.sks = sks;
    }

  public String getMkid() {
        return mkid;
    }

    public void setMkid(String mkid) {
        this.mkid = mkid;
    }

    public String getNamaMk() {
        return namaMk;
    }

    public void setNamaMk(String namaMk) {
        this.namaMk = namaMk;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }
}