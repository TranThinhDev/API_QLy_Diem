package model;

public class KetQua {
    private String maSV;
    private String maMon;
    private int lanHoc;
    private Double diemCC;
    private Double diemKT;
    private Double diemThiLan1;
    private Double diemThiLan2;
    private Double diemTK;
    private String hocKy;
    private String ghiChu;
    private String maHP;
    private String danhGia;

    public KetQua() {}

    public KetQua(String maSV, String maMon, int lanHoc, Double diemCC, Double diemKT,
                  Double diemThiLan1, Double diemThiLan2, Double diemTK,
                  String hocKy, String ghiChu, String maHP, String danhGia) {
        this.maSV = maSV;
        this.maMon = maMon;
        this.lanHoc = lanHoc;
        this.diemCC = diemCC;
        this.diemKT = diemKT;
        this.diemThiLan1 = diemThiLan1;
        this.diemThiLan2 = diemThiLan2;
        this.diemTK = diemTK;
        this.hocKy = hocKy;
        this.ghiChu = ghiChu;
        this.maHP = maHP;
        this.danhGia = danhGia;
    }

    // Getters and setters (có thể sinh tự động nếu dùng IDE)
    // ...

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public int getLanHoc() {
        return lanHoc;
    }

    public void setLanHoc(int lanHoc) {
        this.lanHoc = lanHoc;
    }

    public Double getDiemCC() {
        return diemCC;
    }

    public void setDiemCC(Double diemCC) {
        this.diemCC = diemCC;
    }

    public Double getDiemKT() {
        return diemKT;
    }

    public void setDiemKT(Double diemKT) {
        this.diemKT = diemKT;
    }

    public Double getDiemThiLan1() {
        return diemThiLan1;
    }

    public void setDiemThiLan1(Double diemThiLan1) {
        this.diemThiLan1 = diemThiLan1;
    }

    public Double getDiemThiLan2() {
        return diemThiLan2;
    }

    public void setDiemThiLan2(Double diemThiLan2) {
        this.diemThiLan2 = diemThiLan2;
    }

    public Double getDiemTK() {
        return diemTK;
    }

    public void setDiemTK(Double diemTK) {
        this.diemTK = diemTK;
    }

    public String getHocKy() {
        return hocKy;
    }

    public void setHocKy(String hocKy) {
        this.hocKy = hocKy;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getMaHP() {
        return maHP;
    }

    public void setMaHP(String maHP) {
        this.maHP = maHP;
    }

    public String getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(String danhGia) {
        this.danhGia = danhGia;
    }
}
