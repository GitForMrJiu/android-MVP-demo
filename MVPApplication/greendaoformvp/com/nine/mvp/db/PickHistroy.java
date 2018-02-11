package com.nine.mvp.db;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "PickHistroy".
 */
@Entity(nameInDb = "PickHistroy")
public class PickHistroy {

    @Id(autoincrement = true)
    private Long ID;

    @NotNull
    private String VC_Name;

    @NotNull
    private String VC_Code;
    private String VC_SupplierCode;
    private String vc_billno;
    private String VC_Suppliername;
    private String VC_Specification;
    private String VC_Manufacturer;
    private String VC_Unit;
    private String VC_BatchNumber;
    private String D_Amount;
    private String vc_warehouseCode;
    private String vc_warehousename;
    private String posid;
    private String posno;
    private String D_amount1;
    private String D_money;
    private Integer State;
    private String CartonNo;
    private String OperateTime;
    private String temp;

    @Generated
    public PickHistroy() {
    }

    public PickHistroy(Long ID) {
        this.ID = ID;
    }

    @Generated
    public PickHistroy(Long ID, String VC_Name, String VC_Code, String VC_SupplierCode, String vc_billno, String VC_Suppliername, String VC_Specification, String VC_Manufacturer, String VC_Unit, String VC_BatchNumber, String D_Amount, String vc_warehouseCode, String vc_warehousename, String posid, String posno, String D_amount1, String D_money, Integer State, String CartonNo, String OperateTime, String temp) {
        this.ID = ID;
        this.VC_Name = VC_Name;
        this.VC_Code = VC_Code;
        this.VC_SupplierCode = VC_SupplierCode;
        this.vc_billno = vc_billno;
        this.VC_Suppliername = VC_Suppliername;
        this.VC_Specification = VC_Specification;
        this.VC_Manufacturer = VC_Manufacturer;
        this.VC_Unit = VC_Unit;
        this.VC_BatchNumber = VC_BatchNumber;
        this.D_Amount = D_Amount;
        this.vc_warehouseCode = vc_warehouseCode;
        this.vc_warehousename = vc_warehousename;
        this.posid = posid;
        this.posno = posno;
        this.D_amount1 = D_amount1;
        this.D_money = D_money;
        this.State = State;
        this.CartonNo = CartonNo;
        this.OperateTime = OperateTime;
        this.temp = temp;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    @NotNull
    public String getVC_Name() {
        return VC_Name;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setVC_Name(@NotNull String VC_Name) {
        this.VC_Name = VC_Name;
    }

    @NotNull
    public String getVC_Code() {
        return VC_Code;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setVC_Code(@NotNull String VC_Code) {
        this.VC_Code = VC_Code;
    }

    public String getVC_SupplierCode() {
        return VC_SupplierCode;
    }

    public void setVC_SupplierCode(String VC_SupplierCode) {
        this.VC_SupplierCode = VC_SupplierCode;
    }

    public String getVc_billno() {
        return vc_billno;
    }

    public void setVc_billno(String vc_billno) {
        this.vc_billno = vc_billno;
    }

    public String getVC_Suppliername() {
        return VC_Suppliername;
    }

    public void setVC_Suppliername(String VC_Suppliername) {
        this.VC_Suppliername = VC_Suppliername;
    }

    public String getVC_Specification() {
        return VC_Specification;
    }

    public void setVC_Specification(String VC_Specification) {
        this.VC_Specification = VC_Specification;
    }

    public String getVC_Manufacturer() {
        return VC_Manufacturer;
    }

    public void setVC_Manufacturer(String VC_Manufacturer) {
        this.VC_Manufacturer = VC_Manufacturer;
    }

    public String getVC_Unit() {
        return VC_Unit;
    }

    public void setVC_Unit(String VC_Unit) {
        this.VC_Unit = VC_Unit;
    }

    public String getVC_BatchNumber() {
        return VC_BatchNumber;
    }

    public void setVC_BatchNumber(String VC_BatchNumber) {
        this.VC_BatchNumber = VC_BatchNumber;
    }

    public String getD_Amount() {
        return D_Amount;
    }

    public void setD_Amount(String D_Amount) {
        this.D_Amount = D_Amount;
    }

    public String getVc_warehouseCode() {
        return vc_warehouseCode;
    }

    public void setVc_warehouseCode(String vc_warehouseCode) {
        this.vc_warehouseCode = vc_warehouseCode;
    }

    public String getVc_warehousename() {
        return vc_warehousename;
    }

    public void setVc_warehousename(String vc_warehousename) {
        this.vc_warehousename = vc_warehousename;
    }

    public String getPosid() {
        return posid;
    }

    public void setPosid(String posid) {
        this.posid = posid;
    }

    public String getPosno() {
        return posno;
    }

    public void setPosno(String posno) {
        this.posno = posno;
    }

    public String getD_amount1() {
        return D_amount1;
    }

    public void setD_amount1(String D_amount1) {
        this.D_amount1 = D_amount1;
    }

    public String getD_money() {
        return D_money;
    }

    public void setD_money(String D_money) {
        this.D_money = D_money;
    }

    public Integer getState() {
        return State;
    }

    public void setState(Integer State) {
        this.State = State;
    }

    public String getCartonNo() {
        return CartonNo;
    }

    public void setCartonNo(String CartonNo) {
        this.CartonNo = CartonNo;
    }

    public String getOperateTime() {
        return OperateTime;
    }

    public void setOperateTime(String OperateTime) {
        this.OperateTime = OperateTime;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

}