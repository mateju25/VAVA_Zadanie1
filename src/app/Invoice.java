package app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Invoice implements Cloneable, ViewInListView {
    private Date created;
    private Customer customer = null;
    private List<GoodsAndCount> listOfGoods = new ArrayList<GoodsAndCount>();
    private int totalValue = 0;

    public class GoodsAndCount implements ViewInListView{
        private Goods goodsItem = null;
        private int count = 1;

        public GoodsAndCount(Goods item) {
            this.goodsItem = item;
            this.count = 1;
        }

        public Goods getGoodsItem() {
            return goodsItem;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getInfo() {
            return goodsItem.getInfo() + String.format("Počet: %-10s", String.valueOf(this.count));
        }
    }

    public Invoice() {
        this.created = new Date();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<GoodsAndCount> getListOfGoods() {
        return listOfGoods;
    }

    public void addGood(Goods newGood) {
        try {
            newGood = (Goods) newGood.clone();
        } catch (Exception e ){
            e.printStackTrace();
        }
        for (GoodsAndCount good: listOfGoods) {
            if ( good.getGoodsItem().getName() == newGood.getName()) {
                good.setCount(good.getCount()+1);
                return;
            }
        }
        listOfGoods.add(new GoodsAndCount(newGood));
    }

    public void deleteGood(GoodsAndCount newGood) {
        for (GoodsAndCount good: listOfGoods) {
            if ( good.getGoodsItem().getName() == newGood.getGoodsItem().getName()) {
                listOfGoods.remove(good);
                return;
            }
        }
    }

    public int getTotalValue() {
        int res = 0;
        for (GoodsAndCount good: listOfGoods) {
            res += good.getCount() * good.getGoodsItem().getValue();
        }
        totalValue = res;
        return totalValue;
    }

    public String getInfo() {
        String temp = "| ";
        for (GoodsAndCount good: listOfGoods) {
            temp += good.getInfo() + "|";
        }
        return customer.getInfo() + temp;
    }

    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }
}
