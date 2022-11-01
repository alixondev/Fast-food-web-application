package com.example.appdbservice.entity.users;

import com.example.appdbservice.entity.product.Product;
import com.example.appdbservice.entity.template.AbsLongEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity(name = "favourite")
public class Favourite extends AbsLongEntity {

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Product> favouriteProduct;

}
