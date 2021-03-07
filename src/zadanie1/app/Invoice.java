package zadanie1.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Invoice extends ViewInList {
    private LocalDateTime created;
    private Customer customer = null;
    private List<GoodsAndCount> listOfGoods = new ArrayList<GoodsAndCount>();
    private double totalValue = 0;

    public static class GoodsAndCount {
        private Goods goodsItem = null;
        private int count = 1;

        public GoodsAndCount(Goods item, int count) {
            this.goodsItem = item;
            this.count = count;
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

        public String getName() {
            return goodsItem.getName();
        }

        public String getDescription() {
            return goodsItem.getDescription();
        }

        public double getValue() {
            return goodsItem.getValue();
        }
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
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

    public void setListOfGoods(List<GoodsAndCount> newList) {
        this.listOfGoods = newList;
    }

    public void addGood(Goods newGood) {
        for (GoodsAndCount good: listOfGoods) {
            if (good.getGoodsItem().getName().equals(newGood.getName())
                    && good.getGoodsItem().getDescription().equals(newGood.getDescription())
                    && String.valueOf(good.getGoodsItem().getValue()).equals(String.valueOf(newGood.getValue()))) {
                good.setCount(good.getCount()+1);
                return;
            }
        }
        listOfGoods.add(new GoodsAndCount(newGood, 1));
    }

    public void deleteGood(GoodsAndCount newGood) {
        for (GoodsAndCount good: listOfGoods) {
            if ( good.getGoodsItem().getName() == newGood.getGoodsItem().getName()) {
                listOfGoods.remove(good);
                return;
            }
        }
    }

    public double getTotalValue() {
        double res = 0;
        for (GoodsAndCount good: listOfGoods) {
            res += good.getCount() * good.getGoodsItem().getValue();
        }
        totalValue = res;
        return totalValue;
    }

    public ArrayList<Invoice.GoodsAndCount> duplicateGoods() {
        ArrayList<Invoice.GoodsAndCount> newList = new ArrayList<>();
        for (Invoice.GoodsAndCount item: this.listOfGoods) {
            Goods newGood = new Goods(item.getName(), item.getDescription(), item.getValue());
            Invoice.GoodsAndCount newGoodAndCount = new Invoice.GoodsAndCount(newGood, item.getCount());
            newList.add(newGoodAndCount);
        }
        return newList;
    }
}
