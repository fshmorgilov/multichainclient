package com.themaker.fshmo.klassikaplus.data.persistence.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "items")
public class DbItem {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "name")
    private String name; //h1

    @ColumnInfo(name = "title")
    private String title;//title

    @ColumnInfo(name = "description")
    private String description; //ключевое описание

    @ColumnInfo(name = "annotation")
    private String annotation;

    @ColumnInfo(name = "vendor_code") //артикул
    private String vendorCode;

    @ColumnInfo(name = "shown_on_main") //показать на главной
    private Boolean shownOnMain;

    @ColumnInfo(name = "novelty")
    private Boolean novelty;

    @ColumnInfo(name = "description_long") //содержимое
    private String descriptionLong;

    @ColumnInfo(name = "page_alias")
    private String pageAlias;

    @ColumnInfo(name = "template") //todo шаблон отображения для сайта, возможно мне не нужен
    private String template;

    @ColumnInfo(name = "menu_item_name")
    private String menuItemName; //Пункт Меню

    @ColumnInfo(name = "related_link")
    private String relatedLink; // Сторонняя ссылка

    @ColumnInfo(name = "dont_show")
    private Boolean dontShowInMenu; //Не показывать в меню

    @ColumnInfo(name = "published")
    private Boolean published;

    @ColumnInfo(name = "keywords")
    private String keywords;

    ////------additional_fields-----------////
    //photo
    @ColumnInfo(name = "icon")
    private String icon;

    @Ignore
    private String photo;

    //Characteristics
    @Ignore
    private String countryManufacturer; //Страна производитель

    @Ignore
    private String composition; //хз че с этим делать

    @Ignore
    private String manufacturer; //производитель

    @Ignore
    private List<String> styleCollection;

    //Pricing
    @ColumnInfo(name = "base_price")
    private double basePrice;

    @ColumnInfo(name = "discount")
    private double discount;

    @ColumnInfo(name = "price")
    private double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public Boolean getShownOnMain() {
        return shownOnMain;
    }

    public void setShownOnMain(Boolean shownOnMain) {
        this.shownOnMain = shownOnMain;
    }

    public Boolean getNovelty() {
        return novelty;
    }

    public void setNovelty(Boolean novelty) {
        this.novelty = novelty;
    }

    public String getDescriptionLong() {
        return descriptionLong;
    }

    public void setDescriptionLong(String descriptionLong) {
        this.descriptionLong = descriptionLong;
    }

    public String getIcon() {
        return icon;
    }

    public double getPrice() {
        return price;
    }
}
