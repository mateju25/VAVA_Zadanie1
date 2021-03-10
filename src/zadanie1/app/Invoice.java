package zadanie1.app;

import java.util.ArrayList;
import java.util.List;

public class Invoice extends ViewInList implements ItemForChoice {
    private String created;
    private Customer customer;
    private List<GoodsAndCount> listOfGoods = new ArrayList<GoodsAndCount>();

    public static class GoodsAndCount extends Goods {
        private int count = 1;

        public GoodsAndCount(String name, String description, double value, int count) {
            super(name, description, value);
            this.count = count;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

   }

    public String getCreated() {
        return created;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<GoodsAndCount> getListOfGoods() {
        return listOfGoods;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setListOfGoods(List<GoodsAndCount> newList) {
        this.listOfGoods = newList;
    }

    public void addGood(Goods newGood) {
        for (GoodsAndCount good: listOfGoods) {
            if (good.getName().equals(newGood.getName())
                    && good.getDescription().equals(newGood.getDescription())
                    && String.valueOf(good.getValue()).equals(String.valueOf(newGood.getValue()))) {
                good.setCount(good.getCount()+1);
                return;
            }
        }
        listOfGoods.add(new GoodsAndCount(newGood.getName(), newGood.getDescription(), newGood.getValue(), 1));
    }

    public void deleteGood(GoodsAndCount newGood) {
        for (GoodsAndCount good: listOfGoods) {
            if (good.getName().equals(newGood.getName())
                    && good.getDescription().equals(newGood.getDescription())
                    && String.valueOf(good.getValue()).equals(String.valueOf(newGood.getValue()))) {
                listOfGoods.remove(good);
                return;
            }
        }
    }

    public double getTotalValue() {
        double res = 0;
        for (GoodsAndCount good: listOfGoods) {
            res += good.getCount() * good.getValue();
        }
        return res;
    }

    public ArrayList<Invoice.GoodsAndCount> duplicateGoods() {
        ArrayList<Invoice.GoodsAndCount> newList = new ArrayList<>();
        for (Invoice.GoodsAndCount item: this.listOfGoods) {
            Goods newGood = new Goods(item.getName(), item.getDescription(), item.getValue());
            Invoice.GoodsAndCount newGoodAndCount = new Invoice.GoodsAndCount(newGood.getName(), newGood.getDescription(), newGood.getValue(), item.getCount());
            newList.add(newGoodAndCount);
        }
        return newList;
    }
}
