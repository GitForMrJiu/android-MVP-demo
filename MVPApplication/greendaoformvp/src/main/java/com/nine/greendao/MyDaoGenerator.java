package com.nine.greendao;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

/**
 * Created by thinkformoney on 18/1/19.
 */

public class MyDaoGenerator {

    public static void main(String[] args) throws Exception {
        MyDaoGenerator testDaoGenerator = new MyDaoGenerator();
        testDaoGenerator.generate();
    }

    private final Schema schema;

    public MyDaoGenerator() {
//        schema = new Schema(1, "com.linkstart.intelligentfire.db");
//        addQueryScoreHistory(schema);
//        addTimeLine(schema);
        schema = new Schema(2, "com.nine.mvp.db");
//        addQueryMedicine(schema);
//        addMedicineDataSupplier(schema);
//        addMedicineWarehousingInfo(schema);
//        addSupply(schema);
//        addECode(schema);
        addPickHistroy(schema);



    }

    private static void addPickHistroy(Schema schema) {

        Entity PickHistroy = schema.addEntity("PickHistroy");
        PickHistroy.setTableName("PickHistroy");
        PickHistroy.addLongProperty("ID").primaryKey().autoincrement();
        PickHistroy.addStringProperty("VC_Name").notNull();
        PickHistroy.addStringProperty("VC_Code").notNull();
        PickHistroy.addStringProperty("VC_SupplierCode");
        PickHistroy.addStringProperty("vc_billno");
        PickHistroy.addStringProperty("VC_Suppliername");
        PickHistroy.addStringProperty("VC_Specification");
        PickHistroy.addStringProperty("VC_Manufacturer");
        PickHistroy.addStringProperty("VC_Unit");
        PickHistroy.addStringProperty("VC_BatchNumber");
        PickHistroy.addStringProperty("D_Amount");
        PickHistroy.addStringProperty("vc_warehouseCode");
        PickHistroy.addStringProperty("vc_warehousename");
        PickHistroy.addStringProperty("posid");
        PickHistroy.addStringProperty("posno");
        PickHistroy.addStringProperty("D_amount1");
        PickHistroy.addStringProperty("D_money");
        PickHistroy.addIntProperty("State");
        PickHistroy.addStringProperty("CartonNo");
        PickHistroy.addStringProperty("OperateTime");
        PickHistroy.addStringProperty("temp");
    }

    private static void addECode(Schema schema) {
        Entity messageApp = schema.addEntity("MessageApp");
        messageApp.setTableName("MessageApp");
        messageApp.addLongProperty("ID").primaryKey().autoincrement();
        messageApp.addStringProperty("flag").notNull();
        messageApp.addStringProperty("dep").notNull();
        messageApp.addStringProperty("code");
        messageApp.addStringProperty("des");
        messageApp.addStringProperty("stat");
        messageApp.addStringProperty("userid");
        messageApp.addStringProperty("isread");
        messageApp.addStringProperty("dtm");


        Entity ECode = schema.addEntity("ECodeData");
        ECode.setTableName("ECodeData");
        ECode.addLongProperty("ID").primaryKey().autoincrement();
        ECode.addStringProperty("code").notNull();
        ECode.addStringProperty("bl").notNull();
        ECode.addStringProperty("lev_l");
        ECode.addStringProperty("tcode");
        ECode.addStringProperty("batch");
        ECode.addIntProperty("recnum");


        Entity electronicCodeHistory = schema.addEntity("ElectronicCodeHistory");
        electronicCodeHistory.setTableName("ElectronicCodeHistory");
        electronicCodeHistory.addLongProperty("ID").primaryKey().autoincrement();
        electronicCodeHistory.addStringProperty("ECodeFull").notNull();
        electronicCodeHistory.addStringProperty("ECode").notNull();
        electronicCodeHistory.addStringProperty("TCode").notNull();
        electronicCodeHistory.addStringProperty("BillNo").notNull();
        electronicCodeHistory.addStringProperty("DateExe").notNull();
        electronicCodeHistory.addIntProperty("State").notNull();
        electronicCodeHistory.addStringProperty("VC_SupplierCode_His");


        Entity reviewCodeHistory = schema.addEntity("ReviewCodeHistory");
        reviewCodeHistory.setTableName("ReviewCodeHistory");
        reviewCodeHistory.addLongProperty("ID").primaryKey().autoincrement();
        reviewCodeHistory.addStringProperty("ECodeFull").notNull();
        reviewCodeHistory.addStringProperty("ECode").notNull();
        reviewCodeHistory.addStringProperty("TCode").notNull();
        reviewCodeHistory.addStringProperty("BillNo").notNull();
        reviewCodeHistory.addStringProperty("DateExe").notNull();
        reviewCodeHistory.addIntProperty("State").notNull();
        reviewCodeHistory.addDoubleProperty("Recnum").notNull();
        reviewCodeHistory.addStringProperty("VC_SupplierCode_His");
    }
    private static void addElectronicCodeHistory(Schema schema) {

    }
    private static void addQueryHistory(Schema schema) {
        Entity entity  = schema.addEntity("QueryHistory");
        entity .setTableName("queryHistory_db");
        entity .addLongProperty("id").primaryKey().autoincrement();
        entity .addStringProperty("content").notNull();
        entity .addStringProperty("userId").notNull();
    }
    private static void addQueryMedicine(Schema schema) {
        Entity entity  = schema.addEntity("MedicineCheckInfo");
        entity .setTableName("MedicineCheckInfo");
        entity .addLongProperty("ID").primaryKey().autoincrement();
        entity .addStringProperty("VC_Code").notNull();
        entity .addStringProperty("D_Price");
        entity .addStringProperty("D_Price_His");
        entity .addStringProperty("D_PriceSale");
        entity .addStringProperty("D_Rate_HousingChange");
        entity .addStringProperty("VC_Code_His");
//        entity .addStringProperty("VC_Code_Manufacturer");
        entity .addStringProperty("VC_CommodityCodeNational");
        entity .addStringProperty("VC_Kind");
        entity .addStringProperty("VC_Kind_His");
        entity .addStringProperty("VC_Manufacturer");
        entity .addStringProperty("VC_Manufacturer_His");
        entity .addStringProperty("VC_Name");
        entity .addStringProperty("VC_Name_His");
        entity .addStringProperty("VC_NameShort");
        entity .addStringProperty("VC_OperateDatetime");
        entity .addStringProperty("VC_Specification");
        entity .addStringProperty("VC_Specification_His");
        entity .addStringProperty("VC_SupplierCode").notNull();
        entity .addStringProperty("VC_SupplierCode_His");
//        entity .addStringProperty("VC_SupplierName");
        entity .addStringProperty("VC_WarehouseCode");
        entity .addStringProperty("VC_WarehouseName");
        entity .addStringProperty("VC_Unit");
        entity .addStringProperty("VC_Unit_His");
        entity .addStringProperty("vc_registrationcode");
        entity .addLongProperty("N_ID");
    }
    private static void addSupply(Schema schema) {
        Entity entity = schema.addEntity("SupplierCheckInfo");
        entity.setTableName("SupplierCheckInfo");
        entity.addLongProperty("ID").primaryKey().autoincrement();
        entity .addStringProperty("VC_Code");
        entity .addStringProperty("VC_Code_His");
        entity .addStringProperty("VC_Name");
        entity .addStringProperty("VC_Name_His");
        entity .addLongProperty("N_ID");
    }
    private static void addMedicineDataSupplier(Schema schema) {
        Entity entity = schema.addEntity("MedicineDataSupplier");
        entity.setTableName("MedicineDataSupplier");
        entity.addLongProperty("ID").primaryKey().autoincrement();
        entity .addStringProperty("VC_UploadDeptCode").notNull();
        entity .addStringProperty("VC_BillCode").notNull();
        entity .addStringProperty("N_BillOrder").notNull();
        entity .addStringProperty("VC_BillPlatform");
        entity .addStringProperty("VC_SupplierCode");
        entity .addStringProperty("VC_SupplierName");
        entity .addStringProperty("VC_MedicalInstitutionID");
        entity .addStringProperty("VC_MedicalInstitutionName");
        entity .addStringProperty("VC_SupplyDatetime");
        entity .addStringProperty("VC_RegistrationCode");
        entity .addStringProperty("VC_Name");
        entity .addStringProperty("VC_Manufacturer");
        entity .addStringProperty("VC_ManufacturerCode");
        entity .addStringProperty("VC_SupervisionCode");
        entity .addStringProperty("VC_BarCode");
        entity .addStringProperty("VC_BatchNumber");
        entity .addStringProperty("VC_ManufactureDate");
        entity .addStringProperty("VC_Expirydate");
        entity .addStringProperty("VC_Specification");
        entity .addStringProperty("VC_Form");
        entity .addStringProperty("N_IsUrgent");
        entity .addStringProperty("VC_Unit");
        entity .addStringProperty("D_Price");
        entity .addStringProperty("D_Amount");
        entity .addStringProperty("D_Money");
        entity .addStringProperty("D_BillMoney");
        entity .addStringProperty("VC_InvoiceNo");
        entity .addStringProperty("VC_InvoiceDate");
        entity .addStringProperty("VC_CommodityCodeNational");
        entity .addStringProperty("VC_BatchNumberAsepsis");
        entity .addStringProperty("VC_Info1");
        entity .addStringProperty("VC_Info2");
        entity .addStringProperty("VC_Info3");
        entity .addStringProperty("VC_Info4");
        entity .addStringProperty("VC_UploadDatetime");
        entity .addStringProperty("VC_XmlDatetime");
        entity .addLongProperty("N_ID");
    }

    private static void addMedicineWarehousingInfo(Schema schema) {
        Entity entity = schema.addEntity("MedicineWarehousingInfo");
        entity.setTableName("MedicineWarehousingInfo");
        entity.addLongProperty("ID").primaryKey().autoincrement();
        entity .addStringProperty("VC_SupplierCode").notNull();
        entity .addStringProperty("VC_SupplierName");
        entity .addStringProperty("VC_SupplierCode_Add");
        entity .addStringProperty("VC_SupplierCode_His");
        entity .addStringProperty("VC_SupplierName_His");
        entity .addStringProperty("VC_BillNo").notNull();
        entity .addStringProperty("N_BillOrder").notNull();
        entity .addStringProperty("VC_InstitutionCode");
        entity .addStringProperty("VC_BillNo_Platform");
        entity .addStringProperty("VC_Kind");
        entity .addStringProperty("VC_Kind_His");
        entity .addStringProperty("VC_Code");
        entity .addStringProperty("VC_Code_His");
        entity .addStringProperty("VC_Code_Supervision");
        entity .addStringProperty("VC_Name");
        entity .addStringProperty("VC_Name_His");
        entity .addStringProperty("VC_RegistrationCode");
        entity .addStringProperty("VC_Manufacturer");
        entity .addStringProperty("VC_Manufacturer_His");
        entity .addStringProperty("VC_BatchNumber");
        entity .addStringProperty("VC_BarCode");
        entity .addStringProperty("VC_BarCode2");
        entity .addStringProperty("VC_CommodityCodeNational");
        entity .addStringProperty("VC_Expirydate");
        entity .addStringProperty("VC_Specification");
        entity .addStringProperty("VC_Specification_His");
        entity .addStringProperty("VC_Unit");
        entity .addStringProperty("VC_Unit_His");
        entity .addStringProperty("D_Amount");
        entity .addStringProperty("D_Amount1");
        entity .addStringProperty("D_Price");
        entity .addStringProperty("D_Price1");
        entity .addStringProperty("D_Price_His");
        entity .addStringProperty("D_PriceSale");
        entity .addStringProperty("D_Rate_HousingChange");
        entity .addStringProperty("D_Money");
        entity .addStringProperty("VC_InvoiceNo");
        entity .addStringProperty("D_BillMoney");
        entity .addStringProperty("VC_InvoiceDate");
        entity .addStringProperty("VC_Saler");
        entity .addStringProperty("VC_WarehouseCode");
        entity .addStringProperty("VC_WarehouseName");
        entity .addStringProperty("VC_WarehousingNo");
        entity .addStringProperty("VC_WarehousingOrder");
        entity .addStringProperty("VC_WarehousingDatetime");
        entity .addStringProperty("VC_WarehousingOperator");
        entity .addStringProperty("VC_ScanState");
        entity .addStringProperty("VC_ScanDatetime");
        entity .addStringProperty("VC_ScanOperator");
        entity .addStringProperty("VC_State");
        entity .addStringProperty("VC_SupplyDatetime");
        entity .addLongProperty("N_ID");
    }
    private static void addQueryScoreHistory(Schema schema) {
        Entity entity  = schema.addEntity("QueryScoreHistory");
        entity .setTableName("queryScoreHistory");
        entity .addLongProperty("id").primaryKey().autoincrement();
        entity .addStringProperty("userName").notNull();
        entity .addStringProperty("userId").notNull();
    }

    public void generate() throws Exception {
        DaoGenerator daoGenerator = new DaoGenerator();
//        daoGenerator.generateAll(schema, System.getProperty("user.dir")+"/app/src/main/java/");
        daoGenerator.generateAll(schema, System.getProperty("user.dir")+"/");


    }

}
