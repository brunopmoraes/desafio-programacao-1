package com.nexaas.domain;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name="FILE")
public class File implements Serializable{

    private static final long serialVersionUID = 5572656230643523274L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String purchaserName;
    private String itemDescription;
    private BigDecimal itemPrice;
    private Long purchaseCount;
    private String merchantAddress;
    private String merchantName;

    public File() {
    }

    public File(String purchaserName, String itemDescription, BigDecimal itemPrice, Long purchaseCount, String merchantAddress, String merchantName) {
        this.purchaserName = purchaserName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.purchaseCount = purchaseCount;
        this.merchantAddress = merchantAddress;
        this.merchantName = merchantName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Long getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(Long purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
}
