package com.week6challange.lostandfound.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min=2)
    private String itemTitle;

    @NotNull
    private String itemCatgory;

    @Size(min=4)
    private String itemDescription;

    private boolean found;

    private String imgUrl;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    public Item() {
    }

    public Item(String itemTitle, String itemDescription, String itemCatgory, User user) {
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.itemCatgory = itemCatgory;
        this.user = user;
        this.found=found;
        this.imgUrl=imgUrl;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemCatgory() {
        return itemCatgory;
    }

    public void setItemCatgory(String itemCatgory) {
        this.itemCatgory = itemCatgory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
