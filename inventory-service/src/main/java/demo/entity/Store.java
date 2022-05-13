package demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "STORE_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long storeId;
     private String name;
     private String addressLine1;
     private String addressLine2;
     private String state;
     private String country;
     private String pincode;
     private String email;
     private String phoneNumber;
}
