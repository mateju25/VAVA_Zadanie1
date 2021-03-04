package app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Invoice {
    private class GoodsAndCount {
        private Goods item = new Goods();
        private int count = 0;

        public Goods getItem() {
            return item;
        }

        public void setItem(Goods item) {
            this.item = item;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    private Date created = null;
    private Customer customer = null;
    private List<GoodsAndCount> listOfGoods = null;
    private int totalValue = 0;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
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

    public void setListOfGoods(List<GoodsAndCount> listOfGoods) {
        this.listOfGoods = listOfGoods;
    }
}
