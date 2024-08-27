package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.InhousePartRepository;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;
    private final InhousePartRepository inhousePartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository, InhousePartRepository inhousePartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
        this.inhousePartRepository = inhousePartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

       /*
        OutsourcedPart o= new OutsourcedPart();
        o.setCompanyName("Western Governors University");
        o.setName("out test");
        o.setInv(5);
        o.setPrice(20.0);
        o.setId(100L);
        outsourcedPartRepository.save(o);
        OutsourcedPart thePart=null;
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if(part.getName().equals("out test"))thePart=part;
        }

        System.out.println(thePart.getCompanyName());
        */
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }
        /*
        Product bicycle= new Product("bicycle",100.0,15);
        Product unicycle= new Product("unicycle",100.0,15);
        productRepository.save(bicycle);
        productRepository.save(unicycle);
        */
//partRepository.deleteAll();
//productRepository.deleteAll();
//outsourcedPartRepository.deleteAll();

        if (partRepository.count() == 0) {

            InhousePart strap = new InhousePart();
            strap.setName("Shoulder Strap");
            strap.setPrice(19.99);
            strap.setInv(26);
            strap.setMinimum(1);
            strap.setMaximum(100);


            InhousePart plate = new InhousePart();
            plate.setName("Back Plate");
            plate.setPrice(39.99);
            plate.setInv(9);
            plate.setMinimum(1);
            plate.setMaximum(100);


            InhousePart guard = new InhousePart();
            guard.setName("Pickguard");
            guard.setPrice(49.99);
            guard.setInv(18);
            guard.setMinimum(1);
            guard.setMaximum(100);


            InhousePart strings = new InhousePart();
            strings.setName("Guitar Strings");
            strings.setPrice(59.99);
            strings.setInv(7);
            strings.setMinimum(1);
            strings.setMaximum(100);


            InhousePart bridge = new InhousePart();
            bridge.setName("Guitar Bridge");
            bridge.setPrice(42.99);
            bridge.setInv(10);
            bridge.setMinimum(1);
            bridge.setMaximum(100);


            partRepository.save(strap);
            partRepository.save(plate);
            partRepository.save(guard);
            partRepository.save(strings);
            partRepository.save(bridge);
    }

        if (outsourcedPartRepository.count() == 0) {

            OutsourcedPart knobs = new OutsourcedPart();
            knobs.setName("Guitar Knobs");
            knobs.setPrice(16.99);
            knobs.setInv(7);
            knobs.setCompanyName("Guitars-R-Us");
            knobs.setMinimum(1);
            knobs.setMaximum(100);

            OutsourcedPart capo = new OutsourcedPart();
            capo.setName("Capo");
            capo.setPrice(9.99);
            capo.setInv(9);
            capo.setCompanyName("Guitar Land");
            capo.setMinimum(1);
            capo.setMaximum(100);

            OutsourcedPart head = new OutsourcedPart();
            head.setName("Guitar Head");
            head.setPrice(45.99);
            head.setInv(13);
            head.setCompanyName("Guitar Center");
            head.setMinimum(1);
            head.setMaximum(100);

            OutsourcedPart neck = new OutsourcedPart();
            neck.setName("Guitar Neck");
            neck.setPrice(22.99);
            neck.setInv(8);
            neck.setCompanyName("Guitar Shack");
            neck.setMinimum(1);
            neck.setMaximum(100);

            OutsourcedPart springs = new OutsourcedPart();
            springs.setName("Guitar Springs");
            springs.setPrice(19.99);
            springs.setInv(11);
            springs.setCompanyName("Guitar World");
            springs.setMinimum(1);
            springs.setMaximum(100);

            outsourcedPartRepository.save(knobs);
            outsourcedPartRepository.save(capo);
            outsourcedPartRepository.save(head);
            outsourcedPartRepository.save(neck);
            outsourcedPartRepository.save(springs);

        }

            if (productRepository.count() == 0) {

                Product electricGuitar = new Product("Electric Guitar", 199.99, 28);
                Product acousticGuitar = new Product("Acoustic Guitar", 159.99, 18);
                Product miniGuitar = new Product("Mini Guitar", 99.99, 8);
                Product bassGuitar = new Product("Bass Guitar", 149.99, 5);
                Product jazzGuitar = new Product("Jazz Guitar", 179.99, 7);

                productRepository.save(electricGuitar);
                productRepository.save(acousticGuitar);
                productRepository.save(miniGuitar);
                productRepository.save(bassGuitar);
                productRepository.save(jazzGuitar);

            }



        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());


    }
}
