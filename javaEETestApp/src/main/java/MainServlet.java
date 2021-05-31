import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainServlet extends HttpServlet {

    private List<Product> productList;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        createProductList();
        for (Product p : productList) {
            resp.getWriter().printf("<h1>ID: %s, Name: %s, Price: %s</h1>", p.getId(), p.getTitle(), p.getCost());
        }
    }

    private void createProductList() {
        productList = new ArrayList<>();
        productList.add(new Product(1, "Apple", 23.54));
        productList.add(new Product(2, "Banana", 65.32));
        productList.add(new Product(3, "Potato", 12.25));
        productList.add(new Product(4, "Orange", 54.28));
        productList.add(new Product(5, "Melon", 26.37));
        productList.add(new Product(6, "Carrot", 15.27));
        productList.add(new Product(7, "Pineapple", 89.46));
        productList.add(new Product(8, "Cherry", 154.26));
        productList.add(new Product(9, "Tomato", 54.29));
        productList.add(new Product(10, "Pear", 68.79));
    }
}
