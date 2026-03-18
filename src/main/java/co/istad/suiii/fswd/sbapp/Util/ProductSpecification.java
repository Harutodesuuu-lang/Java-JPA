package co.istad.suiii.fswd.sbapp.Util;

import co.istad.suiii.fswd.sbapp.domain.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> nameContains(String name) {
        return (root, query, cb) -> {
            if (name == null) {
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }
    public static Specification<Product> priceFilter(String price) {

        if (price == null) {
            return (root, query, cb) -> cb.conjunction();
        }

        if (price.startsWith("+")) {
            Double value = Double.parseDouble(price.substring(1));
            return (root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get("price"), value);
        } else if (price.startsWith("-")) {
            Double value = Double.parseDouble(price.substring(1));
            return (root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("price"), value);
        }

        return (root, query, cb) -> cb.conjunction();
    }

    public static Specification<Product> qtyFilter(String qty) {

        if (qty == null) {
            return (root, query, cb) -> cb.conjunction();
        }

        if (qty.startsWith("+")) {
            Integer value = Integer.parseInt(qty.substring(1));
            return (root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get("qty"), value);
        } else if (qty.startsWith("-")) {
            Integer value = Integer.parseInt(qty.substring(1));
            return (root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("qty"), value);
        }

        return (root, query, cb) -> cb.conjunction();
    }
}